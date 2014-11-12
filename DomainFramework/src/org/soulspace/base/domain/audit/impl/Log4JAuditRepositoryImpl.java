package org.soulspace.base.domain.audit.impl;

import org.apache.log4j.Logger;
import org.soulspace.base.domain.audit.AuditEvent;
import org.soulspace.base.domain.audit.AuditRepository;
import org.soulspace.base.domain.audit.Auditable;

public class Log4JAuditRepositoryImpl implements AuditRepository {

	Logger auditLogger = Logger.getLogger("Audit");
	@Override
	public void addAuditEvent(AuditEvent event) {
		auditLogger.info("Audit " + event.getType() + ": "
				+ event.getObject().toString());
		if (event.getObject() instanceof Auditable) {
			Auditable root = (Auditable) event.getObject();
			for (AuditEvent childEvent : root.getAuditEventList()) {
				auditLogger.info("Audit " + childEvent.getType() + ": "
						+ root.toString() + " -> "
						+ childEvent.getObject().toString());
			}
		}
	}

}
