package org.soulspace.base.domain.object;

import java.util.Date;
import org.apache.log4j.Logger;
import org.soulspace.util.DateUtils;

public aspect TemporalAspect {

	static Logger log = Logger.getLogger(TemporalAspect.class.getName());
	
	declare parents : (@org.soulspace.annotation.domain.Temporal *) implements Temporal;

	//
	// intertype declarations
	//
	/**
	 * The time stamp since this object is valid (in the domain).
	 * A null value means it was valid since ever.
	 */
	Date Temporal.validFrom;

	/**
	 * The time stamp until this object is valid  (in the domain).
	 * A null value means it is valid forever.
	 */
	Date Temporal.validTo;

	/**
	 * The time stamp this object was invalidated technically.
	 * A null value means it has not been invalidated yet.
	 */
	Date Temporal.invalidatedAt;

	public synchronized Date Temporal.getValidFrom() {
		return validFrom;
	}
	
	public synchronized void Temporal.setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	
	public synchronized Date Temporal.getValidTo() {
		return validTo;
	}
	
	public synchronized void Temporal.setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public synchronized Date Temporal.getInvalidatedAt() {
		return invalidatedAt;
	}
	
	public synchronized void Temporal.setInvalidatedAt(Date invalidatedAt) {
		this.invalidatedAt = invalidatedAt;
	}
	
	public synchronized void Temporal.deactivate() {
		setInvalidatedAt(new Date());
	}
	
	/**
	 * Tests if this temporal intersects both ends of the other temporal
	 * @param t the other temporal
	 */
	public boolean Temporal.intersectsBoth(Temporal t) {
		if(isFromBeforeFrom(t.getValidFrom(), getValidFrom())
				&& isToBeforeTo(getValidTo(), t.getValidTo())) {
			log.trace("intersected at new from and to");
			return true;
		}
		return false;
	}
	
	/**
	 * Tests if this temporal intersects the beginning of the other temporal
	 * @param t the other temporal
	 */
	public boolean Temporal.intersectsBegin(Temporal t) {
		if(isFromBeforeFrom(getValidFrom(), t.getValidFrom())
			&& isFromBeforeTo(t.getValidFrom(), getValidTo())
			&& isToBeforeTo(getValidTo(), t.getValidTo())) {
			log.trace("intersects at from");
			return true;
		}
		return false;
	}
	
	public boolean Temporal.intersectsEnd(Temporal t) {
		if(isFromBeforeFrom(t.getValidFrom(), getValidFrom())
			&& isFromBeforeTo(getValidFrom(), t.getValidTo())
			&& isToBeforeTo(t.getValidTo(), getValidTo())) {
			log.trace("intersects at to");
			return true;
		}
		return false;
	}
	
	public boolean Temporal.hides(Temporal t) {
		if((isFromBeforeFrom(getValidFrom(), t.getValidFrom()) || isEqual(getValidFrom(), t.getValidFrom()))
			&& (isToBeforeTo(t.getValidTo(), getValidTo()) || isEqual(getValidTo(), t.getValidTo()))) {
			log.trace("hides");
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the object is deactivated
	 * @return
	 */
	public boolean Temporal.isDeactivated() {
		if(invalidatedAt != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the object is active and effective
	 * @param t
	 * @return
	 */
	public boolean Temporal.isValid(Date t) {
		if(!isDeactivated() && isEffective(t)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the object is effective (valid in the domain) at the specified time.
	 * @param t
	 * @return
	 */
	public boolean Temporal.isEffective(Date t) {
		if(t == null) {
			// FIXME exception/true/false?
			return false;
		} else if(getValidFrom() == null && getValidTo() == null) {
			return true;
		} else if(getValidFrom() == null) {
			if(t.before(getValidTo())) {
				return true;
			}
		} else if(getValidTo() == null) {
			if(!getValidFrom().before(t)) {
				return true;
			}
		} else if(!getValidFrom().before(t) && t.before(getValidTo())) {
			return true;
		}
		return false;
	}
	
	static boolean isFromBeforeFrom(Date d0, Date d1) {
		if(d0 == null) {
			return d1 != null;
		} else if(d1 == null) {
			return false;
		}
		return d0.before(d1);
	}
	
	static boolean isFromBeforeTo(Date d0, Date d1) {
		if(d0 == null) {
			return true;
		} else if (d1 == null) {
			return true;
		}
		return d0.before(d1);
	}
	
	static boolean isToBeforeFrom(Date d0, Date d1) {
		if(d0 == null || d1 == null) {
			return false;
		}
		return d0.before(d1);
	}
	
	static boolean isToBeforeTo(Date d0, Date d1) {
		if(d0 == null) {
			return false;
		} else if (d1 == null) {
			return d0 != null;
		}
		return d0.before(d1);
	}
	
	static boolean isEqual(Date d0, Date d1) {
		if(d0 == null) {
			return d1 == null;
		} else {
			 return d0.equals(d1);
		}
	}
	
	/**
	 * Returns true if the object is known to the system at the specified time.
	 * @param t
	 * @return
	 */
	public boolean Temporal.isKnown(Date t) {
		if(t == null) {
			// FIXME exception/true/false?
			return false;
		} else if(getModifiedAt().before(t) &&
				(!(getInvalidatedAt() != null) || t.before(getInvalidatedAt()))) {
			// TODO check invalidation time???
			return true;
		}
		return false;
	}
	
	//
	// pointcuts
	//
	pointcut temporalCreation() :
		call(Temporal+.new(..));
	;
	
	//
	// advices
	//
	after() returning (Temporal t) : temporalCreation() {
		t.validFrom = null;
		t.validTo = null;
		t.invalidatedAt = null;
	}

}
