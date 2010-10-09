package org.soulspace.jmx.monitoring;

import org.aspectj.lang.Signature;

public interface CallStat {
	Signature getSignature();
	long getCallCount();
	void addCall(long time);
}
