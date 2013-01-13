package org.soulspace.architecture;

public aspect DesignEnforcementAspect {

	// TODO ok in declaring type
	pointcut directFieldAccess() :
		get(public !final * *)
		|| set(public !final * *);
	declare warning : directFieldAccess() : "Use accessor methods for access";

	pointcut systemStreamAccess() :   
		get(* System.out)
		|| get(* System.err);
	declare warning : systemStreamAccess() : "Use logging instead of system streams";
	
	pointcut entityCreationViolation() :
		DesignDefininitions.creatingEntity()
		&& !DesignDefininitions.inFactory()
		&& !ArchitectureDefinitions.inTest();
	declare error : entityCreationViolation() : "Don't create entities with new, use a factory!";
	
	pointcut valueCreationViolation() :
		DesignDefininitions.creatingValue()
		&& !DesignDefininitions.inFactory()
		&& !ArchitectureDefinitions.inTest();
	declare error : valueCreationViolation() : "Don't create values with new, use a factory!";
	
	pointcut valueMutation() :
		call(* org.soulspace.base.domain.object.Value+.set*(..));
	declare warning : valueMutation() : "Don't change value objects, create new ones!";
	
	pointcut collectionReplace() :
		call(* org.soulspace.base.domain.object.DomainObject+.set*(Collection+));
	declare warning : collectionReplace() : "Don't replace a collection, use add/remove methods on domain object!";

}