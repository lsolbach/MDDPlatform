package org.soulspace.base.domain.test;

import junit.framework.TestCase;

public class EntityTest extends TestCase {

	public void testEntityCreation() {
		ApplicationImpl app1 = new ApplicationImpl("App1");
		System.out.println(app1.getId());
	}
}
