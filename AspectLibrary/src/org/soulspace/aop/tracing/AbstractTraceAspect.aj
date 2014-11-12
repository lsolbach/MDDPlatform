/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.aop.tracing;

import org.aspectj.lang.JoinPoint;
import org.soulspace.aop.util.AspectHelper;

public abstract aspect AbstractTraceAspect {

	long indent;
	abstract public pointcut tracedMethods();
	abstract public void log(String message);

	Object around() : tracedMethods() {
		log(methodStart(thisJoinPoint));
		Object o = proceed();
		log(methodReturn(o));
		return o;
	}
  
	String methodStart(JoinPoint jp) {
		StringBuilder sb = new StringBuilder(32);
		for(int i = 0; i < indent; i++) {
			sb.append("| ");
		}
		sb.append("> ");
		sb.append(AspectHelper.renderJoinPointWithArgs(jp));
		indent++;
		return sb.toString();
	}

	String methodReturn(Object o) {
		StringBuilder sb = new StringBuilder(32);
		indent--;
		for(int i = 0; i < indent; i++) {
			sb.append("| ");
		}
		sb.append("< ");
		if(o != null) {
			sb.append(o.toString());
		}
		return sb.toString();
	}
	
}