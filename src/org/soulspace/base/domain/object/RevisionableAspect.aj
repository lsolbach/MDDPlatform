package org.soulspace.base.domain.object;

import java.util.Date;

public aspect RevisionableAspect {
	
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
	
}
