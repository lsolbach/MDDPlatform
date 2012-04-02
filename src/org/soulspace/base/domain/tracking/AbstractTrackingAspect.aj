package org.soulspace.base.domain.tracking;

import org.aspectj.lang.JoinPoint;
import org.soulspace.annotation.common.Tracked;

public abstract aspect AbstractTrackingAspect {

	pointcut trackedOperations() : 
		execution(@Tracked * *.*(..))
		;
	
	after() : trackedOperations() {
		track(thisJoinPoint);
	}
	
	abstract void track(JoinPoint joinPoint);
}
