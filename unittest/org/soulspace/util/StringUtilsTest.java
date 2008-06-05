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
