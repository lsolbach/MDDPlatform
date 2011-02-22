package org.soulspace.base.infrastructure.storage;

import java.io.FileFilter;
import java.util.Date;
import java.util.List;

public interface Storage {

	void write(Object o, String filename);
	void delete(Object o, String filename);
	<T> T load(Class<T> type, String filename);
	
	<T> List<T> loadList(Class<T> type);
	<T> List<T> loadList(Class<T> type, FileFilter filter);

}
