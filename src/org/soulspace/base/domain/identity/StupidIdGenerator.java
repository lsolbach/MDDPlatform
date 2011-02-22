package org.soulspace.base.domain.identity;

//import org.soulspace.base.infrastructure.identity.Identified;

public class StupidIdGenerator implements IdGenerator {

	public String getId() {
		return (Long.valueOf(System.nanoTime())).toString();
	}

	public String getId(String sequence) {
		return (Long.valueOf(System.nanoTime())).toString();
	}

//	public String getId(Identified idObject) {
//		return (Long.valueOf(System.nanoTime())).toString();
//	}

}
