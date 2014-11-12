/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
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
