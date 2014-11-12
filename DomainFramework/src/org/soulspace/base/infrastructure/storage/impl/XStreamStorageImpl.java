package org.soulspace.base.infrastructure.storage.impl;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.soulspace.base.infrastructure.storage.XStreamStorage;


import com.thoughtworks.xstream.XStream;

public class XStreamStorageImpl extends AbstractFileStorageImpl implements XStreamStorage {

	protected XStream xstream = new XStream();
	protected Map<Class, String> aliasRegistry = new HashMap<Class, String>();
	protected Map<Class, Class> interfaceRegistry = new HashMap<Class, Class>();
	protected Map<String, Class> nameRegistry = new HashMap<String, Class>();
	protected String extension = ".xml";
	
	public XStreamStorageImpl() {
		File file = new File(rootDir);
		if(!file.exists()) {
			file.mkdirs();
		}		
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public void registerClass(String rName, Class rInterface, Class rClass) {
		interfaceRegistry.put(rClass, rInterface);
		interfaceRegistry.put(rInterface, rInterface);
		aliasRegistry.put(rInterface, rName);
		nameRegistry.put(rName, rInterface);
		xstream.alias(rName, rInterface, rClass);
		// alias intertype fields
		aliasAjcFields(rClass);

		// create subdirs for each entity class except for aggregate childs
		File file = new File(rootDir + File.separator + rInterface.getSimpleName());
		if(!file.exists()) {
			// create subdirs
			file.mkdirs();
		}
	}

	public void aliasAjcFields(Class rClass) {
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
	
	public Class lookupClass(String name) {
		return nameRegistry.get(name);
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

	protected String getStorageDirName(Class type) {
		String interfaceName = interfaceRegistry.get(type).getSimpleName();
		return rootDir + File.separator + interfaceName ;
	}
	
}
