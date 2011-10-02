package org.soulspace.aop.tracing;

import org.aspectj.lang.JoinPoint;
import org.soulspace.aop.util.AspectHelper;

public abstract aspect AbstractTraceAspect {

	long indent;
	abstract public pointcut loggedMethods();
	abstract public void log(String message);

	Object around() : loggedMethods() {
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
