package org.soulspace.base.domain.aggregate;

import java.util.List;

public interface Aggregate {

	List<AggregateChild> getAggregateChild();
	AggregateChild findAggregateChild(String id);

}
