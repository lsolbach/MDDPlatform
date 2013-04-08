package org.soulspace.base.domain.tracking.test;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.soulspace.annotation.common.Tracked;
import org.soulspace.base.domain.tracking.AbstractTrackingAspect;

public aspect SysOutTrackingAspect extends AbstractTrackingAspect {

	public void track(JoinPoint jp) {
		if(jp.getSignature() instanceof MethodSignature) {
			MethodSignature mSig = (MethodSignature) jp.getSignature();
			Method m = mSig.getMethod();
			Tracked a = m.getAnnotation(Tracked.class);
			System.out.println("Tracking: Id=" + a.trackingId() + " - " + jp.toString());
		} else {
			System.out.println("Tracking: " + jp.toString());
		}
	}
}
