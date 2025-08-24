package com.hexagram2021.cme_suck_my_duck.log;

import java.io.Writer;

public abstract class AbstractLogEntry {
	protected final String date;
	protected final String thread;
	protected final String level;
	protected final String traceId;

	protected AbstractLogEntry(String date, String thread, String level, String traceId) {
		this.date = date;
		this.thread = thread;
		this.level = level;
		this.traceId = traceId;
	}

	public abstract void writeTo(Writer writer);
}
