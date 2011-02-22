package org.soulspace.base.domain.authorisation;

import org.soulspace.annotation.security.Secured;
import org.soulspace.annotation.security.Sensitive;

public abstract aspect AuthorisationAspect {

	// TODO implement security context (with user and roles for the application)
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
		if(!hasPermission()) {
			throw new AuthorisationException("Call not permitted");
		}
	}
	
	after() returning() : returnSensitive() {
		if(!hasAccess()) {
			throw new AuthorisationException("Data access denied");
		}
	}
	
	boolean hasPermission() {
		return false;
	}

	boolean hasAccess() {
		return false;		
	}
	
}
