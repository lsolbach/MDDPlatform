package org.soulspace.base.domain.persistence;

import java.util.Date;
import java.util.List;

import org.soulspace.base.domain.object.DomainObject;
import org.soulspace.base.domain.object.Identified;
import org.soulspace.base.domain.object.Modifiable;
import org.soulspace.base.domain.object.Temporal;

import com.sun.corba.se.spi.ior.Identifiable;

public interface PersistentStorage {

	/**
	 * 
	 * @param alias
	 * @param rInterface
	 * @param rClass
	 */
	void registerClass(String name, Class<? extends DomainObject> rInterface, Class<? extends DomainObject> rClass);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	Class<? extends DomainObject> lookupClass(String name);
	
	void write(DomainObject persistent);

	void delete(DomainObject persistent);

	<T extends DomainObject & Identified> List<T> loadList(Class<T> type);

	<T extends DomainObject & Identified> List<T> loadList(Class<T> type, String id);

	<T extends DomainObject & Identified> List<T> loadList(Class<T> type, Date effective);

	<T extends DomainObject & Identified> List<T> loadList(Class<T> type, boolean validOnly);

	<T extends DomainObject & Identified> List<T> loadList(Class<T> type, Date forTime, boolean validOnly);

	<T extends DomainObject & Identified> List<T> loadList(Class<T> type, Date atTime, Date forTime, boolean validOnly);

	<T extends DomainObject & Identified> List<T> loadList(Class<T> type, String id, boolean validOnly);

	<T extends DomainObject & Identified> T load(Class<T> type, String id);

	<T extends DomainObject & Identified & Modifiable> T load(Class<T> type, String id, long modification);

	<T extends DomainObject & Identified> T load(Class<T> type, String id, Date accessTime);

	<T extends DomainObject & Identified & Modifiable> T load(Class<T> type, String id, Date atTime, Date forTime, Long modification, boolean validOnly);
		
}
