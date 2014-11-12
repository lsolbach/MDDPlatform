package org.soulspace.base.domain.aggregate;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.soulspace.annotation.domain.Aggregation;
import org.soulspace.base.domain.object.EntityAspect;
import org.soulspace.util.CollectionUtils;
import org.soulspace.util.ReflectionUtils;

privileged public aspect AggregateAspect {

	declare precedence : AggregateAspect, EntityAspect;

	declare parents : (@org.soulspace.annotation.domain.AggregateRoot *) implements AggregateRoot;
	declare parents : (@org.soulspace.annotation.domain.AggregateChild *) implements AggregateChild;

	//
	// Intertype declarations
	//
	
	// Aggregate
	// mark initialisation
	private transient boolean Aggregate.initialized = false;
	void Aggregate.initialize(Aggregate aggregate) {
		if(!initialized) {
			aggregateChildMap = new HashMap<Integer, AggregateChild>();

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
	public transient Map<Integer, AggregateChild> Aggregate.aggregateChildMap = new HashMap<Integer, AggregateChild>();
	
	public List<AggregateChild> Aggregate.getAggregateChild() {
		List<AggregateChild> descendants = CollectionUtils.asList(aggregateChildMap.values(), AggregateChild.class);
		
		for(AggregateChild child : aggregateChildMap.values()) {
			descendants.addAll(child.getAggregateChild());
		}
		return descendants;
	}
	
	public void Aggregate.addAggregateChild(AggregateChild child) {
		aggregateChildMap.put(child.hashCode(), child);
	}
	
	public void Aggregate.removeAggregateChild(AggregateChild child) {
		aggregateChildMap.remove(child.hashCode());
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

	//
	// Pointcuts
	//
	pointcut inAggregateAspect() :
		adviceexecution() && within(AggregateAspect)
		;
	
	pointcut AggregateConstruction() :
		call(Aggregate+.new(..))
		;
	
	pointcut aggregateChildMapAccess(Aggregate aggregate) :
		get(Map<Integer, AggregateChild>+ *.aggregateChildMap)
		&& target(aggregate)
		;
	
	// pointcuts for aggregate structure operations
	pointcut addChild(Aggregate parent, AggregateChild child) :
		execution(* Aggregate+.add*(AggregateChild+))
		&& !execution(* Aggregate+.addAggregateChild(..))
		&& this(parent) && args(child)
		;
	
	pointcut setChild(Aggregate parent, AggregateChild child) :
		execution(* Aggregate+.set*(AggregateChild+))
		&& this(parent) && args(child)
		;
	
	pointcut aggregateChildAddition(Aggregate parent, AggregateChild child) :
		addChild(parent, child)
		|| setChild(parent, child)
		;
		
	pointcut aggregateChildRemoval(Aggregate parent, AggregateChild child) :
		execution(* Aggregate+.remove*(AggregateChild+))		
		&& !execution(* Aggregate+.removeAggregateChild(..))
		&& this(parent) && args(child)
		;	
	
	//
	// Advices
	//
	before(Aggregate aggregate) : aggregateChildMapAccess(aggregate) {
		aggregate.initialize(aggregate);
	}
	
//	after() returning(Aggregate aggregate) : aggregateRootConstruction() {
//		aggregate.initialize();
//	}
	
	void around(Aggregate parent, AggregateChild child) : aggregateChildAddition(parent, child) {
		// check params
		proceed(parent, child);
		if(parent instanceof AggregateRoot) {
			child.setAggregateRoot((AggregateRoot) parent);
		} else {
			child.setAggregateRoot(((AggregateChild) parent).getAggregateRoot());			
		}
		parent.addAggregateChild(child);
	}
	
	void around(Aggregate parent, AggregateChild child) : aggregateChildRemoval(parent, child) {
		// check params
		proceed(parent, child);
		child.setAggregateRoot(null);
		parent.removeAggregateChild(child);		
	}

}
