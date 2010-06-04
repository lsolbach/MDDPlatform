package org.soulspace.base.domain.audit;

public interface AuditRepository {
	void putAuditEvent(AuditEvent event);
}
