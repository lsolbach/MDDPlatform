package org.soulspace.aop.tracing;

import org.soulspace.annotation.infrastructure.Traced;

public abstract aspect AbstractAnnotationDrivenTraceAspect extends
		AbstractTraceAspect {

	public pointcut tracedMethods() :
		(execution(@Traced * *.*(..))
		||
		execution(* (@Traced *).*(..)))
		&&
		!within(AbstractTraceAspect+)
		;

}
