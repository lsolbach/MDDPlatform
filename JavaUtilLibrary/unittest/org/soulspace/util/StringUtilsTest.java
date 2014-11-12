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

import junit.framework.TestCase;

public class StringUtilsTest extends TestCase {

	public void testIsSet() {
		assertTrue(StringUtils.isSet("Hallo"));
		assertFalse(StringUtils.isSet(""));
		assertFalse(StringUtils.isSet(null));
	}

	public void testToStringObject() {
		assertEquals("", StringUtils.toString(null));
		assertEquals("Hallo", StringUtils.toString("Hallo"));
	}

}
