package org.soulspace.base.domain.validation.impl;

public class RangeValidatorImpl {

	public static boolean isValid(long value, long min, long max) {
		if(value >= min && value <= max) {
			return true;
		} else {
			return false;
		}
	}

}