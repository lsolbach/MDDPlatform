package org.soulspace.aop.logging;

import org.aspectj.lang.Signature;

public abstract aspect AbstractExceptionLoggingAspect {

	abstract public void log(String message, Throwable e);

	private ThreadLocal<Throwable> lastLoggedException = new ThreadLocal<Throwable>();

	pointcut exceptionTraced() :
		execution(* *.*(..))
		&& !within(AbstractExceptionLoggingAspect);

	after() throwing(Throwable ex) : exceptionTraced() {
		if (lastLoggedException.get() != ex) {
			lastLoggedException.set(ex);
			Signature sig = thisJoinPointStaticPart.getSignature();
			log("ERROR: Exception trace aspect [" + sig.toShortString() + "]", ex);
		}
	}
}
