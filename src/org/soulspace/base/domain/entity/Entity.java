package org.soulspace.base.domain.entity;

import org.soulspace.base.domain.object.DomainObject;
import org.soulspace.base.infrastructure.identity.Identified;
import org.soulspace.base.infrastructure.persistence.Modifiable;
import org.soulspace.base.infrastructure.persistence.Persistent;

public interface Entity extends DomainObject, Persistent, Identified, Modifiable {
	
}
