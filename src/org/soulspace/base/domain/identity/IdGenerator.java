package org.soulspace.base.domain.identity;

public interface IdGenerator {
	String getId();
	String getId(String sequence);
//	String getId(Identified idObject);
}
