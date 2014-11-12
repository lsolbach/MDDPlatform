package org.soulspace.base.domain.identity;

public interface Sequence {
	
	String getName();
	void setName(String name);
	
	long getNextValue();
	void setNextValue(long nextValue);
	
}
