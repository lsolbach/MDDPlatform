package org.soulspace.base.domain.audit.impl;

import org.soulspace.base.domain.audit.AuditEvent;
import org.soulspace.base.domain.audit.AuditEventType;
import org.soulspace.base.domain.object.DomainObject;

public class AuditEventImpl implements AuditEvent {

	private AuditEventType type;
	private DomainObject object;
	
	public AuditEventImpl(AuditEventType type, DomainObject object) {
		this.type = type;
		this.object = object;
	}
	
	@Override
	public DomainObject getObject() {
		return object;
	}

	@Override
	public AuditEventType getType() {
		return type;
	}

}
