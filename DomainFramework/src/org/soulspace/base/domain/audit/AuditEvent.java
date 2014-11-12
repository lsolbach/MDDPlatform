package org.soulspace.base.domain.audit;

import org.soulspace.base.domain.object.DomainObject;

public interface AuditEvent {
	AuditEventType getType();
	DomainObject getObject();
}
