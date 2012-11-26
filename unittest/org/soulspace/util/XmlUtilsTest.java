package org.soulspace.util;

import junit.framework.TestCase;

public class XmlUtilsTest extends TestCase {

	public void testEncode() {
		assertEquals("", XmlUtils.encode(""));
		assertEquals("&lt;", XmlUtils.encode("<"));
		assertEquals("&gt;", XmlUtils.encode(">"));
		assertEquals("&quot;", XmlUtils.encode("\""));
		assertEquals("&apos;", XmlUtils.encode("'"));
		assertEquals("&amp;", XmlUtils.encode("&"));

		assertEquals("ä", XmlUtils.encode("ä"));
	}

	public void testDecode() {
		assertEquals("", XmlUtils.decode(""));
		assertEquals("<", XmlUtils.decode("&lt;"));
		assertEquals(">", XmlUtils.decode("&gt;"));
		assertEquals("\"", XmlUtils.decode("&quot;"));
		assertEquals("'", XmlUtils.decode("&apos;"));
		assertEquals("&", XmlUtils.decode("&amp;"));

		assertEquals("ä", XmlUtils.encode("ä"));
	}

}
