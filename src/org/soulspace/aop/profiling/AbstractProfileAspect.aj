package org.soulspace.aop.profiling;

public abstract aspect AbstractProfileAspect {
	private long start;
	  
	abstract public pointcut profiledMethods();
	abstract public void log(String message);
	
	Object around() : profiledMethods() {
		start = System.currentTimeMillis();
		Object o = proceed();
		log((System.currentTimeMillis() - start) + "ms for " + thisJoinPointStaticPart.getSignature().toShortString());
		return o;
	}
}
