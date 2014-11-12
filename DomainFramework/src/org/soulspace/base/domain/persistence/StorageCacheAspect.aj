package org.soulspace.base.domain.persistence;

import java.util.Map;
import java.util.WeakHashMap;

import org.soulspace.base.domain.object.DomainObject;

public aspect StorageCacheAspect {

	Map<String, Object> PersistentStorage.cache = new WeakHashMap<String, Object>();

	pointcut loadPersistent(Class<? extends DomainObject> type, String id) :
		execution(* PersistentStorage+.load(Class, String))
		&& args(type, id)
		;
	
	pointcut storePersistent(DomainObject persistent) :
		execution(void PersistentStorage+.write(DomainObject+))
		&& args(persistent)
		;
	
	pointcut removePersistent(DomainObject persistent) :
		execution(void PersistentStorage+.delete(DomainObject+))
		&& args(persistent)
		;
	
	Object around(Class<? extends DomainObject> type, String id) : loadPersistent(type, id) {
		return proceed(type, id);
	}
	
	void around(DomainObject persistent) : storePersistent(persistent) {
		proceed(persistent);
	}
	
	void around(DomainObject persistent) : removePersistent(persistent) {
		proceed(persistent);
	}
}
