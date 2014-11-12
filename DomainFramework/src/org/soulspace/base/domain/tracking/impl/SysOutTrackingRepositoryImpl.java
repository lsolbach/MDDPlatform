package org.soulspace.base.domain.tracking.impl;

import org.soulspace.base.domain.tracking.TrackingEvent;
import org.soulspace.base.domain.tracking.TrackingRepository;

public class SysOutTrackingRepositoryImpl implements TrackingRepository {

	@Override
	public void addTrackingEvent(TrackingEvent trackingEvent) {
		System.out.println("Tracked " + trackingEvent.toString());
	}
}
