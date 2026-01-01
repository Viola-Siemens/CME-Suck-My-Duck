package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.TraceIdGenerator;
import com.hexagram2021.cme_suck_my_duck.utils.TraceLogger;

@SuppressWarnings("unchecked")
public abstract class AbstractWrappedContainer<W> {
	protected final W wrapped;
	protected final String traceId;

	protected AbstractWrappedContainer(W wrapped) {
		if(wrapped instanceof AbstractWrappedContainer) {
			this.wrapped = ((AbstractWrappedContainer<W>)wrapped).wrapped;
		} else {
			this.wrapped = wrapped;
		}

		this.traceId = TraceIdGenerator.generateTraceId();
	}

	protected AbstractWrappedContainer(W wrapped, String traceId) {
		if(wrapped instanceof AbstractWrappedContainer) {
			this.wrapped = ((AbstractWrappedContainer<W>)wrapped).wrapped;
		} else {
			this.wrapped = wrapped;
		}

		this.traceId = traceId;
	}

	protected void logQuery(String signature, boolean shouldLog) {
		if(shouldLog) {
			TraceLogger.debug(this.traceId, "[Query] " + signature);
		}
	}
	protected void logIteration(String signature, boolean shouldLog) {
		if(shouldLog) {
			TraceLogger.info(this.traceId, "[Iteration] " + signature);
		}
	}
	protected void logModify(String signature, boolean shouldLog) {
		if(shouldLog) {
			TraceLogger.info(this.traceId, "[Modify] " + signature);
		}
	}
}
