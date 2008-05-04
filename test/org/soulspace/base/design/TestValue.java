package org.soulspace.base.design;

import org.soulspace.annotation.design.Function;

public class TestValue {
	private double value;
	
	public TestValue(double value) {
		this.value = value;
	}
	
	@Function
	public TestValue add(TestValue arg) {
//		value = value + arg.getValue();
		return this;
	}

	@Function
	public TestValue multiply(TestValue arg) {
		return new TestValue(value + arg.getValue());
	}

	public double getValue() {
		return value;
	}
	
}
