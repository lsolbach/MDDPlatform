package org.soulspace.base.design;

import org.soulspace.annotation.design.Optional;

public class NullCheckTest {

	private static String name;
	
	public static void main(String[] args) {
		try {
			returnNull();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			toString(null);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			saveToString(null);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			addHashCodes("", null);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		name = null;
		name.toString();
	}

	@Optional
	static Object returnNull() {
		return null;
	}

	static String toString(Object o) {
		return o.toString();
	}

	static String saveToString(@Optional Object o) {
		return (o == null) ? "<null>" : o.toString();
	}

	static int addHashCodes(Object o1, Object o2) {
		return o1.hashCode() + o2.hashCode();
	}
}
