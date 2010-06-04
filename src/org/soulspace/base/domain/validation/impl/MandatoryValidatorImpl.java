package org.soulspace.base.domain.validation.impl;

public class MandatoryValidatorImpl {
	public static boolean isValid(Object value) {
		return value != null;
	}
}
