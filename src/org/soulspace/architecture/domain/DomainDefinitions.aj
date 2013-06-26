package org.soulspace.architecture.domain;

public aspect DomainDefinitions {

	public static pointcut inEntity() :
		within((@org.soulspace.annotation.domain.Entity *))
		;	

	public static pointcut inValue() :
		within((@org.soulspace.annotation.domain.Value *))
		;	

	public static pointcut inDomainObject() :
		inEntity() || inValue()
		;	

	public static pointcut inFactory() :
		within((@org.soulspace.annotation.domain.Factory *))
		;	

	public static pointcut inRepository() :
		within((@org.soulspace.annotation.domain.Repository *))
		;	

	public static pointcut inService() :
		within((@org.soulspace.annotation.domain.Service *))
		;	

	public static pointcut callingEntity() :
		call(* (@org.soulspace.annotation.domain.Entity *).*(..))
		;

	public static pointcut callingValue() :
		call(* (@org.soulspace.annotation.domain.Value *).*(..))
		;

	public static pointcut callingFactory() :
		call(* (@org.soulspace.annotation.domain.Factory *).*(..))
		;

	public static pointcut repositoryCall() :
		call(* (@org.soulspace.annotation.domain.Repository *).*(..))
		;

	public static pointcut callingService() :
		call(* (@org.soulspace.annotation.domain.Service *).*(..))
		;
	
	public static pointcut creatingEntity() :
		call((@org.soulspace.annotation.domain.Entity *).new(..))
		;
	
	public static pointcut creatingValue() :
		call((@org.soulspace.annotation.domain.Value *).new(..))
		;
	
}
