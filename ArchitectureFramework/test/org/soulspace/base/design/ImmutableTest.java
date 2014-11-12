package org.soulspace.base.design;

import org.soulspace.annotation.design.Immutable;

@Immutable
public class ImmutableTest {

	String firstName;
	String lastName;

	public ImmutableTest(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getName() {
		return firstName;
	}

	public void setName(String name) {
//		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
//		this.lastName = lastName;
	}

	public void rename(String name) {
//		this.firstName = name;
	}

}
