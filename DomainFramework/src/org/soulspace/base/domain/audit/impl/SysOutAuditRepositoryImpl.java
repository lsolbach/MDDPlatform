package org.soulspace.base.domain.audit.impl;

import org.soulspace.base.domain.audit.AuditEvent;
import org.soulspace.base.domain.audit.AuditRepository;
import org.soulspace.base.domain.audit.Auditable;

public class SysOutAuditRepositoryImpl implements AuditRepository {

	public void addAuditEvent(AuditEvent event) {
		System.out.println("Audit " + event.getType() + ": "
				+ event.getObject().toString());
		if (event.getObject() instanceof Auditable) {
			Auditable root = (Auditable) event.getObject();
			for (AuditEvent childEvent : root.getAuditEventList()) {
				System.out.println("Audit " + childEvent.getType() + ": "
						+ root.toString() + " -> "
						+ childEvent.getObject().toString());
			}
		}
	}
}
