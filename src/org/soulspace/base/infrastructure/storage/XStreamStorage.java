package org.soulspace.base.infrastructure.storage;

public interface XStreamStorage extends Storage {
	void registerClass(String rName, Class rInterface, Class rClass);
}
