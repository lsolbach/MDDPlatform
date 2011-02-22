package org.soulspace.base.domain.test;

import java.util.Date;

import org.soulspace.base.domain.object.Temporal;
import org.soulspace.util.DateUtils;

import junit.framework.TestCase;

public class TemporalTest extends TestCase {

	Date d0;
	Date d1;
	
	Temporal t0;
	Temporal t1;
	Temporal t2;
	Temporal t3;
	Temporal t4;
	Temporal t5;
	
	public TemporalTest() {
		d0 = new Date(10);
		d1 = new Date(20);
		
		t0 = new TemporalData();
		
		t1 = new TemporalData();
		t1.setValidFrom(new Date(20));
		t1.setValidTo(new Date(40));
		
		t2 = new TemporalData();
		t2.setValidFrom(new Date(25));
		t2.setValidTo(new Date(35));

		t3 = new TemporalData();
		t3.setValidFrom(new Date(30));
		t3.setValidTo(new Date(50));
		
		t4 = new TemporalData();
		t4.setValidFrom(new Date(30));

		t5 = new TemporalData();
		t5.setValidTo(new Date(30));

	}
	
	public void testCompareFrom() {
		assertEquals(0, DateUtils.compareFrom(null, null));
		assertEquals(-1, DateUtils.compareFrom(null, d0));
		assertEquals(1, DateUtils.compareFrom(d0, null));
		assertEquals(0, DateUtils.compareFrom(d1, d1));
		assertEquals(-1, DateUtils.compareFrom(d0, d1));
		assertEquals(1, DateUtils.compareFrom(d1, d0));
	}
	
	public void testCompareTo() {
		assertEquals(0, DateUtils.compareTo(null, null));
		assertEquals(1, DateUtils.compareTo(null, d0));
		assertEquals(-1, DateUtils.compareTo(d0, null));
		assertEquals(0, DateUtils.compareTo(d1, d1));
		assertEquals(-1, DateUtils.compareTo(d0, d1));
		assertEquals(1, DateUtils.compareTo(d1, d0));
	}
	
	public void testIntersectsBoth() {
		System.out.println("testing...");
		assertTrue(t2.intersectsBoth(t0));
		assertTrue(t2.intersectsBoth(t1));
		assertFalse(t0.intersectsBoth(t2));
		assertFalse(t1.intersectsBoth(t2));
		assertFalse(t3.intersectsBoth(t2));
		assertFalse(t4.intersectsBoth(t2));
		assertFalse(t5.intersectsBoth(t2));
	}

	public void testIntersectsBegin() {
		System.out.println("testing...");
		assertTrue(t2.intersectsBegin(t3));
		assertTrue(t2.intersectsBegin(t4));
	}

	public void testIntersectsEnd() {
		System.out.println("testing...");
		assertTrue(t2.intersectsEnd(t5));
//		assertTrue(t2.intersectsEnd(t1));
	}

	public void testHides() {
		System.out.println("testing...");
		assertTrue(t0.hides(t2));
		assertTrue(t1.hides(t2));
		assertTrue(t1.hides(t1));
		assertTrue(t0.hides(t4));
		assertTrue(t0.hides(t5));
		assertFalse(t4.hides(t0));
		assertFalse(t5.hides(t0));
	}
		
}
