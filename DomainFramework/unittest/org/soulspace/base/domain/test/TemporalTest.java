package org.soulspace.base.domain.test;

import java.util.Date;

import org.soulspace.base.domain.object.Temporal;

import junit.framework.TestCase;

public class TemporalTest extends TestCase {

	Date sooner;
	Date later;
	
	Temporal t0;
	Temporal t1;
	Temporal t2;
	Temporal t3;
	Temporal t4;
	Temporal t5;
	
	public TemporalTest() {
		sooner = new Date(10);
		later = new Date(20);
		
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
	
	public void testCompareTo() {
		assertEquals(0, later.compareTo(later));
		assertEquals(-1, sooner.compareTo(later));
		assertEquals(1, later.compareTo(sooner));
	}
	
	public void testIntersectsBoth() {
		assertTrue("25/35 intersects both null/null", t2.intersectsBoth(t0));
		assertTrue("25/35 intersects both 20/40", t2.intersectsBoth(t1));
		assertFalse("null/null doesn't intersect both 25/35",  t0.intersectsBoth(t2));
		assertFalse(t1.intersectsBoth(t2));
		assertFalse(t3.intersectsBoth(t2));
		assertFalse(t4.intersectsBoth(t2));
		assertFalse(t5.intersectsBoth(t2));
	}

	public void testIntersectsBegin() {
		assertTrue(t2.intersectsBegin(t3));
		assertTrue(t2.intersectsBegin(t4));
	}

	public void testIntersectsEnd() {
		assertTrue(t2.intersectsEnd(t5));
//		assertTrue(t2.intersectsEnd(t1));
	}

	public void testHides() {
		assertTrue(t0.hides(t2));
		assertTrue(t1.hides(t2));
		assertTrue(t1.hides(t1));
		assertTrue(t0.hides(t4));
		assertTrue(t0.hides(t5));
		assertFalse(t4.hides(t0));
		assertFalse(t5.hides(t0));
	}
		
}
