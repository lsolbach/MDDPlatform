package org.soulspace.util;

import java.lang.reflect.Field;

public class ReflectionUtils {

	/**
	 * Returns the value of the field of the given object
	 * @param o object
	 * @param f field
	 * @return value
	 * @throws RuntimeException
	 */
	public static Object getFieldValue(Object o, Field f) {
		try {
			return f.get(o);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
