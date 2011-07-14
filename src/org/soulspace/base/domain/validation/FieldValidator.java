package org.soulspace.base.domain.validation;

import java.lang.reflect.Field;

public interface FieldValidator {
	
	ValidationResult validateField(ValidationResult result, Field field, Object value);

}
