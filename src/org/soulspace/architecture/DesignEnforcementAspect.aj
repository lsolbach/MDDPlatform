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
	
}