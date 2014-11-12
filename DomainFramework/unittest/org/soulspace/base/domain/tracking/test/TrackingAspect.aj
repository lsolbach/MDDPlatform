package org.soulspace.base.domain.tracking.test;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.soulspace.annotation.common.Tracked;
import org.soulspace.base.domain.tracking.AbstractTrackingAspect;
import org.soulspace.base.domain.tracking.TrackingEvent;

public aspect TrackingAspect extends AbstractTrackingAspect {

	public TrackingEvent createTrackingEvent(String trackingId, JoinPoint jp) {
		return new TrackingEventImpl(trackingId, jp.getSignature().toShortString());
	}
}
