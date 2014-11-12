package org.soulspace.base.domain.object;

public aspect ModifiableAspect {
	
	public static long NEW = -1;
	
	long Modifiable.modification = NEW;
	
	public synchronized long Modifiable.getModification() {
		return modification;
	}
	
	public synchronized void Modifiable.setModification(long modification) {
		this.modification = modification;
	}
	
	public synchronized void Modifiable.incrementModification() {
		modification++;
	}
	
	public synchronized boolean Modifiable.isNew() {
		return modification <= ModifiableAspect.NEW;
	}
	
	pointcut modifiableCreation() :
		call(Modifiable+.new(..))
		;
	
}
