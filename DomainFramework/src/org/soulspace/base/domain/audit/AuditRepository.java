package org.soulspace.base.domain.audit;

public interface AuditRepository {
	void addAuditEvent(AuditEvent event);
}
