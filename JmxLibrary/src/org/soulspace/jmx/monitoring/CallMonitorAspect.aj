/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.jmx.monitoring;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public abstract aspect CallMonitorAspect {
	
	private Map<String, CallStat> callStatMap = new WeakHashMap<String, CallStat>();
	
	public abstract pointcut monitoredCalls();
	
	Object around() : monitoredCalls() {
		long start = System.nanoTime();
		Object o = proceed();
		long duration = System.nanoTime() - start;
		addCall(thisJoinPointStaticPart, duration);
		return o;
	}
	
	void addCall(JoinPoint.StaticPart jpStatic, long duration) {
		CallStat stats = null;
		Signature sig = jpStatic.getSignature();
		if((stats = callStatMap.get(sig.toString())) == null) {
			stats = createCallStat(sig);
			callStatMap.put(sig.toString(), stats);
		}
		stats.addCall(duration);		
	}
	
	// TODO refactor to factory
	CallStat createCallStat(Signature signature) {
		return new CallStatImpl(signature);
	}
	
	public Map<String, CallStat> getCallStatMap() {
		return Collections.unmodifiableMap(callStatMap);
	}
	
	public String printStats() {
		// TODO pretty printing
		StringBuilder sb = new StringBuilder(128);
		sb.append("Call Stats:\n");
		sb.append("calls \ttotal   \taverage   \tminimum   \tmaximum   \tsig\n");
		Map<String , CallStat> map = getCallStatMap();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String sig = (String) it.next();
			CallStat cs = map.get(sig);
			sb.append(cs.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void clearStats() {
		callStatMap = new WeakHashMap<String, CallStat>();		
	}
	
}