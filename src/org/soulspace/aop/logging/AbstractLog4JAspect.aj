package org.soulspace.aop.logging;

import org.apache.log4j.Logger;

public abstract aspect AbstractLog4JAspect {

	// FIXME use correct pointcut designators
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
