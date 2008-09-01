package org.soulspace.base.design;

import java.lang.annotation.Annotation;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.soulspace.annotation.design.Optional;

public aspect NullCheckAspect {

	pointcut checkedFieldSet(Object o) :
		set(!@org.soulspace.annotation.design.Optional * *)
		&& args(o)
		&& !within(NullCheckAspect)
		;
	
	pointcut checkedReturnValues() :
		call(!@org.soulspace.annotation.design.Optional Object *(..))
		&& !within(NullCheckAspect)
		;

	pointcut checkedCallParameters() :
		call(* *(*, ..))
		&& !within(NullCheckAspect)
		;
	
	before(Object o) : checkedFieldSet(o) {
		if(o == null) {
			throw new RuntimeException("Sets a null value in field " + thisJoinPoint.getSignature().toShortString() + ", but the field is not marked as optional");
		}
	}
	
	after() returning(Object o) : checkedReturnValues() {
		if(o == null) {
			throw new RuntimeException("Method " + thisJoinPoint.getSignature().toShortString() + " returns a null value but is not marked as optional");
		}
	}

	before() : checkedCallParameters() {
		Object[] args = thisJoinPoint.getArgs();
		Annotation[][] paramAnnotations = null;
		Signature sig = thisJoinPoint.getSignature();
		Boolean[] nullArgs = new Boolean[args.length];
		int nullArgCount = 0;

		if(args == null) {
			return;
		}
		
		if(sig instanceof MethodSignature) {
			paramAnnotations = ((MethodSignature) sig).getMethod().getParameterAnnotations();
		}
		for(int i = 0; i < args.length; i++) {
			if(args[i] == null && !hasOptionalAnnotation(paramAnnotations[i])) {
				nullArgs[i] = true;
				nullArgCount++;
			} else {
				nullArgs[i] = false;
			}
		}
		if(nullArgCount > 0) {
			StringBuilder sb = new StringBuilder(32);
			sb.append("Method ");
			sb.append(thisJoinPoint.getSignature().toShortString());
			sb.append(" is called with null args which are not declared as optional. Null Arguments: ");
			for(int i = 0; i < nullArgs.length; i++) {
//				sb.append("Arg ");
				if(nullArgs[i] == true) {
					sb.append(i);
					sb.append(" ");							
				}
			}
			throw new RuntimeException(sb.toString());
		}
	}
	
	boolean hasOptionalAnnotation(Annotation[] annotationArray) {
		if(annotationArray == null) {
			return false;
		}
		boolean isPresent = false;
		for(Annotation annotation : annotationArray) {
			if(annotation instanceof Optional) {
				isPresent = true;
			}
		}
		return isPresent;
	}
}
