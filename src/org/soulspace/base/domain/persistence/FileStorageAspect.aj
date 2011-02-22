package org.soulspace.base.domain.persistence;

import java.util.HashMap;
import java.util.Map;

import org.soulspace.base.domain.object.Modifiable;

public aspect FileStorageAspect {

	static Map<Integer, Long> currentModificationMap = new HashMap<Integer, Long>();
	
	pointcut incModification(Modifiable m) :
		execution(* Modifiable.incrementModification())
		&& this(m)
		;
	
	void around(Modifiable m) : incModification(m) {
		synchronized (currentModificationMap) {
			if(currentModificationMap.get(m.hashCode()) != null) {
				m.setModification(currentModificationMap.get(m.hashCode()));				
			}
			proceed(m);
			currentModificationMap.put(m.hashCode(), m.getModification());
		}
	}
}