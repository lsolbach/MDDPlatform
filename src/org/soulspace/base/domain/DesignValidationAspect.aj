package org.soulspace.base.domain;

import org.soulspace.base.domain.entity.Entity;

public aspect DesignValidationAspect {

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
	
	declare error : entityCreationViolation() : "Entity creation violation, use a Factory";
	
}
