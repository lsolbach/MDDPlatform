package org.soulspace.base.domain.persistence.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.soulspace.base.domain.object.Deactivatable;
import org.soulspace.base.domain.object.DomainObject;
import org.soulspace.base.domain.object.Identified;
import org.soulspace.base.domain.object.Modifiable;
import org.soulspace.base.domain.object.Temporal;
import org.soulspace.base.domain.object.Versioned;
import org.soulspace.base.domain.persistence.IdModificationFileFilter;
import org.soulspace.base.domain.persistence.PersistentStorage;
import org.soulspace.base.infrastructure.storage.StorageException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class XStreamPersistentStorageImpl implements PersistentStorage {
	
	private static long MIN_MODIFICATION = 0;
	protected XStream xstream = new XStream(new Dom4JDriver());
	protected String rootDir = "/tmp/repository";
	protected Map<Class<? extends DomainObject>, String> aliasRegistry = new HashMap<Class<? extends DomainObject>, String>();
	protected Map<Class<? extends DomainObject>, Class<? extends DomainObject>> interfaceRegistry = new HashMap<Class<? extends DomainObject>, Class<? extends DomainObject>>();
	protected Map<String, Class<? extends DomainObject>> nameRegistry = new HashMap<String, Class<? extends DomainObject>>();
	
	public XStreamPersistentStorageImpl() {
		File file = new File(rootDir);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
		File file = new File(rootDir);
		if(!file.exists()) {
			file.mkdirs();
		}
	}

	public Class<? extends DomainObject> lookupClass(String name) {
		return nameRegistry.get(name);
	}

	public void registerClass(String rName, Class<? extends DomainObject> rInterface, Class<? extends DomainObject> rClass) {
		interfaceRegistry.put(rClass, rInterface);
		interfaceRegistry.put(rInterface, rInterface);
		aliasRegistry.put(rInterface, rName);
		nameRegistry.put(rName, rInterface);
		xstream.alias(rName, rInterface, rClass);
		// alias intertype fields
		aliasAjcFields(rClass);

		if(DomainObject.class.isAssignableFrom(rClass)) {
			// create subdirs for each entity class except for aggregate childs
			File file = new File(rootDir + File.separator + rInterface.getSimpleName());
			if(!file.exists()) {
				// create subdirs
				file.mkdirs();
			}
		}
	}
	
	public void aliasAjcFields(Class<? extends DomainObject> rClass) {
		String alias;
		Field[] fields = rClass.getFields();
		for(Field field : fields) {
			if(!Modifier.isTransient(field.getModifiers())
					&& field.getName().startsWith("ajc$")) {
				// add alias for not transient intertype fields
				alias = field.getName().substring(field.getName().lastIndexOf("$") + 1);
				xstream.aliasField(alias, rClass, field.getName());
			}
		}
	}
	
	public void write(DomainObject persistent) {
		FileWriter writer = null;
		try {
			File file = new File(getStorageDirName(persistent.getClass()) + "/" + getPrimaryKey(persistent) + ".xml");
			writer = new FileWriter(file);
			xstream.toXML(persistent, writer);
		} catch (IOException e) {
			throw new StorageException(e);
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// FIXME log
				}
			}
		}
	}
	
	public void delete(DomainObject persistent) {
		File file = new File(getStorageDirName(persistent.getClass()) + "/" + getPrimaryKey(persistent) + ".xml");
		boolean deleted = file.delete();
		if(deleted) {
			System.out.println("entity deleted");
		}
	}
	
	public <T extends DomainObject> List<T> loadList(Class<T> type, String id, Long modification) {
		List<T> list = new ArrayList<T>();
		FileReader reader = null;
		try {
			File dir = new File(getStorageDirName(type));
			File[] files = dir.listFiles(new IdModificationFileFilter("xml", id, modification));
			for(File file : files) {
				reader = new FileReader(file);
				Object o = xstream.fromXML(reader);
				if(type.isInstance(o)) {
					// only add of correct type
					list.add(type.cast(o));									
				}
			}
			return Collections.synchronizedList(list);
		} catch (IOException e) {
			throw new StorageException(e);
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// FIXME log
				}
			}
		}
	}

	public <T extends DomainObject> List<T> loadList(Class<T> type, Date forTime, boolean validOnly) {
		List<T> list = loadList(type, null, null);
		// filter list
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			T t = it.next();
			if(validOnly) {
				if(forTime != null && t instanceof Temporal) {
					if(!((Temporal) t).isValid(forTime)) {
						it.remove();
					}
				} else if(t instanceof Deactivatable && ((Deactivatable) t).getDeactivated()) {
					it.remove();
				}				
			}
		}
		return list;
	}

	public <T extends DomainObject> List<T> loadList(Class<T> type, Date atTime, Date forTime, boolean validOnly) {
		List<T> list = loadList(type, null, null);
		// filter list
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			T t = it.next();
			boolean isRemoved = false; // don't try to remove element twice
			if(validOnly && t instanceof Deactivatable) {
				if(((Deactivatable) t).getDeactivated()) {
					it.remove();
					isRemoved = true;
				}
			}
			if(t instanceof Temporal) {
				if(forTime != null && !((Temporal) t).isEffective(forTime) && !isRemoved) {
					it.remove();
					isRemoved = true;
				}
				if(atTime != null && !((Temporal) t).isKnown(atTime) && !isRemoved) {
					it.remove();						
					isRemoved = true;
				}
			}				
		}
		return list;
	}

	public <T extends DomainObject> List<T> loadList(Class<T> type, String id, boolean validOnly) {
		List<T> list = loadList(type, id, null);
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			T t = it.next();
			if(validOnly && t instanceof Deactivatable && ((Deactivatable) t).getDeactivated()) {
				it.remove();
			}
		}
		return list;
	}

	public <T extends DomainObject> List<T> loadList(Class<T> type) {
		return loadList(type, null, null);
	}
	
	public <T extends DomainObject> List<T> loadList(Class<T> type, Date forDate) {
		return loadList(type, forDate, true);
	}
		
	public <T extends DomainObject> List<T> loadList(Class<T> type, boolean validOnly) {
		return loadList(type, new Date(), validOnly);
	}
	
	public <T extends DomainObject> List<T> loadList(Class<T> type, String id) {
		return loadList(type, id, true);
	}

	public <T extends DomainObject> T load(Class<T> type, String id) {
		T result = null;
		List<T> list = loadList(type, id, null);
		// return object with highest modification number
		long maxModification = MIN_MODIFICATION;
		for (T o : list) {
			if(o instanceof Deactivatable) {
				if(((Deactivatable) o).getDeactivated()) {
					break;
				}
			}
			if(o instanceof Modifiable) {
				Modifiable m = (Modifiable) o;
				if(m.getModification() >= maxModification) {
					result = o;
					maxModification = m.getModification();
				}				
			} else {
				result = o;
			}
		}
		return result;
	}

	public <T extends DomainObject> T load(Class<T> type, String id, Date forDate) {
		T result = null;
		List<T> list = loadList(type, id, null);
		for (T o : list) {
			if(o instanceof Temporal) {
				Temporal te = (Temporal) o;
				if(forDate == null || te.isValid(forDate)) {
					result = o;
				}
			} else if(o instanceof Deactivatable && !((Deactivatable) o).getDeactivated()) {
				result = o;
			} else {
				result = o;
			}
		}
		return result;
	}
	
	
	public <T extends DomainObject & Modifiable> T load(Class<T> type, String id, long modification) {
		T result = null;
		List<T> list = loadList(type, id, modification);
		for(T t : list) {
			if(t.getModification() == modification) {
				result = t;
			}
		}
		return result;
	}
	
	public <T extends DomainObject & Modifiable> T load(Class<T> type, String id, Date atTime,
			Date forTime, Long modification, boolean validOnly) {
		if(id == null || id.equals("")) {
			throw new StorageException("Id not set");
		}
		T result = null;
		List<T> list = loadList(type, id, modification);
		// filter list according to the parameters
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			T t = it.next();
			boolean isRemoved = false; // don't try to remove element twice
			if(validOnly && t instanceof Deactivatable) {
				if(((Deactivatable) t).getDeactivated()) {
					it.remove();
					isRemoved = true;
				}
			}
			if(t instanceof Temporal) {
				if(forTime != null && !((Temporal) t).isEffective(forTime) && !isRemoved) {
					it.remove();
					isRemoved = true;
				}
				if(atTime != null && !((Temporal) t).isKnown(atTime) && !isRemoved) {
					it.remove();						
					isRemoved = true;
				}
			}				
		}
		// of all remaining return the one with the highest modification
		long maxModification = MIN_MODIFICATION;
		for (T t : list) {
			if(t.getModification() >= maxModification) {
				result = t;
				maxModification = t.getModification();
			}
		}
		
		return result;
	}

	protected String getPrimaryKey(DomainObject persistent) {
		String key = "";
		if(persistent instanceof Identified) {
			key = ((Identified) persistent).getId();
		}
		if(persistent instanceof Modifiable && persistent instanceof Versioned) {
			key = key + "-" + ((Modifiable) persistent).getModification();
		}
		return key;
	}
	
	String getAliasForType(Class<? extends DomainObject> type) {
		if(type.isInterface()) {
			return aliasRegistry.get(type.getName());
		} else {
			Class<? extends DomainObject> rInterface = interfaceRegistry.get(type);
			return aliasRegistry.get(rInterface);
		}
	}
	
	String getStorageDirName(Class<? extends DomainObject> type) {
		String interfaceName = interfaceRegistry.get(type).getSimpleName();
		return rootDir + File.separator + interfaceName;
	}

	String getStorageDirName(DomainObject persistent) {
		String interfaceName = interfaceRegistry.get(persistent.getClass()).getSimpleName();
		return rootDir + File.separator + interfaceName;
	}

	public void writeObject(Writer writer, Object o) {
		xstream.toXML(o, writer);		
	}
	
	public <T> T readObject(Reader reader, Class<T> type) {
		Object o = xstream.fromXML(reader);
		if(type.isInstance(o)) {
			return type.cast(o);
		}
		return null;
	}
}
