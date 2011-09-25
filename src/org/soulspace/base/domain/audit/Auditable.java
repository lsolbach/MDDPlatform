package org.soulspace.base.domain.audit;

import java.util.List;

public interface Auditable {
	List<AuditEvent> getAuditEventList();
}
