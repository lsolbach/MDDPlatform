package org.soulspace.base.domain.identity;

public interface IdGenerator {
	String getId();
	String getId(String type);
	//String getId(Identified object);
}
