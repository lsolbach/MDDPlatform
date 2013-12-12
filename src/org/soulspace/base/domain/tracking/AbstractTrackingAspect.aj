package org.soulspace.base.domain.tracking;

import org.aspectj.lang.JoinPoint;
import org.soulspace.annotation.common.Tracked;

public abstract aspect AbstractTrackingAspect {

	private TrackingRepository trackingRepository;
	
	public void setTrackingRepository(TrackingRepository trackingRepository) {
		this.trackingRepository = trackingRepository;
	}
	
	public pointcut trackedOperations(String trackingId) : 
		execution(@Tracked * *.*(..))
		&& @annotation(Tracked(trackingId))
		;
	
	after(String trackingId) returning : trackedOperations(trackingId) {
		TrackingEvent trackingEvent = createTrackingEvent(trackingId, thisJoinPoint);
		trackingRepository.addTrackingEvent(trackingEvent);
	}
	
	public abstract TrackingEvent createTrackingEvent(String trackingId, JoinPoint joinPoint);
	
}
