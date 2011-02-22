package org.soulspace.jmx.monitoring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class CallMonitor implements CallMonitorMBean {

	private Map<String, CallStat> callStatMap = new WeakHashMap<String, CallStat>();

	public void clearStatistics() {
		callStatMap = new WeakHashMap<String, CallStat>();
	}

	public List<String> getStatistics() {
		List<String> statList = new ArrayList<String>(); 
		for(Entry<String, CallStat> entry :callStatMap.entrySet()) {
			statList.add(entry.getValue().toString());
		}
		return statList;
	}

	public String printStats() {
		// TODO pretty printing
		StringBuilder sb = new StringBuilder(128);
		sb.append("Call Stats:\n");
		sb.append("calls \ttotal   \taverage   \tminimum   \tmaximum   \tsig\n");
		Iterator<String> it = callStatMap.keySet().iterator();
		while (it.hasNext()) {
			String sig = (String) it.next();
			CallStat cs = callStatMap.get(sig);
			sb.append(cs.toString());
			sb.append("\n");
		}
		return sb.toString();
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
	

}
