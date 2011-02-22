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
	Date Temporal.validFrom;
	Date Temporal.validTo;
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
	
	public boolean Temporal.intersectsBoth(Temporal t) {
		if(DateUtils.compareFrom(getValidFrom(), t.getValidFrom()) > 0
				&& DateUtils.compareTo(getValidTo(), t.getValidTo()) <= 0) {
			log.info("intersected at new from and to");
			return true;
		}
		return false;
	}
	
	public boolean Temporal.intersectsBegin(Temporal t) {
		if(DateUtils.compareFrom(getValidFrom(), t.getValidFrom()) <= 0
				&& DateUtils.compareTo(getValidTo(), t.getValidFrom()) > 0
				&& DateUtils.compareTo(getValidTo(), t.getValidTo()) < 0) {
			log.info("intersects at from");
			return true;
		}
		return false;
	}
	
	public boolean Temporal.intersectsEnd(Temporal t) {
		if(DateUtils.compareFrom(getValidFrom(), t.getValidFrom()) > 0
				&& DateUtils.compareTo(getValidFrom(), t.getValidTo()) < 0
				&& DateUtils.compareTo(getValidTo(), t.getValidTo()) >= 0) {
			log.info("intersects at to");
			return true;
		}
		return false;
	}
	
	public boolean Temporal.hides(Temporal t) {
		if(DateUtils.compareFrom(getValidFrom(), t.getValidFrom()) <= 0
				&& DateUtils.compareTo(getValidTo(), t.getValidTo()) >= 0
				) {
			log.info("hides");
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
