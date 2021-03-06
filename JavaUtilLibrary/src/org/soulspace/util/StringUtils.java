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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringUtils {

	/**
	 * Test if string is not null and not empty.
	 * @param str
	 * @return
	 */
	public static boolean isSet(String str) {
		return str != null && !str.equals("");
	}
  
	/**
	 * Returns the string representation of an object or an empty string for null.
	 * @param o
	 * @return
	 */
	public static String toString(Object o) {
		return (o != null) ? o.toString() : "";
	}
	
	public static String loadStringFromFile(String fileName) throws IOException {
		StringBuilder buffer = new StringBuilder(128);
		String line = null;

		File file = new File(fileName);
		System.out.println(file.getAbsolutePath());

		BufferedReader in = new BufferedReader(new FileReader(file));
		while ((line = in.readLine()) != null) {
			buffer.append(line + "\n");
		}
		in.close();
		return buffer.toString();
	}

	public static String loadStringFromStream(InputStream is)
			throws IOException {
		StringBuilder buffer = new StringBuilder(128);
		String line = null;

		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		while ((line = in.readLine()) != null) {
			buffer.append(line + "\n");
		}
		in.close();
		return buffer.toString();
	}

}
