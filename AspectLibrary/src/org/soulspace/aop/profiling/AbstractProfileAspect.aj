/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
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
