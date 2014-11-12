package org.soulspace.base.domain.validation.impl;

import java.lang.reflect.Field;

import org.soulspace.annotation.metadata.Range;
import org.soulspace.base.domain.validation.FieldValidator;
import org.soulspace.base.domain.validation.Severity;
import org.soulspace.base.domain.validation.ValidationResult;

public class RangeValidatorImpl implements FieldValidator {

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

	public ValidationResult validateField(ValidationResult result, Field field,
			Object value) {
		if(field.isAnnotationPresent(Range.class) && value != null) {
			// validate size
			Range range = field.getAnnotation(Range.class);
			long min = range.min();
			long max = range.max();
			Class<? extends Object> type = field.getType();
			if(type.isAssignableFrom(Long.class)) {
				long longValue = ((Long) value).longValue();
				if(longValue < min) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The value must be greater than or equal to " + min + "!"));
				}
				if(longValue > max) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The value must be less than or equal to " + max + "!"));
				}
			}
		}
		return result;
	}
}