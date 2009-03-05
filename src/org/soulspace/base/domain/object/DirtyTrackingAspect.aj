package org.soulspace.base.domain.object;

import java.util.Map;
import java.util.HashMap;

import org.soulspace.base.domain.entity.Entity;
import org.soulspace.base.domain.repository.Repository;
import org.soulspace.base.util.AspectUtils;

public aspect DirtyTrackingAspect {
	
	/*
	 * Intertype declarations
	 */
	transient boolean DirtyTrackable.dirty = false;
	transient Map<String, String> DirtyTrackable.fieldMap = new HashMap<String, String>(); 
	
	public boolean DirtyTrackable.isDirty() {
		return dirty;
	}

	void DirtyTrackable.markDirty(String field) {
		dirty = true;
		fieldMap.put(field, field);
	}

	void DirtyTrackable.clearDirty() {
		dirty = false;
		fieldMap = new HashMap<String, String>();
	}
	
	void DirtyTrackable.clearDirty(String field) {
		fieldMap.remove(field);
		if(fieldMap.size() == 0) {
			dirty = false;
		}
	}
	
	/*
	 * Poincuts
	 */
	
	pointcut persistentSetCall(DirtyTrackable obj) :
		execution(* DirtyTrackable+.set*(..))
		&& !within(DirtyTrackingAspect)
		&& target(obj)
		;

	pointcut persistentAddCall(DirtyTrackable obj) :
		execution(* DirtyTrackable+.add*(..))
		&& !within(DirtyTrackingAspect)
		&& target(obj)
		;

	pointcut persistentRemoveCall(DirtyTrackable obj) :
		execution(* DirtyTrackable+.remove*(..))
		&& !within(DirtyTrackingAspect)
		&& target(obj)
		;

//	pointcut persistentSetField(DirtyTrackable obj) :
//		set(!transient * DirtyTrackable+.*)
//		&& !within(DirtyTrackingAspect)
//		&& this(obj)
//		;

	pointcut persistentModification(DirtyTrackable obj) :
		(persistentSetCall(obj)
		|| persistentAddCall(obj)
		|| persistentRemoveCall(obj))
//		|| persistentSetField(obj))
//		&& !within(DirtyTrackingAspect)
		;

	after(DirtyTrackable obj) returning : persistentModification(obj) {
		String field = AspectUtils.getNameFromJoinPoint(thisJoinPoint);
		obj.markDirty(field);
	}


	pointcut repositoryStore(Repository repository, Entity entity) :
		execution(* Repository+.store*(Entity+))
		&& this(repository)
		&& args(entity)
		;
	
//	pointcut remove(Repository repository, Entity entity) :
//		execution(* Repository+.remove*(Entity+))
//		&& this(repository)
//		&& args(entity)
//		;

	
}
