package org.soulspace.jmx.monitoring;

import java.util.Map;

public interface Monitored {
	String printStats();
	void clearStats();
	Map<String, CallStat> getCallStatMap();
}
