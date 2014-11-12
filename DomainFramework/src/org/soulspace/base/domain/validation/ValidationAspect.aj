package org.soulspace.base.domain.validation;

import org.soulspace.base.domain.factory.Factory;
import org.soulspace.base.domain.repository.Repository;
import org.soulspace.base.domain.validation.impl.ValidatorImpl;

/**
 * Valdidation aspect.
 * 
 * @author Ludger Solbach
 */
public aspect ValidationAspect {

	declare parents : (@org.soulspace.annotation.domain.Validatable *) implements Validatable;
	
	public boolean Validatable.isValid() {
		return true;
	}
	
	public ValidationResult Validatable.validate() {
		return ValidatorImpl.validate(this);
	}
	
	// captures factory shippings of validatable objects 
	pointcut factoryShipping() :
		execution(Validatable+ Factory+.*(..))
		;
	
	pointcut repositoryStorage() :
		execution(* Repository+.add*(Validatable+))
		||
		execution(* Repository+.update*(Validatable+))
		;
	
	pointcut repositoryValidation(Validatable v) :
		repositoryStorage()
		&& args(v)
		;
	
	// Validate object returned from factory
	after() returning(Validatable v) : factoryShipping() {
		validate(v);
	}

	// Validate object before storing to repository
	before(Validatable v) : repositoryValidation(v) {		
		validate(v);
	}
		
	void validate(Validatable v) {
		if(v == null) {
			throw new NullPointerException("The object to validate is null!");
		}
		ValidationResult vResult = v.validate();
		if(!vResult.isValid()) {
			throw new ValidationException("The object " + v + " is invalid!", vResult);
		}
	}
}