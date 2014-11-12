package org.soulspace.architecture.design;

public aspect FunctionAspect {
	
	pointcut inFunction() :
		withincode(@org.soulspace.annotation.design.Function * *.*(..))		
		;
	
	pointcut functionContractViolation() :
		set(* *.*)
		&& inFunction()
		;

	declare error : functionContractViolation() :
		"forbidden state change in function";
}
