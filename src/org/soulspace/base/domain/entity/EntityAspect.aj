package org.soulspace.base.domain.entity;

import org.soulspace.base.infrastructure.identity.Identified;
import org.soulspace.base.infrastructure.identity.IdentityAspect;
import org.soulspace.base.infrastructure.persistence.Persistent;
import org.soulspace.base.infrastructure.persistence.PersistenceAspect;

public aspect EntityAspect {
	
//  declare parents : (@org.soulspace.annotation.domain.Entity *) implements Persistent;
//	declare parents : (@org.soulspace.annotation.domain.Entity *) implements Identifiable;
	declare parents : (@org.soulspace.annotation.domain.Entity *) implements Entity;

	pointcut entityCreationCall() :
		call((Entity+).new(..))
		;

	pointcut entityCreation() :
		execution((Entity+).new(..))
		;

}
