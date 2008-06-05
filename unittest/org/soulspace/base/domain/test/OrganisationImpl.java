package org.soulspace.base.domain.test;

import java.util.ArrayList;
import java.util.List;

import org.soulspace.annotation.domain.Entity;

@Entity
public class OrganisationImpl implements Organisation {

	String name;
	List<EMailDomain> eMailDomainList = new ArrayList<EMailDomain>();
	
	public OrganisationImpl(String name) {
		this.name = name;
	}
	
	public List<EMailDomain> getEMailDomainList() {
		return eMailDomainList;
	}

	public String getName() {
		return name;
	}

	public void addEMailDomain(EMailDomain domain) {
		eMailDomainList.add(domain);
	}

	public void removeEMailDomain(EMailDomain domain) {
		eMailDomainList.remove(domain);
	}

}
