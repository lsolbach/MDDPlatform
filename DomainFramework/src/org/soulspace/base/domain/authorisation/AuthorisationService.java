package org.soulspace.base.domain.authorisation;

import java.util.Set;

public interface AuthorisationService {

	boolean checkPermission(String permissionName);
	void validatePermission(String permissionName);
	
	Set<String> getPermissions();
	
}
