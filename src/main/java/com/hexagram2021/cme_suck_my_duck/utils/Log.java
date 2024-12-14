package com.hexagram2021.cme_suck_my_duck.utils;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Set;

@SuppressWarnings("unused")
public final class Log {
	private static final int LOG_LEVEL;

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final Writer WRITER;

	public Log(String path) {
		this.WRITER = setLogFile(Path.of(path));
		this.info("Log level: " + LOG_LEVEL);
	}

	public static Writer setLogFile(Path path) {
		try {
			return Files.newBufferedWriter(path, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new IllegalStateException("Error setting log file: %s\n", e);
		}
	}

	enum Level {
		DEBUG(0), INFO(1), WARN(2), ERROR(3);

		final int level;
		Level(int level) {
			this.level = level;
		}

		public int level() {
			return this.level;
		}
	}

	private void log(Level level, String message) {
		String out = String.format("[%s] [%s] [%s]: %s\n", DATE_FORMAT.format(new Date()), Thread.currentThread().getName(), level.name(), message);
		if(level.level() >= LOG_LEVEL) {
			synchronized (this.WRITER) {
				try {
					this.WRITER.write(out);
					this.WRITER.flush();
				} catch (Exception e) {
					System.err.printf("Error writing log: %s\n", e);
				}
			}
		}
	}
	private void log(Level level, Throwable t) {
		synchronized (this.WRITER) {
			Set<Throwable> dejaVu = Collections.newSetFromMap(new IdentityHashMap<>());
			dejaVu.add(t);
			try {
				this.WRITER.write(String.format("[%s] [%s] [%s]: %s\n", DATE_FORMAT.format(new Date()), Thread.currentThread().getName(), level.name(), t));
				this.WRITER.flush();
				StackTraceElement[] trace = t.getStackTrace();
				for (StackTraceElement traceElement : trace) {
					this.WRITER.write("\tat " + traceElement + "\n");
				}
				this.WRITER.flush();
				for (Throwable se : t.getSuppressed()) {
					logEnclosedStackTrace(WRITER, se, trace, SUPPRESSED_CAPTION, "\t", dejaVu);
				}
				Throwable ourCause = t.getCause();
				if (ourCause != null) {
					logEnclosedStackTrace(WRITER, ourCause, trace, CAUSE_CAPTION, "", dejaVu);
				}
			} catch (Exception e) {
				System.err.printf("Error writing log: %s\n", e);
			}
		}
	}

	public void debug(String message) {
		this.log(Level.DEBUG, message);
	}
	public void debug(String format, Object... args) {
		this.debug(String.format(format, args));
	}
	public void debug(Throwable t) {
		this.log(Level.DEBUG, t);
	}

	public void info(String message) {
		this.log(Level.INFO, message);
	}
	public void info(String format, Object... args) {
		this.info(String.format(format, args));
	}
	public void info(Throwable t) {
		this.log(Level.INFO, t);
	}

	public void warn(String message) {
		this.log(Level.WARN, message);
	}
	public void warn(String format, Object... args) {
		this.warn(String.format(format, args));
	}
	public void warn(Throwable t) {
		this.log(Level.WARN, t);
	}

	public void error(String message) {
		this.log(Level.ERROR, message);
	}
	public void error(String format, Object... args) {
		this.error(String.format(format, args));
	}
	public void error(Throwable t) {
		this.log(Level.ERROR, t);
	}

	public void fatal(String message) {
		this.error(message);
		System.exit(1);
	}
	public void fatal(String format, Object... args) {
		this.error(format, args);
		System.exit(1);
	}
	public void fatal(Throwable t) {
		this.error(t);
		System.exit(1);
	}

	private static final String CAUSE_CAPTION = "Caused by: ";
	private static final String SUPPRESSED_CAPTION = "Suppressed: ";

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

	public static String buildArrayString(Object[] objects) {
		if(objects.length == 0) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder(objects[0].toString());
		for(int i = 1; i < objects.length; ++i) {
			stringBuilder.append(", ");
			stringBuilder.append(objects[i].toString());
		}
		return stringBuilder.toString();
	}

	static {
		int level = 1;
		try {
			level = Integer.parseInt(System.getProperty("cme_suck_my_duck.log_level"));
		} catch (Exception ignored) {
		}
		LOG_LEVEL = level;
	}
}
