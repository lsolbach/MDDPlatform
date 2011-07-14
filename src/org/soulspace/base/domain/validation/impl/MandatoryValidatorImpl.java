package org.soulspace.base.domain.validation.impl;

import java.lang.reflect.Field;

import org.soulspace.annotation.metadata.Mandatory;
import org.soulspace.base.domain.validation.FieldValidator;
import org.soulspace.base.domain.validation.Severity;
import org.soulspace.base.domain.validation.ValidationResult;

public class MandatoryValidatorImpl implements FieldValidator {
	public static boolean isValid(Object value) {
		return value != null;
	}

	public ValidationResult validateField(ValidationResult result, Field field,
			Object value) {
		if (field.isAnnotationPresent(Mandatory.class)) {
			if (value == null) {
				result.addValidationIssue(
						new ValidationIssueImpl(field.getName(),
												Severity.ERROR,
												"Field is mandatory!"));
			}
		}
		return result;
	}
}
