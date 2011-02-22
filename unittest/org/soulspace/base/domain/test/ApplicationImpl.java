package org.soulspace.base.domain.test;

import org.soulspace.annotation.domain.Entity;
import org.soulspace.annotation.infrastructure.Persistent;
import org.soulspace.annotation.metadata.Mandatory;
import org.soulspace.annotation.metadata.Size;
import org.soulspace.base.domain.validation.Validateable;
import org.soulspace.base.domain.validation.ValidationResult;

@Entity
public class ApplicationImpl implements Application, Validateable {

	@Mandatory
	@Size(min = 2, max = 20)
	private String name;
	
	public ApplicationImpl(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	public ValidationResult validate() {
		// TODO Auto-generated method stub
		return null;
	}

}
