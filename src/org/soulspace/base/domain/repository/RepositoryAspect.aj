package org.soulspace.base.domain.repository;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.soulspace.base.domain.entity.Entity;
import org.soulspace.base.domain.validation.Validateable;
import org.soulspace.base.domain.validation.ValidationException;
import org.soulspace.base.domain.validation.ValidationResult;
import org.soulspace.base.infrastructure.persistence.PersistentStorage;

/**
 * The RepositoryAspect handles the persistance of Entity objects
 * 
 */
privileged public aspect RepositoryAspect {
	// FIXME convert StorageException to RepositoryException

	Logger log = Logger.getLogger(RepositoryAspect.class.getName());
	
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

	pointcut getById(String id) :
		execution(* Repository+.get*(String, ..))
		&& args(id)
		;

	pointcut storeValidateable(Validateable v) :
		execution(* Repository+.store*(Validateable+))
		&& args(v);
		;
	
	before(Validateable v) : storeValidateable(v) {
		ValidationResult vResult = v.validate();
		if(!vResult.isValid()) {
			// FIXME check severity
			throw new ValidationException("Object invalid!", vResult);
		}
	}
	
	after(String id) returning (Object o) : getById(id) {
		if(o == null) {
			throw new RepositoryException("Object with id " + id + " not found!");
		}
	}
}