package org.soulspace.base.domain.test;

import org.soulspace.annotation.domain.Entity;
import org.soulspace.annotation.infrastructure.Persistent;

@Entity
@Persistent
public class ApplicationImpl implements Application {

	String name;
	
	public ApplicationImpl(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
