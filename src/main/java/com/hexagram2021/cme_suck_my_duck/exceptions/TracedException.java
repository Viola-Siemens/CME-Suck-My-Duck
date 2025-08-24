package com.hexagram2021.cme_suck_my_duck.exceptions;

import com.hexagram2021.cme_suck_my_duck.containers.Containers;

public class TracedException extends RuntimeException {
	private TracedException(String traceId, RuntimeException cause) {
		super("Check CMESuckMyDuck.log for details. Trace ID: " + traceId, cause);
	}

	public static TracedException create(String traceId, RuntimeException cause) {
		if(cause instanceof TracedException) {
			return (TracedException)cause;
		}
		TracedException ret = new TracedException(traceId, cause);
		if(STOP_LOGGING_IF_EXCEPTION_CREATED) {
			Containers.logger.fatal(ret);
		}
		return ret;
	}

	private static final boolean STOP_LOGGING_IF_EXCEPTION_CREATED;
	static {
		boolean shouldStopLoggingIfExceptionCreated = true;
		String property = System.getProperty("cme_suck_my_duck.stop_logging_if_exception_created");
		if(property != null && !Boolean.parseBoolean(property)) {
			shouldStopLoggingIfExceptionCreated = false;
		}
		STOP_LOGGING_IF_EXCEPTION_CREATED = shouldStopLoggingIfExceptionCreated;
	}
}
