package org.soulspace.base.domain.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.soulspace.base.domain.object.Deactivatable;
import org.soulspace.base.domain.object.DirtyTrackable;
import org.soulspace.base.domain.object.DomainObject;
import org.soulspace.base.domain.object.Entity;
import org.soulspace.base.domain.object.IdentifiedAspect;
import org.soulspace.base.domain.object.Modifiable;
import org.soulspace.base.domain.object.Revisionable;
import org.soulspace.base.domain.object.Temporal;
import org.soulspace.base.domain.object.Value;
import org.soulspace.base.infrastructure.storage.StorageException;

public aspect PersistenceAspect {

	// FIXME check if needed
	declare precedence : PersistenceAspect, IdentifiedAspect;

	Logger log = Logger.getLogger("Persistence");

	declare parents : (@org.soulspace.annotation.domain.Repository *) implements Persistence;
	
	//
	// intertype declarations
	//
	public PersistentStorage Persistence.storage;
	
	public void Persistence.setStorage(PersistentStorage storage) {
		this.storage = storage;
	}
	
	PersistentStorage Persistence.getStorage() {
		return storage;
	}
	
	pointcut persistentCreation() :
		call((DomainObject+).new(..))
		;

	pointcut store(PersistentStorage storage, DomainObject persistent) :
		call(* PersistentStorage+.write(DomainObject+))
		&& target(storage)
		&& args(persistent)
		&& !within(PersistenceAspect)
		;

	void around(PersistentStorage storage, DomainObject persistent) : store(storage, persistent) {
		Date modificationTime = new Date();
		boolean isNew = false;
		boolean isDirty = true;

		// check, if entity has to be stored
		if(!(persistent instanceof Modifiable)) {
			// not modifiable
			return;
		} else {
			isNew = ((Modifiable) persistent).isNew();
			if(persistent instanceof DirtyTrackable) {
				isDirty = ((DirtyTrackable) persistent).isDirty();
			}
		}
		if (!isNew && !isDirty) {
			// storing not needed
			log.debug("Storing skipped, entity not dirty");
			return;
		}
		
		if(persistent instanceof Entity) {
			Entity entity = (Entity) persistent;
			synchronized (storage) {
				if (isNew) {
					// new entity, create first modification
					newModification(persistent, modificationTime);
					// TODO restore modification time and version, if store fails?
					storage.write(persistent);
				} else if (isDirty) {
					// dirty, must be stored
					// optimistic locking, get stored entity
					Entity stored = (Entity) storage.load(persistent.getClass(), entity.getId());
					if (stored == null || !persistent.getClass().isInstance(stored)) {
						throw new StorageException("Couldn't load stored version of Object " + persistent);
					}
					if(isModified(entity, stored)) {
						throw new StorageException("Object was modified, this modification is "
								+ entity.getModification()
								+ ", stored modification is " + stored.getModification());
					}

					if (persistent instanceof Temporal) {
						writeTemporal(storage, (Temporal) persistent,
								modificationTime);
					} else {
						writePersistent(storage, entity, modificationTime);
					}
				}
			}
		} else if(persistent instanceof Value) {
			
		}		
	}

	boolean isPersisted(DomainObject p) {
		if(p instanceof Modifiable) {
			return !((Modifiable) p).isNew();
		} else {
			return false;
		}
	}
	
	void newModification(DomainObject persistent, Date timeStamp) {
		// FIXME move to modifiable and revisionable, mind the precedence
		if(persistent instanceof Modifiable) {
			Modifiable m = (Modifiable) persistent;
			if(m instanceof Revisionable) {
				Revisionable r = (Revisionable) m; 
				if(m.isNew()) {
					// first storage
					r.setCreatedAt(timeStamp);
				}
				r.setModifiedAt(timeStamp);
			}
			m.incrementModification();
		}
	}
	
	void deactivate(DomainObject persistent, Date timeStamp) {
		if(persistent instanceof Deactivatable) {
			Deactivatable d = (Deactivatable) persistent;
			d.deactivate();
		}
		if(persistent instanceof Temporal) {
			Temporal t = (Temporal) persistent;
			t.setInvalidatedAt(timeStamp);
		}
	}

	boolean isModified(Entity entity, Entity stored) {
		// optimistic locking, compare modification for up to date check
		if (stored.getModification() != entity.getModification()) {
			return true;
		}
		return false;
	}

	/**
	 * Write an entity.
	 * 
	 * @param storage
	 * @param entity
	 * @param modificationTime
	 */
	void writePersistent(PersistentStorage storage, Entity persistent,
			Date modificationTime) {
		// from optimistic locking we know storedEntity exists
		// create new modification
		if (true /* TODO is DomainObject */) {
			Entity stored = storage.load(persistent.getClass(), persistent
					.getId());
			if(persistent instanceof Deactivatable) {
				((Deactivatable) stored).setDeactivated(true);				
			}
			storage.write(stored);
		}
		newModification(persistent, modificationTime);

		// TODO transaction: restore modification time and version, if store
		// fails?
		storage.write(persistent);
		// persistent.clearDirty();
	}

	/**
	 * Write a temporal entity. Check for temporal intersections and generate
	 * new temporal sliced entities accordingly.
	 * 
	 * @param storage
	 * @param entity
	 * @param modificationTime
	 */
	<T extends DomainObject & Temporal> void writeTemporal(PersistentStorage storage,
			T persistent, Date modificationTime) {
		Temporal te = (Temporal) persistent;
		List<T> list = (List<T>) storage.loadList(persistent.getClass(),
				persistent.getId());
		List<T> temporals = (List<T>) filterTemporals(list, true);

		for (Temporal t : temporals) {
			if (te.intersectsBoth(t)) {
				// intersected
				log.debug("intersects begin and end");
				Temporal newT1 = storage.load(t.getClass(), t.getId(), t
						.getModification());
				Temporal newT2 = storage.load(t.getClass(), t.getId(), t
						.getModification());

				newModification(newT1, modificationTime);
				newT1.setValidTo(te.getValidFrom());
				storage.write(newT1);

				newModification(newT2, modificationTime);
				newT2.setValidFrom(te.getValidTo());
				storage.write(newT2);

				t.setInvalidatedAt(modificationTime);
				storage.write(t);
			} else if (te.intersectsBegin(t)) {
				// intersected
				log.debug("intersects begin");

				Temporal newT = storage.load(t.getClass(), t.getId(), t
						.getModification());
				newModification(newT, modificationTime);
				newT.setValidFrom(te.getValidTo());
				storage.write(newT);

				t.setInvalidatedAt(modificationTime);
				storage.write(t);
			} else if (te.intersectsEnd(t)) {
				// intersected
				log.debug("intersects end");

				Temporal newT = storage.load(t.getClass(), t.getId(), t
						.getModification());
				newModification(newT, modificationTime);
				newT.setValidTo(te.getValidFrom());
				storage.write(newT);

				t.setInvalidatedAt(modificationTime);
				storage.write(t);
			} else if (te.hides(t)) {
				// hidden
				log.debug("not intersected but hidden");
				t.setInvalidatedAt(modificationTime);
				storage.write(t);
			}
		}
		newModification(persistent, modificationTime);
		storage.write(persistent);
		// persistent.clearDirty();
	}

	<T extends Temporal> List<T> filterTemporals(List<T> list, boolean onlyValid) {
		List<T> temporalList = new ArrayList<T>();
		for (T t : list) {
			if (onlyValid) {
				if (!t.isDeactivated()) {
					temporalList.add(t);
				}
			} else {
				temporalList.add(t);
			}
		}
		Collections.sort(temporalList, new Temporal.TemporalComparator());
		return temporalList;
	}
	
}
