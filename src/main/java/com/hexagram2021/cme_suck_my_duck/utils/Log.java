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
public class Log {
	private static final int LOG_LEVEL;

	static {
		int level = 1;
		try {
			level = Integer.parseInt(System.getProperty("cme_suck_my_duck.log_level"));
		} catch (Exception ignored) {
		}
		LOG_LEVEL = level;
	}

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Writer WRITER = setLogFile(Path.of("CMESuckMyDuck.log"));

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

	private static void log(Level level, String message) {
		String out = String.format("[%s] [%s] [%s]: %s\n", DATE_FORMAT.format(new Date()), Thread.currentThread().getName(), level.name(), message);
		if(level.level() >= LOG_LEVEL) {
			synchronized (WRITER) {
				try {
					WRITER.write(out);
					WRITER.flush();
				} catch (Exception e) {
					System.err.printf("Error writing log: %s\n", e);
				}
			}
		}
	}
	private static void log(Level level, Throwable t) {
		Set<Throwable> dejaVu = Collections.newSetFromMap(new IdentityHashMap<>());
		dejaVu.add(t);
		synchronized (WRITER) {
			try {
				WRITER.write(String.format("[%s] [%s] [%s]: %s\n", DATE_FORMAT.format(new Date()), Thread.currentThread().getName(), level.name(), t));
				StackTraceElement[] trace = t.getStackTrace();
				for (StackTraceElement traceElement : trace) {
					WRITER.write("\tat " + traceElement + "\n");
				}
				for (Throwable se : t.getSuppressed()) {
					logEnclosedStackTrace(se, trace, SUPPRESSED_CAPTION, "\t", dejaVu);
				}
				Throwable ourCause = t.getCause();
				if (ourCause != null) {
					logEnclosedStackTrace(ourCause, trace, CAUSE_CAPTION, "", dejaVu);
				}
			} catch (Exception e) {
				System.err.printf("Error writing log: %s\n", e);
			}
		}
	}

	public static void debug(String message) {
		log(Level.DEBUG, message);
	}
	public static void debug(String format, Object... args) {
		debug(String.format(format, args));
	}
	public static void debug(Throwable t) {
		log(Level.DEBUG, t);
	}

	public static void info(String message) {
		log(Level.INFO, message);
	}
	public static void info(String format, Object... args) {
		info(String.format(format, args));
	}
	public static void info(Throwable t) {
		log(Level.INFO, t);
	}

	public static void warn(String message) {
		log(Level.WARN, message);
	}
	public static void warn(String format, Object... args) {
		warn(String.format(format, args));
	}
	public static void warn(Throwable t) {
		log(Level.WARN, t);
	}

	public static void error(String message) {
		log(Level.ERROR, message);
	}
	public static void error(String format, Object... args) {
		error(String.format(format, args));
	}
	public static void error(Throwable t) {
		log(Level.ERROR, t);
	}

	public static void fatal(String message) {
		error(message);
		System.exit(1);
	}
	public static void fatal(String format, Object... args) {
		error(format, args);
		System.exit(1);
	}
	public static void fatal(Throwable t) {
		error(t);
		System.exit(1);
	}

	private static final String CAUSE_CAPTION = "Caused by: ";
	private static final String SUPPRESSED_CAPTION = "Suppressed: ";

	private static void logEnclosedStackTrace(Throwable t, StackTraceElement[] enclosingTrace, String caption, String prefix, Set<Throwable> dejaVu) throws IOException {
		assert Thread.holdsLock(WRITER);
		if (dejaVu.contains(t)) {
			WRITER.write(prefix + caption + "[CIRCULAR REFERENCE: " + t + "]\n");
		} else {
			dejaVu.add(t);

			StackTraceElement[] trace = t.getStackTrace();
			int m = trace.length - 1;
			int n = enclosingTrace.length - 1;
			while (m >= 0 && n >=0 && trace[m].equals(enclosingTrace[n])) {
				m--; n--;
			}
			int framesInCommon = trace.length - 1 - m;

			WRITER.write(prefix + caption + t + "\n");
			for (int i = 0; i <= m; i++) {
				WRITER.write(prefix + "\tat " + trace[i] + "\n");
			}
			if (framesInCommon != 0) {
				WRITER.write(prefix + "\t... " + framesInCommon + " more\n");
			}

			for (Throwable se : t.getSuppressed()) {
				logEnclosedStackTrace(se, trace, SUPPRESSED_CAPTION, prefix + "\t", dejaVu);
			}

			// Print cause, if any
			Throwable ourCause = t.getCause();
			if (ourCause != null) {
				logEnclosedStackTrace(ourCause, trace, CAUSE_CAPTION, prefix, dejaVu);
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
}
