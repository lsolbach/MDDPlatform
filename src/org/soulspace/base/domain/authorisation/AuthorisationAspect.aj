package org.soulspace.base.domain.authorisation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.soulspace.annotation.security.Secured;
import org.soulspace.annotation.security.Sensitive;

public abstract aspect AuthorisationAspect {

	private AuthorisationService authorisationService;
	
	public void setAuthorisationService(AuthorisationService authorisationService) {
		this.authorisationService = authorisationService;
	}
	
	public AuthorisationService getAuthorisationService() {
		return this.authorisationService;
	}
	
	declare precedence : AuthorisationAspect, *;

	pointcut securedMethodCall() :
		call(@Secured * *(..))
		;
	
	pointcut returnSensitive() :
		execution((@Sensitive *) *(..))
		;

//	pointcut returnSensitiveList()
//		execution(List<? extends (@Sensitive *)> *(..))
//		;
//	
//	pointcut returnSensitiveSet()
//		execution(Set<(@Sensitive *)> *(..))
//		;

	before() : securedMethodCall() {
		String requiredPermission = getPermission(thisJoinPoint);
		if(!authorisationService.checkPermission(requiredPermission)) {
			throw new AuthorisationException("Call to " + thisJoinPointStaticPart.toShortString()
					+ " is not permitted. Required permission " + requiredPermission + "!");
		}
	}
	
	after() returning() : returnSensitive() {
		if(!hasAccess()) {
			throw new AuthorisationException("Data access denied");
		}
	}
	
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
