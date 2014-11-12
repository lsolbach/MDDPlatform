package org.soulspace.base.domain.validation;

public interface Validatable {

	boolean isValid();
	ValidationResult validate();
//	void setValidator(Validator validator);
}
