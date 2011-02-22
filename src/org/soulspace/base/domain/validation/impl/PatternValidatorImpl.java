package org.soulspace.base.domain.validation.impl;

public class PatternValidatorImpl {

	public static boolean isValid(Object value, String pattern) {
		return value.toString().matches(pattern);
	}

}
