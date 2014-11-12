package org.soulspace.base.domain.object;

public aspect EntityAspect {
	
	declare parents : (@org.soulspace.annotation.domain.Entity *) implements Entity;

	pointcut entityCreationCall() :
		call((Entity+).new(..))
		;

	pointcut entityCreation() :
		execution((Entity+).new(..))
		;

}
