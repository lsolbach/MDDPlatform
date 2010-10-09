package org.soulspace.jmx.monitoring;

import java.util.List;

public interface CallMonitorMBean {
	List<String> getStatistics();
	void clearStatistics();
}
