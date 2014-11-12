package org.soulspace.base.infrastructure.storage.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.soulspace.base.infrastructure.storage.Storage;
import org.soulspace.base.infrastructure.storage.StorageException;

public abstract class AbstractFileStorageImpl implements Storage {

	protected String rootDir = "/tmp/store";
	
	public AbstractFileStorageImpl() {
		File file = new File(rootDir);
		if(!file.exists()) {
			file.mkdirs();
		}		
	}
	
	public AbstractFileStorageImpl(String rootDir) {
		this.rootDir = rootDir;
		File file = new File(rootDir);
		if(!file.exists()) {
			file.mkdirs();
		}		
	}
	
	public void setRepositoryRoot(String rootDir) {
		this.rootDir = rootDir;
		File file = new File(rootDir);
		if(!file.exists()) {
			file.mkdirs();
		}
	}

	protected String getStorageDirName(Class type) {
		return rootDir + File.separator + type.getSimpleName();
	}
	
	public void write(Object o, String filename) {
		try {
			File file = new File(getStorageDirName(o.getClass()) + "/" + filename);
			FileWriter writer = new FileWriter(file);
			writeObject(writer, o);
		} catch (IOException e) {
			throw new StorageException(e);
		}
	}
	
	public void delete(Object o, String filename) {
		File file = new File(getStorageDirName(o.getClass()) + "/" + filename);
		boolean deleted = file.delete();
		if(deleted) {
			System.out.println("entity deleted");
		}
	}	
	
	public <T> List<T> loadList(Class<T> type, FileFilter filter) {
		List<T> list = new ArrayList<T>();
		try {
			File dir = new File(getStorageDirName(type));
			File[] files;
			if(filter != null) {
				files = dir.listFiles(filter);				
			} else {
				files = dir.listFiles();
			}
			for(File file : files) {
				FileReader reader = new FileReader(file);
				Object o = readObject(reader, type);
				if(type.isInstance(o)) {
					// only add of correct type
					list.add(type.cast(o));
				}
			}
			return list;
		} catch (IOException e) {
			throw new StorageException(e);
		}
	}
	
	public <T> List<T> loadList(Class<T> type) {
		return loadList(type, null);
	}
	
	public <T> T load(Class<T> type, String filename) {
		try {
			FileReader reader = new FileReader(filename);
			Object o = readObject(reader, type);
			if(type.isInstance(o)) {
				// check for correct type
				return type.cast(o);									
			}
			return null;
		} catch (FileNotFoundException e) {
			throw new StorageException(e);
		}
	}
	
	public abstract void writeObject(Writer writer,Object o);
	
	public abstract <T> T readObject(Reader reader, Class<T> type);

}
