package org.soulspace.util;

import java.util.Date;

import junit.framework.TestCase;

public class DateUtilsTest extends TestCase {

	Date sooner;
	Date later;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		sooner = new Date();
		Thread.sleep(2);
		later = new Date();
	}

	public void testCompareTo() {
		assertEquals(0, DateUtils.compareTo(null, null));
		assertEquals(0, DateUtils.compareTo(sooner, sooner));
		// TODO check
		assertEquals(-1, DateUtils.compareTo(sooner, null));
		assertEquals(1, DateUtils.compareTo(null, later));
		assertEquals(-1, DateUtils.compareTo(sooner, later));
		assertEquals(1, DateUtils.compareTo(later, sooner));
	}

	public void testCompareFrom() throws InterruptedException {
		assertEquals(0, DateUtils.compareFrom(null, null));
		assertEquals(0, DateUtils.compareFrom(sooner, sooner));
		// TODO check
		assertEquals(1, DateUtils.compareFrom(sooner, null));
		assertEquals(-1, DateUtils.compareFrom(null, later));
		assertEquals(1, DateUtils.compareFrom(sooner, later));
		assertEquals(-1, DateUtils.compareFrom(later, sooner));
	}
}
