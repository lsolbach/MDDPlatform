package org.soulspace.base.domain.validation;

public interface Validateable {

	boolean isValid();
	ValidationResult validate();
//	void setValidator(Validator validator);
}
