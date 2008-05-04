package org.soulspace.base.domain;

import org.soulspace.base.domain.entity.Entity;
import org.soulspace.base.domain.factory.Factory;

public aspect DesignValidationAspect {

	pointcut entityCreation() :
		call((Entity+).new(..))
		;
	
	pointcut inFactory() :
		within(Factory+)
		;
	
	pointcut inUnitTest() :
		within(junit.framework.TestCase+)
		;
	
	pointcut inTest() :
		within(*..*Test)
		;
		
	pointcut entityCreationViolation() :
		entityCreation()
		&& !inFactory()
		&& !inTest()
		&& !inUnitTest()
		;
	
	declare error : entityCreationViolation() : "Entity creation violation, use a Factory";
	
}
