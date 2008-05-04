package org.soulspace.base.design;

public aspect EntityAspect {

	pointcut entityCreation() :
		call(((@org.soulspace.annotation.domain.Entity *)).new(..))
	;

	
}
