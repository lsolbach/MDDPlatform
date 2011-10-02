package org.soulspace.aop.design;

import org.soulspace.annotation.design.Immutable;

public aspect ImmutableAspect {
	pointcut immutableMutation() :
		set(* (@Immutable *).*)
		&& !withincode((@Immutable *).new(..))
		;

	declare error : immutableMutation() : "forbidden change on an immutable object!";
}
