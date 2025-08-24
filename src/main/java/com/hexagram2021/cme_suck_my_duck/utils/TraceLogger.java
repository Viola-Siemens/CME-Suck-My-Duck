package com.hexagram2021.cme_suck_my_duck.utils;

import static com.hexagram2021.cme_suck_my_duck.containers.Containers.logger;
import static com.hexagram2021.cme_suck_my_duck.utils.Log.*;

//SuckTrace::new is too slow so we use this class to check ignore thread before create throwable instance.
public final class TraceLogger {
	public static void debug(String traceId, String traceMessage) {
		if(Level.DEBUG.level() >= LOG_LEVEL && !shouldIgnoreThread()) {
			logger.log(Level.DEBUG, traceId, new SuckTrace(traceMessage));
		}
	}
	public static void info(String traceId, String traceMessage) {
		if(Level.INFO.level() >= LOG_LEVEL && !shouldIgnoreThread()) {
			logger.log(Level.INFO, traceId, new SuckTrace(traceMessage));
		}
	}

	private TraceLogger() {
	}
}
