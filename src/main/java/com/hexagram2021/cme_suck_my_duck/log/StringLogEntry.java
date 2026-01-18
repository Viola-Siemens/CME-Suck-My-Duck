package com.hexagram2021.cme_suck_my_duck.log;

import com.hexagram2021.cme_suck_my_duck.utils.Log;

import java.io.Writer;

public class StringLogEntry extends AbstractLogEntry {
	final String message;

	public StringLogEntry(String level, String traceId, String message) {
		super(Log.newDate(), Thread.currentThread().getName(), level, traceId);
		this.message = message;
	}

	@Override
	public void writeTo(Writer writer) {
		try {
			String out = String.format("[%s] [%s] [%s] [%s]: %s\n", this.date, this.thread, this.level, this.traceId, this.message);
			writer.write(out);
			writer.flush();
		} catch (Exception e) {
			System.err.printf("Error writing log: %s%n", e);
		}
	}
}
