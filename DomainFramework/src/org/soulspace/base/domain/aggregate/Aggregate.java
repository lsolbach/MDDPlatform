package org.soulspace.base.domain.aggregate;

import java.util.List;

public interface Aggregate {

	List<AggregateChild> getAggregateChild();
	void addAggregateChild(AggregateChild child);
	void removeAggregateChild(AggregateChild child);
}
