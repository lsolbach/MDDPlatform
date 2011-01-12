package org.soulspace.architecture;

public aspect ArchitectureEnforcementAspect extends ArchitectureDefinitionAspect {

	pointcut forbiddenPresentationLayerCall() :
		presentationLayerCall()
		&& (inApplicationLayer() || inDomainLayer() || inInfrastructureLayer())
		;
	
	pointcut forbiddenApplicationLayerCall() :
		applicationLayerCall()
		&& (inDomainLayer() || inInfrastructureLayer())
		;
	
	pointcut forbiddenDomainLayerCall() :
		domainLayerCall()
		&& inInfrastructureLayer()
		;
	
	declare error : forbiddenPresentationLayerCall() : "Don't call presentation layer from lower layers!";
	declare error : forbiddenApplicationLayerCall() : "Don't call application layer from lower layers!";
	declare error : forbiddenDomainLayerCall() : "Don't call domain layer from lower layers!";
	
}
