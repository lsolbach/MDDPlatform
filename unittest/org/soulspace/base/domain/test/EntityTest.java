package org.soulspace.base.domain.test;

import org.soulspace.base.domain.validation.ValidationResult;
import org.soulspace.base.domain.validation.impl.ValidatorImpl;

import junit.framework.TestCase;

public class EntityTest extends TestCase {

	public void testEntityCreation() {
		ApplicationImpl app1 = new ApplicationImpl("App1");
		System.out.println(app1.getId());
	}
	
	public void testValidation() {
		ValidatorImpl validator = new ValidatorImpl();
		ApplicationImpl app1 = new ApplicationImpl(null);
		ValidationResult vr = validator.validate(app1);
		assertTrue(!vr.isValid());
	}
}
