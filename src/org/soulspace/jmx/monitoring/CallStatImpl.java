package org.soulspace.jmx.monitoring;

import org.aspectj.lang.Signature;

public class CallStatImpl implements CallStat {
	Signature signature;
	long callCount;
	long maxDuration = 0;
	long minDuration = Long.MAX_VALUE;
	long sumDuration = 0;
	
	protected CallStatImpl(Signature signature) {
		this.signature = signature;
	}
	
	public void addCall(long duration) {
		callCount++;
		sumDuration = sumDuration + duration;
		if(duration < minDuration) {
			minDuration = duration;
		}
		if(duration > maxDuration) {
			maxDuration = duration;
		}
	}

	public long getCallCount() {
		return callCount;
	}
	
	public Signature getSignature() {
		return signature;
	}

	public String toString() {
		long avgDuration = 0;
		if(callCount > 0) {
			avgDuration = sumDuration / callCount;
		}
		StringBuilder sb = new StringBuilder(64);
		sb.append(callCount);
		sb.append("\t");
		sb.append(sumDuration);
		sb.append("\t");
		sb.append(avgDuration);
		sb.append("\t");
		sb.append(minDuration);
		sb.append("\t");
		sb.append(maxDuration);
		sb.append("\t");
		sb.append(signature.toShortString());
		
		return sb.toString();
	}
}
