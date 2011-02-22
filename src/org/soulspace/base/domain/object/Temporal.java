package org.soulspace.base.domain.object;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public interface Temporal extends Entity, Revisionable {

	Date getValidFrom();
	void setValidFrom(Date validFrom);

	Date getValidTo();
	void setValidTo(Date validTo);
	
	Date getInvalidatedAt();
	void setInvalidatedAt(Date invalidatedAt);
	
	boolean intersectsBoth(Temporal t);
	boolean intersectsBegin(Temporal t);
	boolean intersectsEnd(Temporal t);
	boolean hides(Temporal t);
	
	public class TemporalComparator implements Comparator<Temporal>, Serializable {

		private static final long serialVersionUID = 1L;

		public int compare(Temporal o1, Temporal o2) {
			int result = 0;
			if(o1.getValidFrom() == null && o2.getValidFrom() == null) {
				result = 0;
			} else if(o1.getValidFrom() == null) {
				result = -1;
			} else if(o2.getValidFrom() == null) {
				result = 1;
			} else {
				if(o1.getValidFrom().getTime() < o2.getValidFrom().getTime()) {
					result = -1;				
				} else if(o1.getValidFrom().getTime() > o2.getValidFrom().getTime()) {
					result = 1;				
				} else {
					result = 0;					
				}
			}
			return result;
		}
	}
}