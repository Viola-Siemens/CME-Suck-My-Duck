package com.hexagram2021.cme_suck_my_duck.log;

import com.hexagram2021.cme_suck_my_duck.utils.Log;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public class ThrowableLogEntry extends AbstractLogEntry {
	final Throwable throwable;

	private static final String CAUSE_CAPTION = "Caused by: ";
	private static final String SUPPRESSED_CAPTION = "Suppressed: ";

	public ThrowableLogEntry(String level, String traceId, Throwable throwable) {
		super(Log.newDate(), Thread.currentThread().getName(), level, traceId);
		this.throwable = throwable;
	}

	@Override
	public void writeTo(Writer writer) {
		try {
			Set<Throwable> dejaVu = Collections.newSetFromMap(new IdentityHashMap<>());
			dejaVu.add(this.throwable);
			writer.write(String.format("[%s] [%s] [%s] [%s]: %s\n", this.date, this.thread, this.level, this.traceId, this.throwable));
			writer.flush();
			StackTraceElement[] trace = this.throwable.getStackTrace();
			for (StackTraceElement traceElement : trace) {
				writer.write("\tat " + traceElement + "\n");
			}
			writer.flush();
			for (Throwable se : this.throwable.getSuppressed()) {
				logEnclosedStackTrace(writer, se, trace, SUPPRESSED_CAPTION, "\t", dejaVu);
			}
			Throwable ourCause = this.throwable.getCause();
			if (ourCause != null) {
				logEnclosedStackTrace(writer, ourCause, trace, CAUSE_CAPTION, "", dejaVu);
			}
		} catch (Exception e) {
			System.err.printf("Error writing log: %s\n", e);
		}
	}

	private static void logEnclosedStackTrace(Writer writer, Throwable t, StackTraceElement[] enclosingTrace, String caption, String prefix, Set<Throwable> dejaVu) throws IOException {
		assert Thread.holdsLock(writer);
		if (dejaVu.contains(t)) {
			writer.write(prefix + caption + "[CIRCULAR REFERENCE: " + t + "]\n");
			writer.flush();
		} else {
			dejaVu.add(t);

			StackTraceElement[] trace = t.getStackTrace();
			int m = trace.length - 1;
			int n = enclosingTrace.length - 1;
			while (m >= 0 && n >=0 && trace[m].equals(enclosingTrace[n])) {
				m--; n--;
			}
			int framesInCommon = trace.length - 1 - m;

			writer.write(prefix + caption + t + "\n");
			writer.flush();
			for (int i = 0; i <= m; i++) {
				writer.write(prefix + "\tat " + trace[i] + "\n");
			}
			writer.flush();
			if (framesInCommon != 0) {
				writer.write(prefix + "\t... " + framesInCommon + " more\n");
			}
			writer.flush();

			for (Throwable se : t.getSuppressed()) {
				logEnclosedStackTrace(writer, se, trace, SUPPRESSED_CAPTION, prefix + "\t", dejaVu);
			}
			Throwable ourCause = t.getCause();
			if (ourCause != null) {
				logEnclosedStackTrace(writer, ourCause, trace, CAUSE_CAPTION, prefix, dejaVu);
			}
		}
	}
}
