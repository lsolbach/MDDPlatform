package org.soulspace.base.domain.validation;

public interface Validateable {

	boolean validate();
	void setValidator(Validator validator);
}
