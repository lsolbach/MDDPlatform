package org.soulspace.architecture;

import java.util.Collection;


public aspect DesignEnforcementAspect extends AbstractDesignDefininitionAspect {

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
	
	declare error : entityCreationViolation() : "Don't create entities with new, use a factory!";
	
	pointcut valueMutation() :
		call(* org.soulspace.base.domain.object.Value+.set*(..))
		;
		
	declare warning : valueMutation() : "Don't change value objects, create new ones!";
	
	pointcut collectionReplace() :
		call(* org.soulspace.base.domain.object.DomainObject+.set*(Collection+))
		;
	
	declare warning : collectionReplace() : "Don't replace a collection, use add/remove methods on domain object!";

}
