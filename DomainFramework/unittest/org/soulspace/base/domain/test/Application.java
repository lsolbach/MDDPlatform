package org.soulspace.base.domain.test;

import org.soulspace.annotation.domain.Entity;

@Entity
public interface Application {
	String getName();
	void setName(String name);
}
