package org.soulspace.aop.tracing;

import org.soulspace.aop.util.AspectHelper;

public abstract aspect AbstractExceptionContextAspect {

	abstract pointcut exceptionsTraced();
	abstract public void log(String message);
	
	after() throwing(Exception e) :
		exceptionsTraced()
	{
		log("Exception occured: " + e.getClass() + ": " + e.getMessage());
		log("Exception occured in: " + AspectHelper.renderJoinPointWithArgs(thisJoinPoint));
	}	
	
}
