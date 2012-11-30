package org.soulspace.aop.tracing;

public abstract aspect AbstractExceptionContextAspect {

	abstract pointcut exceptionsTraced();
	abstract public void log(String message);
	
	after() throwing(Exception e) : 
		exceptionsTraced()
	{
		log("Exception occured: " + e.getMessage());
		for(int i = 0; i < thisJoinPoint.getArgs().length; i++) {
			log("Arg " + i + ": " + thisJoinPoint.getArgs()[i]);
		}
	}	
	
}
