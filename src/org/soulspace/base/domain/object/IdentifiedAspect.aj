package org.soulspace.base.domain.object;

import org.soulspace.base.domain.identity.IdGenerator;
import org.soulspace.base.domain.identity.StupidIdGenerator;

public aspect IdentifiedAspect {

	// default to simple IdGenerator
	private IdGenerator idGenerator = new StupidIdGenerator();

	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}
	
	public IdGenerator getIdGenerator() {
		return idGenerator;
	}
	
	declare parents : (@org.soulspace.annotation.domain.Identifiable *) implements Identified;
	
	private String Identified.id;

	public String Identified.getId() {
		return id;
	}
	
	public void Identified.setId(String id) {
		this.id = id;
	}
	
	public boolean Identified.equals(Object o) {
		if(!this.getClass().isInstance(o)) {
			return false;			
		}
		Identified i = (Identified) o;
		if(!getId().equals(i.getId())) {
			return false;
		}
		return true;
	}

	public int Identified.hashCode() {
		int result = 17;
//		if(getClass().getSuperclass() != null 
//				&& getClass().getSuperclass() != java.lang.Object.class) {
//			result = 29 * result + super.hashCode();
//		}
		result = 29 * result + id.hashCode();
		return result;
	}
	
	pointcut identifiedCreation() :
		call((Identified+).new(..))
		;

	after() returning(Identified identified) : identifiedCreation() {
		identified.id = idGenerator.getId();
	}

}
