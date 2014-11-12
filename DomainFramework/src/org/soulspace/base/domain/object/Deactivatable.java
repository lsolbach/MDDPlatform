package org.soulspace.base.domain.object;

public interface Deactivatable extends Modifiable {
	boolean getDeactivated();
	void deactivate();
}
