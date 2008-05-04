package org.soulspace.base.domain.validation;

public aspect ValidationAspect {

	public boolean Validateable.validate() {
		return true;
	}
}
