package org.soulspace.base.domain.validation.impl;

public class PatternValidatorImpl {

	public static boolean isValid(String value, String pattern) {
		if(value != null) {
			return value.matches(pattern);
		} else {
			return true;
		}
	}

}
