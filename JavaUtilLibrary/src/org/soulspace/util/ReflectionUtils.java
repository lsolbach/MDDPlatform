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
