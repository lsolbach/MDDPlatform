package org.soulspace.base.domain.repository;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.soulspace.base.domain.entity.Entity;
import org.soulspace.base.infrastructure.persistence.PersistentStorage;

/**
 * The RepositoryAspect handles the persistance of Entity objects
 * 
 */
privileged public aspect RepositoryAspect {

	// FIXME convert StorageException to RepositoryException
	Log log = LogFactory.getLog("Repository");
	
	//
	// intertype declarations
	//
	declare parents : (@org.soulspace.annotation.domain.Repository *) implements Repository;

	public PersistentStorage Repository.storage;
	public void Repository.setStorage(PersistentStorage storage) {
		this.storage = storage;
		registerClasses();
	}
	PersistentStorage Repository.getStorage() {
		return storage;
	}
	
	
	//
	// pointcuts
	//
	pointcut loadList(Repository repository) :
		execution(* Repository+.get*List())
		&& this(repository)
//		&& args(type);
		;	

	pointcut loadListValid(Repository repository, boolean onlyValid) :
		execution(* Repository+.get*List(boolean))
		&& this(repository)
		&& args(onlyValid);
		;	

	pointcut loadListForTime(Repository repository, Date time) :
		execution(* Repository+.get*List(Date))
		&& this(repository)
		&& args(time);
		;	

	pointcut load(Repository repository, String id) :
		execution(* Repository+.get*(String))
		&& this(repository)
		&& args(id)
		;	
	
	pointcut loadForTime(Repository repository, String id, Date time) :
		execution(* Repository+.get*(String, Date))
		&& this(repository)
		&& args(id, time)
		;
	
	pointcut store(Repository repository, Entity entity) :
		execution(* Repository+.store*(Entity+))
		&& this(repository)
		&& args(entity)
		;
	
	pointcut remove(Repository repository, Entity entity) :
		execution(* Repository+.remove*(Entity+))
		&& this(repository)
		&& args(entity)
		;

	//
	// advices
	//
	/**
	 * Advice around load[Entity]List
	 */
//	List around(Repository repository) : loadEntityList(repository) {
//		Date accessTime = new Date();
//		Storage storage = repository.getStorage();
//		String methodName = thisJoinPoint.getSignature().getName();
//		String name = methodName.substring(3, methodName.length() - 4);
//		Class type = storage.lookupClass(name);
//		// filter(storage.loadPersistantList(type));
//		if(Temporal.class.isAssignableFrom(type)) {
//			return storage.loadList(type, accessTime);
//		} else {
//			return storage.loadList(type);
//		}
//	}
//	
//	/**
//	 * Advice around load[Entity]List
//	 */
//	List around(Repository repository, boolean onlyValid) : loadEntityListValid(repository, onlyValid) {
//		Storage storage = repository.getStorage();
//		String methodName = thisJoinPoint.getSignature().getName();
//		String name = methodName.substring(3, methodName.length() - 4);
//		Class type = storage.lookupClass(name);
//		return storage.loadList(type, onlyValid);			
//	}
//	
//	/**
//	 * Advice around load[Entity]ListForTime
//	 */
//	List around(Repository repository, Date time) : loadEntityListForTime(repository, time) {
//		Storage storage = repository.getStorage();
//		String methodName = thisJoinPoint.getSignature().getName();
//		String name = methodName.substring(3, methodName.length() - 4);
//		Class type = storage.lookupClass(name);
//		return storage.loadList(type, time);
//	}
//	
//	/**
//	 * load[Entity]
//	 */
//	Object around(Repository repository, String id) : loadEntity(repository, id) {
//		Date accessTime = new Date();
//		Storage storage = repository.getStorage();
//		String methodName = thisJoinPoint.getSignature().getName();
//		String name = methodName.substring(3);
//		Class type = storage.lookupClass(name);
//		return storage.load(type, id, accessTime);			
//	}
//	
//	/**
//	 * load[Entity]
//	 */
//	Object around(Repository repository, String id, Date time) : loadEntityForTime(repository, id, time) {
//		Storage storage = repository.getStorage();
//		String methodName = thisJoinPoint.getSignature().getName();
//		String name = methodName.substring(3);
//		System.out.println("Class: " + name);
//		Class type = storage.lookupClass(name);
//		return storage.load(type, id, time);
//	}
//	
//	/**
//	 * Advice around store[Entity].
//	 *  
//	 */
//	void around(Repository repository, Persistant persistant) : storeEntity(repository, persistant) {
//		Date modificationTime = new Date();
//		Storage storage = repository.getStorage();
//
//		// check, if entity has to be stored
//		if(persistant.getModification() != 0 && !persistant.isDirty()) {
//			// storing not needed
//			log.debug("Storing skipped, entity not dirty");
//			return;
//		}
//		
//		// TODO lock on entity or on repository?
//		synchronized (repository) {
//			if(persistent.getModification() == 0) {
//				// new entity, create first modification
//				persistent.newModification(modificationTime);
//				// TODO restore modification time and version, if store fails?
//				storage.write(persistent);
//				persistent.clearDirty();
//			} else if(persistant.isDirty()) {
//				// dirty, must be stored
//
//				// optimistic locking
//				// get stored entity
//				Persistent stored = storage.load(persistent.getClass(), persistent.getId());
//				if(stored == null || !persistant.getClass().isInstance(stored)) {
//					throw new StorageException("Couldn't load stored version of Object " + persistent.getId());
//				}
//				// compare modification for up to date check (optimistic locking)
//				if(stored.getModification() != persistent.getModification()) {
//					throw new RepositoryException("Object was modified, this modification is " + persistent.getModification() + ", stored modification is " + storedEntity.getModification());
//				}
//				
//				if(persistant instanceof Temporal) {
//					writeTemporalEntity(storage, (Temporal) persistant, modificationTime);
//				} else if(persistant instanceof Revisioned) {				
//					writeRevisionedEntity(storage, (Revisioned) persistant, modificationTime);
//				} else {
//					writeEntity(storage, persistant, modificationTime);
//				}
//			}
//		}
//	}
//	
//	/**
//	 * 
//	 */
//	void around(Repository repository, Persistant persistant) : removeEntity(repository, persistant) {
//		Date modificationTime = new Date();
//		synchronized (repository) {
//			Storage storage = repository.getStorage();
//			Persistant storedPersistant = storage.load(persistant.getClass(), persistant.getId());
//			if(storedPersistant == null || !persistant.getClass().isInstance(storedPersistant)) {
//				throw new RepositoryException("Couldn't load stored version of entity " + persistant.getId());
//			}
//			// compare modification for up to date check (optimistic locking)
//			if(storedPersistant.getModification() != persistant.getModification()) {
//				throw new RepositoryException("Object was modified, this modification is " + persistant.getModification() + ", stored modification is " + storedEntity.getModification());
//			}
//			if(persistant instanceof Temporal) {
//				// mark stored entities invalid
//				List<? extends Persistant> validEntities = storage.loadList(persistant.getClass(), persistant.getId(), true);
//				for(Persistant t : validEntities) {
//					
//					((Temporal) t).setInvalidationTime(modificationTime);
//					storage.write(t);
//				}
//			} else if(persistant instanceof Revisioned) {
//				// mark stored entity invalid
//				((Revisioned) persistant).setInvalidationTime(modificationTime);
//				storage.write(persistant);
//			} else {
//				storage.delete(persistant);
//			}
//		}
//	}
//
////	/**
////	 * Write a temporal entity.
////	 * Check for temporal intersections and generate new temporal sliced entities accordingly. 
////	 * @param storage
////	 * @param entity
////	 * @param modificationTime
////	 */
////	void writeTemporal(Storage storage, Entity entity, Date modificationTime) {		
////		Temporal te = (Temporal) entity;
////		List<? extends Temporal> temporals = storage.loadTemporalEntities(entity.getClass(), entity.getId(), true);
////		Collections.sort(temporals, new Temporal.TemporalComparator());
////
////		for(Temporal t : temporals) {
////			if(te.intersectsBoth(t)) {
////				// intersected 
////				log.debug("intersects begin and end");
////				Temporal newT1 = storage.loadEntity(t.getClass(), t.getId(), t.getModification());
////				Temporal newT2 = storage.loadEntity(t.getClass(), t.getId(), t.getModification());
////
////				newT1.newModification(modificationTime);
////				newT1.setValidTo(te.getValidFrom());
////				storage.writePersistant(newT1);
////
////				newT2.newModification(modificationTime);
////				newT2.setValidFrom(te.getValidTo());
////				storage.writePersistant(newT2);
////
////				t.setInvalidationTime(modificationTime);
////				storage.writePersistant(t);
////			} else if(te.intersectsBegin(t)) {
////				// intersected
////				log.debug("intersects begin");
////
////				Temporal newT = storage.loadEntity(t.getClass(), t.getId(), t.getModification());
////				newT.newModification(modificationTime);
////				newT.setValidFrom(te.getValidTo());
////				storage.writePersistant(newT);
////
////				t.setInvalidationTime(modificationTime);
////				storage.writePersistant(t);
////			} else if(te.intersectsEnd(t)) {
////				// intersected
////				log.debug("intersects end");
////				
////				Temporal newT = storage.loadEntity(t.getClass(), t.getId(), t.getModification());
////				newT.newModification(modificationTime);
////				newT.setValidTo(te.getValidFrom());
////				storage.writePersistant(newT);
////
////				t.setInvalidationTime(modificationTime);
////				storage.writePersistant(t);
////			} else if(te.hides(t)){
////				// hidden
////				log.debug("not intersected but hidden");
////				t.setInvalidationTime(modificationTime);
////				storage.writePersistant(t);
////			}
////		}
////		writeEntity(storage, entity, modificationTime);
////	}
////
////	/**
////	 * Write a revisioned entity.
////	 * Invalidate last revision and create new revision.
////	 * @param storage
////	 * @param entity
////	 * @param modificationTime
////	 */
////	void writeRevisionedEntity(Storage storage, Persistent persistant, Date modificationTime) {
////		// from optimistic locking we know storedEntity exists
////		Persistent storedEntity = storage.loadPersistant(persistant.getClass(), persistant.getId());
////		
////		((Revisioned) storedEntity).setInvalidationTime(modificationTime);
////		storage.writePersistant(storedEntity);
////		writePersistant(storage, persistant, modificationTime);		
////	}
////
////	/**
////	 * Write an entity.
////	 * @param storage
////	 * @param entity
////	 * @param modificationTime
////	 */
////	void writePersistant(Storage storage, Persistent persistant, Date modificationTime) {
////		// create new modification
////		persistant.newModification(modificationTime);
////
////		// create new modifications of aggregate childs
////		if(persistant instanceof AggregateRoot) {
////			AggregateRoot root = (AggregateRoot) persistant;
////			newAggregateChildModification(root, modificationTime);
////		}
////		
////		// TODO transaction: restore modification time and version, if store fails?
////		storage.writePersistant(persistant);
////		persistant.clearDirty();		
////	}
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