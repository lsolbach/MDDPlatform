package org.soulspace.base.domain.object;

import java.util.Date;

import org.soulspace.base.domain.repository.Repository;

public aspect RevisionableAspect {
	
	declare parents : (@org.soulspace.annotation.domain.Revisionable *) implements Revisionable;
	
	Date Revisionable.createdAt;
	String Revisionable.createdBy;
	Date Revisionable.modifiedAt;
	String Revisionable.modifiedBy;
	
	public synchronized Date Revisionable.getCreatedAt() {
		return createdAt;
	}
	
	public synchronized void Revisionable.setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public synchronized String Revisionable.getCreatedBy() {
		return createdBy;
	}
	
	public synchronized void Revisionable.setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
		
	public synchronized Date Revisionable.getModifiedAt() {
		return modifiedAt;
	}
	
	public synchronized void Revisionable.setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	public synchronized String Revisionable.getModifiedBy() {
		return modifiedBy;
	}
	
	public synchronized void Revisionable.setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public pointcut addRevisionable(Revisionable revisionable) :
		execution(void Repository+.add*(Revisionable+))
		&& args(revisionable)
		;

	before(Revisionable revisionable) : addRevisionable(revisionable) {
		Date now = new Date();
		revisionable.setCreatedAt(now);
		revisionable.setModifiedAt(now);
		// TODO add user
	}
	
	public pointcut updateRevisionable(Revisionable revisionable) :
		execution(Revisionable+ Repository+.update*(Revisionable+))
		&& args(revisionable)
		;

	before(Revisionable revisionable) : updateRevisionable(revisionable) {
		Date now = new Date();
		revisionable.setModifiedAt(now);
		// TODO add user
	}
	
}
