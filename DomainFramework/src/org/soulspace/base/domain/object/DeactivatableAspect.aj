package org.soulspace.base.domain.object;

public aspect DeactivatableAspect {

	public interface DeactivatableInternal {
		void setDeactivated(boolean deactivated);
	}
	
	declare parents : Deactivatable implements DeactivatableInternal;
	
	boolean Deactivatable.deactivated;
	public synchronized boolean Deactivatable.getDeactivated() {
		return deactivated;
	}

	public synchronized void Deactivatable.setDeactivated(boolean deactivated) {
		this.deactivated = deactivated;
	}
	
	public synchronized void Deactivatable.deactivate() {
		deactivated = true;
	}
//	public boolean Deactivatable.isDeactivated() {
//		return deactivated;
//	}
}
