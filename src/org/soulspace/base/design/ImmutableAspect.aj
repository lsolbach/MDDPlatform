package org.soulspace.base.design;

public aspect ImmutableAspect {

//	pointcut immutableCreation() :
//		execution((@org.soulspace.annotation.design.Immutable *).new(..))
//		;
	
	pointcut immutableMutation() :
		set(* (@org.soulspace.annotation.design.Immutable *).*)
		&& !withincode((@org.soulspace.annotation.design.Immutable *).new(..))
		;

	declare error : immutableMutation() : "forbidden change on an immutable object!";
}
