package org.soulspace.architecture.domain;

import org.soulspace.architecture.ArchitectureDefinitions;
import org.soulspace.architecture.domain.DomainDefinitions;

public aspect DomainEnforcementAspect {

	pointcut entityCreationViolation() :
		DomainDefinitions.creatingEntity()
		&& !DomainDefinitions.inFactory()
		&& !ArchitectureDefinitions.inTest();
	declare error : entityCreationViolation() : "Don't create entities with new, use a factory!";
	
	pointcut valueCreationViolation() :
		DomainDefinitions.creatingValue()
		&& !DomainDefinitions.inFactory()
		&& !ArchitectureDefinitions.inTest();
	declare error : valueCreationViolation() : "Don't create values with new, use a factory!";
	
	pointcut valueMutation() :
		call(* org.soulspace.base.domain.object.Value+.set*(..));
	declare warning : valueMutation() : "Don't change value objects, create new ones!";
	
	pointcut collectionReplace() :
		call(* org.soulspace.base.domain.object.DomainObject+.set*(Collection+));
	declare warning : collectionReplace() : "Don't replace a collection, use add/remove methods on domain object!";

}
