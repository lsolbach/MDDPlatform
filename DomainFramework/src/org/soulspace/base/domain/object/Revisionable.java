package org.soulspace.base.domain.object;

import java.util.Date;

public interface Revisionable extends Modifiable {
	Date getCreatedAt();
	String getCreatedBy();

	Date getModifiedAt();
	String getModifiedBy();
}
