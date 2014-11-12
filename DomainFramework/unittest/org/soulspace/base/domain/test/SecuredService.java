package org.soulspace.base.domain.test;

import org.soulspace.annotation.common.Tracked;
import org.soulspace.annotation.security.Secured;

public class SecuredService {

	@Secured(permission="VIP")
	@Tracked(trackingId="SEC_CALL")
	public void securedCall() {
		
	}
	
	@Tracked(trackingId="RET_SENS")
	public SensitiveData returnSensitve() {
		return new SensitiveData();
	}
}
