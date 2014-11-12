package org.soulspace.base.domain.authorisation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.soulspace.annotation.security.Secured;
import org.soulspace.annotation.security.Sensitive;

public abstract aspect AbstractAuthorisationAspect {

	private AuthorisationService authorisationService;
	
	public void setAuthorisationService(AuthorisationService authorisationService) {
		this.authorisationService = authorisationService;
	}
	
	public AuthorisationService getAuthorisationService() {
		return this.authorisationService;
	}
	
	abstract pointcut securedMethodCall();
	
	abstract pointcut returnSensitive();

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
	
	abstract boolean hasAccess();

	abstract String getPermission(JoinPoint thisJoinPoint);
	
}
