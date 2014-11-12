/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.aop.logging;

import org.apache.log4j.Logger;

public abstract aspect AbstractLog4JAspect {

	// TODO check use of the correct pointcut designators
	pointcut logInfo(Logger logger) :
		call(* Logger.info(..))
		&&
		target(logger)
		;

	pointcut logDebug(Logger logger) :
		call(* Logger.debug(..))
		&&
		target(logger)
		;

	pointcut logTrace(Logger logger) :
		call(* Logger.trace(..))
		&&
		target(logger)
		;

	void around(Logger logger) : logInfo(logger) {
		if(logger.isInfoEnabled()) {
			proceed(logger);
		}
	}

	void around(Logger logger) : logDebug(logger) {
		if(logger.isDebugEnabled()) {
			proceed(logger);
		}		
	}

	void around(Logger logger) : logTrace(logger) {
		if(logger.isTraceEnabled()) {
			proceed(logger);
		}		
	}
}
