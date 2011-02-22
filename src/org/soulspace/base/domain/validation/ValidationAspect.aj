package org.soulspace.base.domain.validation;

import org.soulspace.base.domain.factory.Factory;
import org.soulspace.base.domain.repository.Repository;
import org.soulspace.base.domain.validation.impl.ValidatorImpl;

public aspect ValidationAspect {

	declare parents : (@org.soulspace.annotation.domain.Validateable *) implements Validateable;
	
	public boolean Validateable.isValid() {
		return true;
	}
	
	public ValidationResult Validateable.validate() {
		return ValidatorImpl.validate(this);
	}
	
	pointcut factoryShipping() :
		execution(Validateable+ Factory+.*())
		;
	
	after() returning(Validateable v) : factoryShipping() {
		validate(v);
	}

	pointcut repositoryStorage(Validateable v) :
		execution(* Repository+.store*(Validateable+))
		&& args(v);
		;
	
	before(Validateable v) : repositoryStorage(v) {
		validate(v);
	}
	
	ValidationResult validate(Validateable v) {
		ValidationResult vResult = v.validate();
		if(vResult.getSeverity().equals(Severity.ERROR)) {
			throw new ValidationException("Object " + v + " invalid!", vResult);
		}
		return vResult;
	}
}
