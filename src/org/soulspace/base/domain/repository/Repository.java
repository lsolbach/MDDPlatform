package org.soulspace.base.domain.repository;

import org.soulspace.base.infrastructure.persistence.PersistentStorage;

public interface Repository {
	void registerClasses();
	void setStorage(PersistentStorage storage);
}
