package org.soulspace.base.domain.authentication;

import org.soulspace.base.domain.object.Identified;

public interface AuthenticationService {
	Identified getCurrentUser();
}
