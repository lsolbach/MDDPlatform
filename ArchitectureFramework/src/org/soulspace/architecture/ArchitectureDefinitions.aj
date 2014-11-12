package org.soulspace.architecture;

public abstract aspect ArchitectureDefinitions {

	/*
	 * definition of join point locations
	 */
	
	/*
	 * layer definitions
	 */
	public static pointcut inPresentationLayer() :
		within(org.soulspace..web..*)
		|| within(org.soulspace..ui..*)
	;

	public static pointcut inApplicationLayer() :
		within(org.soulspace..application..*)
	;

	public static pointcut inDomainLayer() :
		within(org.soulspace..domain..*)
	;

	public static pointcut inInfrastructureLayer() :
		within(org.soulspace..infrastructure..*)
	;

	public static pointcut inTest() :
		within(junit.framework.TestCase+)
	;
	
	/*
	 * definition of call targets
	 */
	
	/*
	 * layer targets
	 */
	public static pointcut callingPresentationLayer() :
		call(* org.soulspace..web..*.*(..))
		|| call(org.soulspace..web..new(..))
		|| call(* org.soulspace..ui..*.*(..))
		|| call(org.soulspace..ui..new(..))
	;

	public static pointcut callingApplicationLayer() :
		call(* org.soulspace..application..*.*(..))
		|| call(org.soulspace..application..new(..))
	;

	public static pointcut callingDomainLayer() :
		call(* org.soulspace..domain..*.*(..))
		|| call(org.soulspace..domain..new(..))
	;

	public static pointcut callingInfrastructureLayer() :
		call(* org.soulspace..infrastructure..*.*(..))
		|| call(org.soulspace..infrastructure..new(..))
	;

}