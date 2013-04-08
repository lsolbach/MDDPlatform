package org.soulspace.base.domain.tracking;

import org.aspectj.lang.JoinPoint;
import org.soulspace.annotation.common.Tracked;

public abstract aspect AbstractTrackingAspect {

	public pointcut trackedOperations() : 
		execution(@Tracked * *.*(..))
		;
	
	after() returning : trackedOperations() {
		track(thisJoinPoint);
	}
	
	public abstract void track(JoinPoint joinPoint);
}
