package org.soulspace.base.domain.test;

import junit.framework.TestCase;

public class AuthorisationTest extends TestCase {

	SecuredService ts = new SecuredService();
	
	public void testServiceCall() {
		try {
			ts.securedCall();			
			fail("Call to secured method succeded with insufficient rights");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void testSensitive() {
		try {
			SensitiveData td = ts.returnSensitve();
			fail("Access to sensitve data succeded with insufficient rights");
		} catch(Exception e) {
			System.out.println(e.getMessage());			
		}
	}	
}
