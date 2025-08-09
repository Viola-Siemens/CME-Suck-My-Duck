package com.hexagram2021.cme_suck_my_duck.utils;

import static com.hexagram2021.cme_suck_my_duck.containers.Containers.logger;
import static com.hexagram2021.cme_suck_my_duck.utils.Log.*;

//SuckTrace::new is too slow so we use this class to check ignore thread before create throwable instance.
public final class TraceLogger {
	public static void debug(String traceMessage) {
		log(Level.DEBUG, traceMessage);
	}
	public static void info(String traceMessage) {
		log(Level.INFO, traceMessage);
	}

	private static void log(Log.Level level, String traceMessage) {
		if(level.level() >= LOG_LEVEL && !shouldIgnoreThread()) {
			logger.log(level, new SuckTrace(traceMessage));
		}
	}

	private TraceLogger() {
	}
}
