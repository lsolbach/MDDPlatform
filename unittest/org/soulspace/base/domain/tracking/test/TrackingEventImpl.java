package org.soulspace.base.domain.tracking.test;

import org.soulspace.base.domain.tracking.TrackingEvent;

public class TrackingEventImpl implements TrackingEvent {

	String trackingId;

	String operation;
	
	public TrackingEventImpl(String trackingId, String operation) {
		this.trackingId = trackingId;
		this.operation = operation;
	}
	
	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String toString() {
		return "TrackingEventImpl[tracikngId: " + trackingId + ", operation: " + operation + "]";
	}
}
