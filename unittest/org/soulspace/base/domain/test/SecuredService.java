package org.soulspace.base.domain.test;

import org.soulspace.annotation.security.Secured;

public class SecuredService {

	@Secured(permission="VIP")
	public void securedCall() {
		
	}
	
	public SensitiveData returnSensitve() {
		return new SensitiveData();
	}
}
