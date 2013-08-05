/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.aop.util;

import junit.framework.TestCase;

public class AspectHelperTest extends TestCase {

	protected void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
	}

	public void testRenderJoinPoint() {
		//fail("Not yet implemented");
	}

	public void testRenderJoinPointWithArgs() {
		//fail("Not yet implemented");
	}

	public void testRenderArgs() {
		//fail("Not yet implemented");
	}

	public void testRenderArg() {
		//fail("Not yet implemented");
	}

	public void testGetFieldName() {
		//fail("Not yet implemented");
	}

	public void testGetFieldNameFromMethodName() {
		assertEquals("name", AspectHelper.getFieldNameFromMethodName("getName"));
		assertEquals("name", AspectHelper.getFieldNameFromMethodName("setName"));
		//assertEquals("name", AspectHelper.getFieldNameFromMethodName("addName"));
		//assertEquals("name", AspectHelper.getFieldNameFromMethodName("removeName"));
		assertEquals("nameList", AspectHelper.getFieldNameFromMethodName("getNameList"));
		assertEquals("nameSet", AspectHelper.getFieldNameFromMethodName("getNameSet"));
	}

	public void testGetGetterName() {
		assertEquals("getName", AspectHelper.getGetMethodName("name"));
	}

	public void testGetSetterName() {
		assertEquals("setName", AspectHelper.getSetMethodName("name"));
	}

	public void testGetAdderName() {
		assertEquals("addName", AspectHelper.getAddMethodName("nameList"));
	}

	public void testGetRemoveName() {
		assertEquals("removeName", AspectHelper.getRemoveMethodName("nameList"));
	}

	public void testFirstLower() {
		assertEquals("name", AspectHelper.firstLower("Name"));
	}

	public void testFirstUpper() {
		assertEquals("Name", AspectHelper.firstUpper("name"));
	}

}
