package org.soulspace.util;

public class FileUtils {

	public static String getSuffix(String filename) {
		if(filename.lastIndexOf(".") != -1) {
			return filename.substring(filename.lastIndexOf(".") + 1);			
		}
		return "";
	}
	
	public static String getBase(String filename) {
		String name = filename;
		if(name.lastIndexOf("/") != -1) {
			name = name.substring(name.lastIndexOf("/"));			
		}
		if(name.lastIndexOf(".") != -1) {
			name = name.substring(0, name.lastIndexOf("."));			
		}
		return name;
	}

}
