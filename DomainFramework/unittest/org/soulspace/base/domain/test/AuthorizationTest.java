package org.soulspace.base.domain.test;

import junit.framework.TestCase;

public class AuthorizationTest extends TestCase {

	SecuredService ts = new SecuredService();
	
	public void testServiceCall() {
		try {
			ts.securedCall();			
			fail("Failed to precent access to secured method with insufficient rights");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void testSensitive() {
		try {
			SensitiveData td = ts.returnSensitve();
			fail("Failed to precent access to sensitve data with insufficient rights");
		} catch(Exception e) {
			System.out.println(e.getMessage());			
		}
	}	
}
