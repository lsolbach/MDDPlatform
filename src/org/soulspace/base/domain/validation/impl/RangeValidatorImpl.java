package org.soulspace.base.domain.validation.impl;

public class RangeValidatorImpl {

	public static boolean isValid(Object arg, long min, long max) {
		long value = 0;
		if(arg.getClass().isAssignableFrom(Long.class)) {
			value = ((Long) arg).longValue();
			if(value >= min && value <= max) {
				return true;
			} else {
				return false;
			}
		} else {
			// TODO return what?
			return false;
		}
	}

}