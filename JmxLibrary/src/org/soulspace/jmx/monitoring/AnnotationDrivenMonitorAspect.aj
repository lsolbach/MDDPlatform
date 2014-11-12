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

import org.aspectj.lang.Signature;
import org.soulspace.annotation.metadata.Optional;
import org.soulspace.jmx.util.JmxHelper;

public aspect AnnotationDrivenMonitorAspect {	
	
	declare parents : (@org.soulspace.annotation.infrastructure.Monitored *) implements Monitored;
	
	/*
	 * Managed intertype declarations 
	 */
	@Optional
	public Map<String, CallStat> Monitored.callStatMap = null;

	public String Monitored.printStats() {
		// TODO pretty printing
		StringBuilder sb = new StringBuilder(128);
		sb.append("#calls \ttotal[ms]   \taverage[ms]   \tminimum[ms]   \tmaximum[ms]   \tsig\n");
		Iterator<String> it = getCallStatMap().keySet().iterator();
		while (it.hasNext()) {
			String sig = (String) it.next();
			CallStat cs = callStatMap.get(sig);
			sb.append(cs.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void Monitored.clearStats() {
		callStatMap = new WeakHashMap<String, CallStat>();		
	}

	public Map<String, CallStat> Monitored.getCallStatMap() {
		if(callStatMap != null) {
			return Collections.synchronizedMap(callStatMap);
		}
		return null;
	}

	
	CallStat createCallStat(Signature signature) {
		return new CallStatImpl(signature);
	}
	
	pointcut monitoredCreation() :
		call(Monitored+.new(..))
		;

	after() returning(Monitored c) : monitoredCreation() {
		JmxHelper.register(Monitored.class, c, "monitor:name");
	}

	public pointcut monitoredCalls(Monitored m) :
		execution(* (@org.soulspace.annotation.infrastructure.Monitored *).*(..))
//		||
//		execution(@org.soulspace.annotation.infrastructure.Monitored * *.*(..))
		&& target(m)
		;
	
	Object around(Monitored m) : monitoredCalls(m) {
		// TODO check if callStatMap is initialized, if not initialize callStatMap and register MBean,
		// because registration on constructor doesn't work for spring wiring
		if(m.getCallStatMap() == null) {
			m.clearStats();
			JmxHelper.register(Monitored.class, m, "monitored:name");
		}
		long start = System.currentTimeMillis();
		Object o = proceed(m);
		long duration = System.currentTimeMillis() - start;
		CallStat stats = null;
		Signature sig = thisJoinPointStaticPart.getSignature();
		if((stats = m.getCallStatMap().get(sig.toString())) == null) {
			stats = createCallStat(sig);
			m.getCallStatMap().put(sig.toString(), stats);
		}
		stats.addCall(duration);
		return o;
	}	
}
