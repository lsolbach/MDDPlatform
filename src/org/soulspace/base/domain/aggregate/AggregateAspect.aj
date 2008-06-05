package org.soulspace.base.domain.aggregate;


import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.soulspace.annotation.domain.Aggregation;
import org.soulspace.base.domain.entity.EntityAspect;
import org.soulspace.util.CollectionUtils;
import org.soulspace.util.ReflectionUtils;

privileged public aspect AggregateAspect {

	declare precedence : AggregateAspect, EntityAspect;

	//
	// Intertype declarations
	//
	
	// Aggregate
	// mark initialisation
	private transient boolean Aggregate.initialized = false;
	void Aggregate.initialize(Aggregate aggregate) {
		if(!initialized) {
			aggregateChildMap = new HashMap<String, AggregateChild>();

			Field[] fields = getClass().getFields();
			for(Field field : fields) {
				if(field.getAnnotation(Aggregation.class) != null) {
//				if(field.isAnnotationPresent(Aggregation.class)) {
					System.out.println("adding elements from aggregation " + field.getName());
					Object value = ReflectionUtils.getFieldValue(this, field);
					if(value != null) {
						if(value instanceof AggregateChild) {
							addAggregateChild((AggregateChild) value);
						} else if(value instanceof Collection) {
							for(Object o : (Collection) value) {
								if(o instanceof AggregateChild) {
									addAggregateChild((AggregateChild) o);									
								}
							}
						} else if(value instanceof Map) {
							for(Object o : ((Map) value).values()) {
								if(o instanceof AggregateChild) {
									addAggregateChild((AggregateChild) o);									
								}
							}
						} else {
							System.out.println("Unhandled field type " + value.getClass().getSimpleName());
						}
					}
				}
			}
			initialized = true;
		}
	}
	
	// map of all children of the aggregate
	public transient Map<String, AggregateChild> Aggregate.aggregateChildMap = new HashMap<String, AggregateChild>();
	
	public List<AggregateChild> Aggregate.getAggregateChild() {
		List<AggregateChild> descendants = CollectionUtils.asList(aggregateChildMap.values(), AggregateChild.class);
		
		for(AggregateChild child : aggregateChildMap.values()) {
			descendants.addAll(child.getAggregateChild());
		}
		return descendants;
	}
	
	public AggregateChild Aggregate.findAggregateChild(String id) {
		AggregateChild result = null;
		if((result = aggregateChildMap.get(id)) != null) {
			return result;
		} else {
			for(AggregateChild child : aggregateChildMap.values()) {
				if((result = child.findAggregateChild(id)) != null) {
					return result;
				}
			}
		}
		return null;
	}
	
	public void Aggregate.addAggregateChild(AggregateChild child) {
		aggregateChildMap.put(child.getId(), child);
	}
	
	public void Aggregate.removeAggregateChild(AggregateChild child) {
		aggregateChildMap.remove(child.getId());
	}
	
	// AggregateChild
	// reference to the root of the aggregate
	public transient AggregateRoot AggregateChild.aggregateRoot;

	public AggregateRoot AggregateChild.getAggregateRoot() {
		return aggregateRoot;
	}

	void AggregateChild.setAggregateRoot(AggregateRoot root) {
		aggregateRoot = root;
	}

	declare parents : (@org.soulspace.annotation.domain.AggregateRoot *) implements AggregateRoot;
	declare parents : (@org.soulspace.annotation.domain.AggregateChild *) implements AggregateChild;

	//
	// Pointcuts
	//
	pointcut inAggregateAspect() :
		adviceexecution() && within(AggregateAspect)
		;
	
	pointcut constructAggregate() :
		call(Aggregate+.new(..))
		;
	
	pointcut accessAggregateChildMap(Aggregate aggregate) :
		get(Map<String, AggregateChild>+ *.aggregateChildMap)
		&& target(aggregate)
		;
	
	// pointcuts for aggregate structure operations
	pointcut addAggregateChild(Aggregate parent, AggregateChild child) :
		execution(* Aggregate+.add*(AggregateChild+))
		&& !execution(* Aggregate+.addAggregateChild(..))
		&& this(parent) && args(child)
		;
	
	pointcut setAggregateChild(Aggregate parent, AggregateChild child) :
		execution(* Aggregate+.set*(AggregateChild+))
		&& this(parent) && args(child)
		;
	
	pointcut newAggregateChild(Aggregate parent, AggregateChild child) :
		addAggregateChild(parent, child)
		|| setAggregateChild(parent, child)
		;
		
	pointcut removeAggregateChild(Aggregate parent, AggregateChild child) :
		execution(* Aggregate+.remove*(AggregateChild+))		
		&& !execution(* Aggregate+.removeAggregateChild(..))
		&& this(parent) && args(child)
		;	
	
	//
	// Advices
	//

	before(Aggregate aggregate) : accessAggregateChildMap(aggregate) {
		aggregate.initialize(aggregate);
	}
	
//	after() returning(Aggregate aggregate) : constructAggregateRoot() {
//		aggregate.initialize();
//	}
	
	void around(Aggregate parent, AggregateChild child) : newAggregateChild(parent, child) {
		// check params
		proceed(parent, child);
		if(parent instanceof AggregateRoot) {
			child.setAggregateRoot((AggregateRoot) parent);
		} else {
			child.setAggregateRoot(((AggregateChild) parent).getAggregateRoot());			
		}
		parent.addAggregateChild(child);
	}
	
	void around(Aggregate parent, AggregateChild child) : removeAggregateChild(parent, child) {
		// check params
		proceed(parent, child);
		child.setAggregateRoot(null);
		parent.removeAggregateChild(child);		
	}

//	pointcut childChange(Entity child) :
//		execution(void Entity.markDirty())
//		&& this(child)
//		;
//	
//	after(Entity child) returning : childChange(child) {
//		if(child instanceof AggregateChild) {
//			((AggregateChild) child).getAggregateRoot().markDirty();			
//		}
//	}
//	
//	void newAggregateChildModification(Aggregate a, Date modificationTime) {
//		for(AggregateChild child : a.getAggregateChild()) {
//			// create new modifications of aggregate childs
//			if(((Persistant) child).isDirty() || ((Entity) child).getModification() == 0) {
//				((Persistant) child).newModification(modificationTime);
//				newAggregateChildModification(child, modificationTime);
//			}
//		}
//	}	

}
