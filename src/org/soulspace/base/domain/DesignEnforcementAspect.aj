package org.soulspace.base.domain;

import org.soulspace.base.domain.entity.Entity;

public aspect DesignEnforcementAspect {

	pointcut entityCreation() :
		call((Entity+).new(..))
		;
	
	pointcut inUnitTest() :
		within(junit.framework.TestCase+)
		;
	
	pointcut inTest() :
		within(*..*Test)
		;		
	
	pointcut inFactory() :
		within((@org.soulspace.annotation.domain.Factory *))
		;	

	pointcut inRepository() :
		within((@org.soulspace.annotation.domain.Repository *))
		;	

	pointcut inService() :
		within((@org.soulspace.annotation.domain.Service *))
		;	

	pointcut entityCreationViolation() :
		entityCreation()
		&& !inFactory()
		&& !inTest()
		&& !inUnitTest()
		;
	
	declare error : entityCreationViolation() : "Don't create entities with new, use a factory!";
	
	pointcut valueMutation() :
		call(* Value+.set*(..))
		;
		
	declare warning : valueMutation() : "Don't change value objects, create new ones!";
	
	pointcut collectionReplace() :
		call(* DomainObject+.set*(Collection+))
		;
	
	declare warning : collectionReplace() : "Don't replace a collection, use add/remove methods on domain object!";
}
