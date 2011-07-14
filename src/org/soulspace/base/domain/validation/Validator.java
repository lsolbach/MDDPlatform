package org.soulspace.base.domain.validation;

public interface Validator {
	ValidationResult validate(Validatable v);
	ValidationResult validate(Validatable v, ValidationResult vResult);
}
