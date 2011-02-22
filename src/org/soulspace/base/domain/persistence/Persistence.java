package org.soulspace.base.domain.persistence;

public interface Persistence {
	
	void registerClasses();
	void setStorage(PersistentStorage storage);

}
