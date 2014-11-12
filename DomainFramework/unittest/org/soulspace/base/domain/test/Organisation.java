package org.soulspace.base.domain.test;

import java.util.List;

import org.soulspace.annotation.domain.AggregateRoot;
import org.soulspace.annotation.domain.Entity;

@Entity
@AggregateRoot
public interface Organisation {
	String getName();
	List<EMailDomain> getEMailDomainList();
	void addEMailDomain(EMailDomain domain);
	void removeEMailDomain(EMailDomain domain);
}
