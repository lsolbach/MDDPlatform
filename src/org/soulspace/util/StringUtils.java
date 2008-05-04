package org.soulspace.util;

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
}
