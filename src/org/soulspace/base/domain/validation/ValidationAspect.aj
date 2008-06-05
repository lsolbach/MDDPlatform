package org.soulspace.base.domain.validation;

public aspect ValidationAspect {

	declare parents : (@org.soulspace.annotation.domain.Validateable *) implements Validateable;
	
	public boolean Validateable.isValid() {
		return true;
	}
	
	pointcut factoryShipping() :
		execution(Validateable+ (@org.soulspace.annotation.domain.Factory *).*(..))
		;
	
	after() returning (Validateable v) : factoryShipping() {
		if(!v.isValid()) {
			;
		}
	}
}
