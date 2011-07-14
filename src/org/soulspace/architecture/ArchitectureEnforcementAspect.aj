package org.soulspace.architecture;

public aspect ArchitectureEnforcementAspect extends ArchitectureDefinitions {

	pointcut forbiddenPresentationLayerCall() :
		callingPresentationLayer()
		&& (inApplicationLayer() || inDomainLayer() || inInfrastructureLayer())
		;
	
	pointcut forbiddenApplicationLayerCall() :
		callingApplicationLayer()
		&& (inDomainLayer() || inInfrastructureLayer())
		;
	
	pointcut forbiddenDomainLayerCall() :
		callingDomainLayer()
		&& inInfrastructureLayer()
		;
	
	declare error : forbiddenPresentationLayerCall() : "Don't call presentation layer from lower layers!";
	declare error : forbiddenApplicationLayerCall() : "Don't call application layer from lower layers!";
	declare error : forbiddenDomainLayerCall() : "Don't call domain layer from lower layers!";
	
}
