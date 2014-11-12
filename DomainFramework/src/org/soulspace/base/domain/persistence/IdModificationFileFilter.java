package org.soulspace.base.domain.persistence;

import java.io.File;
import java.io.FileFilter;

public class IdModificationFileFilter implements FileFilter {
	String pattern;
	
	public IdModificationFileFilter(String extension, String id, Long modification) {
		if(id != null && modification != null) {
			pattern = id + "-" + modification + "\\." + extension;
		} else if(id != null) {
			pattern = id + "(-\\d+)?" + "\\." + extension;
		} else {
			pattern = ".*\\." + extension;
		}
	}
	
	public boolean accept(File f) {
		if(f.getName().matches(pattern)) {
			return true;
		}
		return false;
	}

}
