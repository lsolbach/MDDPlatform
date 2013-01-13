package org.soulspace.base.domain.object;

import java.util.Map;
import java.util.HashMap;

import org.soulspace.aop.util.AspectHelper;
import org.soulspace.base.domain.repository.Repository;

public aspect DirtyTrackingAspect {
	
	/*
	 * Intertype declarations
	 */
	transient Map<String, String> DirtyTrackable.dirtyFieldMap = new HashMap<String, String>(); 
	
	public boolean DirtyTrackable.isDirty() {
		return dirtyFieldMap.size() > 0;
	}

	void DirtyTrackable.markDirty(String field) {
		dirtyFieldMap.put(field, field);
	}

	void DirtyTrackable.clearDirty() {
		dirtyFieldMap = new HashMap<String, String>();
	}
	
	void DirtyTrackable.clearDirty(String field) {
		dirtyFieldMap.remove(field);
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

	pointcut persistentSetField(DirtyTrackable obj) :
		set(!transient * DirtyTrackable+.*)
		&& !within(DirtyTrackingAspect)
		&& this(obj)
		;

	pointcut persistentModification(DirtyTrackable obj) :
		(persistentSetCall(obj)
		|| persistentAddCall(obj)
		|| persistentRemoveCall(obj))
//		|| persistentSetField(obj))
		&& !within(DirtyTrackingAspect)
		;

	after(DirtyTrackable obj) returning : persistentModification(obj) {
		// TODO test for transient/persistent fields
		String field = AspectHelper.getFieldName(thisJoinPoint);
		obj.markDirty(field);
	}

	pointcut repositoryStore(DirtyTrackable obj) :
		execution(* Repository+.store*(DirtyTrackable+))
		&& args(obj)
		;
	
	after(DirtyTrackable obj) returning : repositoryStore(obj) {
		obj.clearDirty();
	}

}
