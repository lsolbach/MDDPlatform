package org.soulspace.base.domain.validation;

public interface Validator {
	ValidationResult validate(Validateable v);
	ValidationResult validate(Validateable v, ValidationResult vResult);
}
