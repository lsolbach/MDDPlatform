package org.soulspace.base.domain.validation;

public interface Validator {
	Class<? extends Validateable> getType();
	ValidationResult validate(Validateable v);
	boolean isValid();
}
