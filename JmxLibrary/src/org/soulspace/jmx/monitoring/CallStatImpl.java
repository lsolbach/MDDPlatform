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

import org.aspectj.lang.Signature;

public class CallStatImpl implements CallStat {
	Signature signature;
	long callCount;
	long maxDuration = 0;
	long minDuration = Long.MAX_VALUE;
	long sumDuration = 0;
	
	protected CallStatImpl(Signature signature) {
		this.signature = signature;
	}
	
	public void addCall(long duration) {
		callCount++;
		sumDuration = sumDuration + duration;
		if(duration < minDuration) {
			minDuration = duration;
		}
		if(duration > maxDuration) {
			maxDuration = duration;
		}
	}

	public long getCallCount() {
		return callCount;
	}
	
	public Signature getSignature() {
		return signature;
	}

	public String toString() {
		long avgDuration = 0;
		if(callCount > 0) {
			avgDuration = sumDuration / callCount;
		}
		StringBuilder sb = new StringBuilder(64);
		sb.append(callCount);
		sb.append("\t");
		sb.append(sumDuration);
		sb.append("\t");
		sb.append(avgDuration);
		sb.append("\t");
		sb.append(minDuration);
		sb.append("\t");
		sb.append(maxDuration);
		sb.append("\t");
		sb.append(signature.toShortString());
		
		return sb.toString();
	}
}
