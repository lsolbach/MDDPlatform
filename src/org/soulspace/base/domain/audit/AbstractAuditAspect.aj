package org.soulspace.base.domain.audit;

import java.util.Collections;
import java.util.List;

import org.soulspace.base.domain.audit.impl.AuditEventImpl;
import org.soulspace.base.domain.object.DomainObject;

public abstract aspect AbstractAuditAspect {
	
	private AuditRepository auditRepository;
	
	public void setAuditRepository(AuditRepository auditRepository) {
		this.auditRepository = auditRepository;
	}
	
	private List<AuditEvent> Auditable.auditEventList;
	
	public List<AuditEvent> Auditable.getAuditEventList() {
		return Collections.unmodifiableList(auditEventList);
	}
	
	List<AuditEvent> Auditable.getAuditEventListInternal() {
		return auditEventList;
	}
	
	void Auditable.addAuditEvent(AuditEvent event) {
		auditEventList.add(event);
	}
	
	void Auditable.clearAuditEventList() {
		auditEventList.clear();
	}
	
	abstract pointcut rootCreate(Auditable auditable);
	abstract pointcut rootUpdate(Auditable auditable);
	abstract pointcut rootDelete(Auditable auditable);

	abstract pointcut childCreate(Auditable auditable, DomainObject child);
	abstract pointcut childUpdate(Auditable auditable, DomainObject child);
	abstract pointcut childDelete(Auditable auditable, DomainObject child);
	
	after(Auditable auditable) : rootCreate(auditable) {
		auditRepository.addAuditEvent(new AuditEventImpl(AuditEventType.CREATE, (DomainObject) auditable));
		auditable.clearAuditEventList();
	}
	
	after(Auditable auditable) : rootUpdate(auditable) {
		auditRepository.addAuditEvent(new AuditEventImpl(AuditEventType.UPDATE, (DomainObject) auditable));
		auditable.clearAuditEventList();
	}
	
	after(Auditable auditable) : rootDelete(auditable) {
		auditRepository.addAuditEvent(new AuditEventImpl(AuditEventType.DELETE, (DomainObject) auditable));
		auditable.clearAuditEventList();
	}

	void around(Auditable auditable, DomainObject child) : childCreate(auditable, child) {
		auditable.addAuditEvent(new AuditEventImpl(AuditEventType.CREATE, child));
	}

	void around(Auditable auditable, DomainObject child) : childUpdate(auditable, child) {
		auditable.addAuditEvent(new AuditEventImpl(AuditEventType.UPDATE, child));
	}

	void around(Auditable auditable, DomainObject child) : childDelete(auditable, child) {
		auditable.addAuditEvent(new AuditEventImpl(AuditEventType.DELETE, child));
	}

}