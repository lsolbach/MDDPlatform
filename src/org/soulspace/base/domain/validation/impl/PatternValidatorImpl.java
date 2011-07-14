package org.soulspace.base.domain.validation.impl;

import java.lang.reflect.Field;

import org.soulspace.annotation.metadata.Pattern;
import org.soulspace.base.domain.validation.FieldValidator;
import org.soulspace.base.domain.validation.Severity;
import org.soulspace.base.domain.validation.ValidationResult;

public class PatternValidatorImpl implements FieldValidator {

	public static boolean isValid(Object value, String pattern) {
		return value.toString().matches(pattern);
	}

	public ValidationResult validateField(ValidationResult result, Field field,
			Object value) {
		if(field.isAnnotationPresent(Pattern.class) && value != null) {
			// validate pattern
			Pattern pattern = field.getAnnotation(Pattern.class);
			Class<? extends Object> type = field.getType();
			if(type.equals(String.class)) {
				if(!((String) value).matches(pattern.pattern())) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(), Severity.ERROR,
									"The string doesn't match the required pattern " + pattern.pattern()));
				}
			}
		}
		return result;
	}
	
}
