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
