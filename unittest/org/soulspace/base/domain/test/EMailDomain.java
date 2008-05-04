package org.soulspace.base.domain.test;

import org.soulspace.annotation.domain.AggregateChild;
import org.soulspace.annotation.domain.Entity;

@Entity
@AggregateChild
public interface EMailDomain {
	String getDomainName();
}
