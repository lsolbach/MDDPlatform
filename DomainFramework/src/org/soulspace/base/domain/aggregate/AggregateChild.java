package org.soulspace.base.domain.aggregate;


/**
 * 
 * @author soulman
 *
 */
public interface AggregateChild extends Aggregate {

	AggregateRoot getAggregateRoot();
	
}
