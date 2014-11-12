package org.soulspace.base.domain.authorisation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.soulspace.annotation.security.Secured;
import org.soulspace.annotation.security.Sensitive;

public aspect AnnotationBasedAuthorisationAspect extends AbstractAuthorisationAspect {

	declare precedence : AnnotationBasedAuthorisationAspect, *;

	pointcut securedMethodCall() :
		call(@Secured * *(..))
		;
	
	pointcut returnSensitive() :
		execution((@Sensitive *) *(..))
		;

	// TODO implement for real
	boolean hasAccess() {
		return false;		
	}

	String getPermission(JoinPoint thisJoinPoint) {
		Signature sig = thisJoinPoint.getSignature();
		if(sig instanceof MethodSignature) {
			Secured secured = ((MethodSignature) sig).getMethod().getAnnotation(Secured.class);
			return secured.permission();
		}
		return "";
	}	
}
