package com.hexagram2021.cme_suck_my_duck.utils;

import com.hexagram2021.cme_suck_my_duck.log.AbstractLogEntry;
import com.hexagram2021.cme_suck_my_duck.log.StringLogEntry;
import com.hexagram2021.cme_suck_my_duck.log.ThrowableLogEntry;

import javax.annotation.Nullable;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

@SuppressWarnings("unused")
public final class Log {
	@Nullable
	public static Log INSTANCE = null;
	private static final int LOG_LEVEL;
	private static final Thread LOG_THREAD;

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	private final Queue<AbstractLogEntry> TO_LOGS = new ConcurrentLinkedDeque<>();
	private final Writer WRITER;

	public Log(String path, StandardOpenOption... openOptions) {
		this.WRITER = setLogFile(Paths.get(path), openOptions);
		this.info("Log level: " + LOG_LEVEL);

		INSTANCE = this;
	}

	public static Writer setLogFile(Path path, StandardOpenOption... openOptions) {
		try {
			return Files.newBufferedWriter(path, StandardCharsets.UTF_8, openOptions);
		} catch (Exception e) {
			throw new IllegalStateException("Error setting log file: %s\n", e);
		}
	}

	public enum Level {
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
		if(level.level() >= LOG_LEVEL) {
			this.TO_LOGS.add(new StringLogEntry(level.name(), message));
		}
	}
	private void log(Level level, Throwable t) {
		if(level.level() >= LOG_LEVEL) {
			this.TO_LOGS.add(new ThrowableLogEntry(level.name(), t));
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

	public static String newDate() {
		return DATE_FORMAT.format(new Date());
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
		LOG_THREAD = new Thread(Log::logThread, "CMESuckMyDuck-Log");
		LOG_THREAD.start();
		Thread currentThread = Thread.currentThread();
		Thread callback = new Thread(() -> {
			try {
				currentThread.join();
			} catch (InterruptedException ignored) {
			}
			if(INSTANCE != null) {
				INSTANCE.info("Main thread (" + currentThread.getName() + ") is stopped. Log thread is stopping.");
			}
			exit = true;
		}, "CMESuckMyDuck-Callback");
	}

	private static boolean exit = false;
	private static void logThread() {
		while(!exit) {
			try {
				Thread.sleep(2000L);
				if(INSTANCE == null) {
					continue;
				}
				while(!INSTANCE.TO_LOGS.isEmpty()) {
					AbstractLogEntry entry = INSTANCE.TO_LOGS.poll();
					entry.writeTo(INSTANCE.WRITER);
				}
			} catch (Exception e) {
				System.err.printf("Error writing log: %s\n", e);
			}
		}
	}
}
