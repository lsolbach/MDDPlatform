package org.soulspace.architecture;

public abstract aspect ArchitectureDefinitionAspect {

	/*
	 * definition of join point locations
	 */
	
	/*
	 * layer definitions
	 */
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
		within(junit.framework.TestCase+)
	;
	
	/*
	 * definition of call targets
	 */
	
	/*
	 * layer targets
	 */
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
	;
	
}
