/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
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
