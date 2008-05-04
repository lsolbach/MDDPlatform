package org.soulspace.base.domain.entity;

import org.soulspace.annotation.infrastructure.Persistent;
import org.soulspace.base.infrastructure.persistence.PersistenceAspect;

public aspect EntityAspect {

	declare precedence : EntityAspect, PersistenceAspect;
	
	declare parents : (@org.soulspace.annotation.domain.Entity *) implements Entity;

//	declare @type : (@org.soulspace.annotation.domain.Entity *) : @Persistent;

//	pointcut entityCreation() :
//		call((Entity+).new(..))
//		;
	
}
