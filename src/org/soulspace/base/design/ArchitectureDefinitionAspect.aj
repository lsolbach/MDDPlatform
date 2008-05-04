package org.soulspace.base.design;

public abstract aspect ArchitectureDefinitionAspect {

	public pointcut inPresentationLayer() :
		within(*..web..*)
		|| within(*..ui..*)
	;

	public pointcut inApplicationLayer() :
		within(*..application..*)
	;

	public pointcut inDomainLayer() :
		within(*..domain..*)
	;

	public pointcut inInfrastructureLayer() :
		within(*..infrastructure..*)
	;
	
	public pointcut inTest() :
		within(org.junit.TestCase+)
	;
	
	public pointcut presentationLayerCall() :
		call(* *..web..*.*(..))
		|| call(*..web..new(..))
		|| call(* *..ui..*.*(..))
		|| call(*..ui..new(..))
	;

	public pointcut applicationLayerCall() :
		call(* *..application..*.*(..))
		|| call(*..application..new(..))
	;

	public pointcut domainLayerCall() :
		call(* *..domain..*.*(..))
		|| call(*..domain..new(..))
	;

	public pointcut infrastructureLayerCall() :
		call(* *..infrastructure..*.*(..))
		|| call(*..infrastructure..new(..))
	;

	public pointcut serviceCall() :
		call(* *.Service+.*(..))
		|| call(*.Service+.new(..))
		|| call(* *.*Service+.*(..))
		|| call(*.*Service+.new(..))
	;
	
}
