package org.soulspace.aop.profiling;

import org.soulspace.annotation.infrastructure.Profiled;

public abstract aspect AbstractAnnotationDrivenProfileAspect extends AbstractProfileAspect {

	public pointcut profiledMethods() :
		(execution(@Profiled * *.*(..))
		||
		execution(* (@Profiled *).*(..)))
		&&
		!within(AbstractProfileAspect+)
		;
}
