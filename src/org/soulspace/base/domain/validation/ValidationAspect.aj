package org.soulspace.base.domain.validation;

import org.soulspace.base.domain.factory.Factory;
import org.soulspace.base.domain.repository.Repository;

public aspect ValidationAspect {

	declare parents : (@org.soulspace.annotation.domain.Validateable *) implements Validateable;
	
	public boolean Validateable.isValid() {
		return true;
	}
	
	pointcut factoryShipping() :
		execution(Validateable+ Factory+.*())
		;
	
	after() returning(Validateable v) : factoryShipping() {
		ValidationResult vResult = v.validate();
		if(vResult.getSeverity().equals(Severity.ERROR)) {
			throw new ValidationException("Object " + v + " invalid!", vResult);
		}
	}

	pointcut repositoryStorage(Validateable v) :
		execution(* Repository+.store*(Validateable+))
		&& args(v);
		;
	
	before(Validateable v) : repositoryStorage(v) {
		ValidationResult vResult = v.validate();
		if(vResult.getSeverity().equals(Severity.ERROR)) {
			throw new ValidationException("Object " + v + " invalid!", vResult);
		}
	}

}
