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

}