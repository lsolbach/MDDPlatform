package org.soulspace.architecture;

public abstract aspect AbstractDesignDefininitionAspect {

	pointcut inEntity() :
		within((@org.soulspace.annotation.domain.Entity *))
		;	

	pointcut inValue() :
		within((@org.soulspace.annotation.domain.Value *))
		;	

	pointcut inDomainObject() :
		inEntity() || inValue()
		;	

	pointcut inFactory() :
		within((@org.soulspace.annotation.domain.Factory *))
		;	

	pointcut inRepository() :
		within((@org.soulspace.annotation.domain.Repository *))
		;	

	pointcut inService() :
		within((@org.soulspace.annotation.domain.Service *))
		;	

	pointcut entityCall() :
		call(* (@org.soulspace.annotation.domain.Entity *).*(..))
		;

	pointcut valueCall() :
		call(* (@org.soulspace.annotation.domain.Value *).*(..))
		;

	pointcut factoryCall() :
		call(* (@org.soulspace.annotation.domain.Factory *).*(..))
		;

	pointcut repositoryCall() :
		call(* (@org.soulspace.annotation.domain.Repository *).*(..))
		;

	pointcut serviceCall() :
		call(* (@org.soulspace.annotation.domain.Service *).*(..))
		;
	
	pointcut entityCreation() :
		call((@org.soulspace.annotation.domain.Entity *).new(..))
		;
	
	pointcut valueCreation() :
		call((@org.soulspace.annotation.domain.Value *).new(..))
		;
	
}
