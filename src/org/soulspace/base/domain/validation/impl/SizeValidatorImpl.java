package org.soulspace.base.domain.validation.impl;

import java.util.Collection;
import java.util.Map;

public class SizeValidatorImpl {

	public static boolean isValid(String value, int min, int max) {
		if(value == null) {
			return true;
		}
		if(value.length() >= min && value.length() <= max) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValid(Object[] values, int min, int max) {
		if(values == null) {
			return true;
		}
		if(values.length >= min && values.length <= max) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValid(Collection<? extends Object> values, int min, int max) {
		if(values == null) {
			return true;
		}
		if(values.size() >= min && values.size() <= max) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValid(Map<? extends Object, ? extends Object> values, int min, int max) {
		if(values == null) {
			return true;
		}
		if(values.size() >= min && values.size() <= max) {
			return true;
		} else {
			return false;
		}
	}
	
	public static <T> boolean isValid(Class<T> type, T value, int min, int max) {
		return true;
	}
}
