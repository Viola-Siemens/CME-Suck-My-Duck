package com.hexagram2021.cme_suck_my_duck.utils;

import com.hexagram2021.cme_suck_my_duck.log.AbstractLogEntry;
import com.hexagram2021.cme_suck_my_duck.log.StringLogEntry;
import com.hexagram2021.cme_suck_my_duck.log.ThrowableLogEntry;

import javax.annotation.Nullable;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@SuppressWarnings("unused")
public final class Log {
	@Nullable
	public static Log INSTANCE = null;
	static final int LOG_LEVEL;
	private static final long LOG_WAIT_TIME;
	private static final int FILE_MAX_ENTRIES;
	@Nullable
	private static final String WHITELIST_CONSTRUCTOR_STACKTRACE;
	private static final String[] IGNORE_THREADS;
	private static final Thread LOG_THREAD;
	private static final Thread MAIN_THREAD;

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	private final Queue<AbstractLogEntry> TO_LOGS = new ConcurrentLinkedDeque<>();
	private final String path;
	private final StandardOpenOption[] openOptions;
	private Writer WRITER;

	public Log(String path, StandardOpenOption... openOptions) {
		this.path = path;
		this.openOptions = openOptions;
		this.WRITER = this.setLogFile();
		this.info("Log level: " + LOG_LEVEL);

		INSTANCE = this;
	}

	private Writer setLogFile() {
		return setLogFile(Paths.get(this.path + ".log"), this.openOptions);
	}

	public static Writer setLogFile(Path path, StandardOpenOption... openOptions) {
		try {
			return Files.newBufferedWriter(path, StandardCharsets.UTF_8, openOptions);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new IllegalStateException("Error setting log file.\n", e);
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

	@SuppressWarnings("SameParameterValue")
	private void log(Level level, String traceId, String message) {
		if(level.level() >= LOG_LEVEL) {
			this.TO_LOGS.add(new StringLogEntry(level.name(), traceId, message));
		}
	}
	void log(Level level, String traceId, Throwable t) {
		if(level.level() >= LOG_LEVEL) {
			this.TO_LOGS.add(new ThrowableLogEntry(level.name(), traceId, t));
		}
	}

	private static final String SYSTEM_TRACE_ID = "SYSTEM";

	public void debug(String message) {
		this.log(Level.DEBUG, SYSTEM_TRACE_ID, message);
	}
	public void debug(String format, Object... args) {
		this.debug(String.format(format, args));
	}
	public void debug(Throwable t) {
		this.log(Level.DEBUG, SYSTEM_TRACE_ID, t);
	}

	public void info(String message) {
		this.log(Level.INFO, SYSTEM_TRACE_ID, message);
	}
	public void info(String format, Object... args) {
		this.info(String.format(format, args));
	}
	public void info(Throwable t) {
		this.log(Level.INFO, SYSTEM_TRACE_ID, t);
	}

	public void warn(String message) {
		this.log(Level.WARN, SYSTEM_TRACE_ID, message);
	}
	public void warn(String format, Object... args) {
		this.warn(String.format(format, args));
	}
	public void warn(Throwable t) {
		this.log(Level.WARN, SYSTEM_TRACE_ID, t);
	}

	public void error(String message) {
		this.log(Level.ERROR, SYSTEM_TRACE_ID, message);
	}
	public void error(String format, Object... args) {
		this.error(String.format(format, args));
	}
	public void error(Throwable t) {
		this.log(Level.ERROR, SYSTEM_TRACE_ID, t);
	}

	public void fatal(String message) {
		this.error(message);
		exit = true;
		try {
			LOG_THREAD.join();
		} catch (InterruptedException ignored) {
		}
		//System.exit(1);
	}
	public void fatal(String format, Object... args) {
		this.error(format, args);
		exit = true;
		try {
			LOG_THREAD.join();
		} catch (InterruptedException ignored) {
		}
		//System.exit(1);
	}
	public void fatal(Throwable t) {
		this.error(t);
		exit = true;
		try {
			LOG_THREAD.join();
		} catch (InterruptedException ignored) {
		}
		//System.exit(1);
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
		long logWaitTime = 500L;
		try {
			logWaitTime = Long.parseLong(System.getProperty("cme_suck_my_duck.log_wait_time"));
		} catch (Exception ignored) {
		}
		LOG_WAIT_TIME = logWaitTime;
		int fileMaxEntries = 1000;
		try {
			fileMaxEntries = Integer.parseInt(System.getProperty("cme_suck_my_duck.file_max_entries"));
		} catch (Exception ignored) {
		}
		FILE_MAX_ENTRIES = fileMaxEntries;
		WHITELIST_CONSTRUCTOR_STACKTRACE = System.getProperty("cme_suck_my_duck.whitelist_constructor_stacktrace");
		String ignoreThreads = System.getProperty("cme_suck_my_duck.ignore_threads");
		if(ignoreThreads == null) {
			IGNORE_THREADS = new String[0];
		} else {
			IGNORE_THREADS = ignoreThreads.split(";");
		}
		LOG_THREAD = new Thread(Log::logThread, "CMESuckMyDuck-Log");
		LOG_THREAD.setDaemon(true);
		LOG_THREAD.start();
		MAIN_THREAD = Thread.currentThread();
	}

	public static boolean canWrap() {
		if(WHITELIST_CONSTRUCTOR_STACKTRACE == null) {
			return true;
		}
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (StackTraceElement element: stackTrace) {
			if(element.toString().contains(WHITELIST_CONSTRUCTOR_STACKTRACE)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("BooleanMethodIsAlwaysInverted")
	public static boolean shouldIgnoreThread() {
		String threadName = Thread.currentThread().getName();
		for(String ignoreThread: IGNORE_THREADS) {
			if(threadName.equals(ignoreThread)) {
				return true;
			}
		}
		return false;
	}

	private static boolean exit = false;
	@SuppressWarnings("BusyWait")
	private static void logThread() {
		int lines = 0;
		while(!exit) {
			try {
				Thread.sleep(LOG_WAIT_TIME);
				if(INSTANCE == null) {
					continue;
				}
				while(!INSTANCE.TO_LOGS.isEmpty()) {
					AbstractLogEntry entry = INSTANCE.TO_LOGS.poll();
					entry.writeTo(INSTANCE.WRITER);
					lines += 1;
					if(lines >= FILE_MAX_ENTRIES) {
						INSTANCE.WRITER.close();
						Files.move(Paths.get(INSTANCE.path + ".log"), Paths.get(INSTANCE.path + "-old.log"), StandardCopyOption.REPLACE_EXISTING);
						INSTANCE.WRITER = INSTANCE.setLogFile();
						lines = 0;
					}
				}
			} catch (Exception e) {
				System.err.printf("Error writing log: %s\n", e);
			}
		}
		try {
			if (INSTANCE != null) {
				Thread.sleep(1L);
				while(!INSTANCE.TO_LOGS.isEmpty()) {
					AbstractLogEntry entry = INSTANCE.TO_LOGS.poll();
					entry.writeTo(INSTANCE.WRITER);
					lines += 1;
					if(lines >= FILE_MAX_ENTRIES) {
						INSTANCE.WRITER.close();
						Files.move(Paths.get(INSTANCE.path + ".log"), Paths.get(INSTANCE.path + "-old.log"), StandardCopyOption.REPLACE_EXISTING);
						INSTANCE.WRITER = INSTANCE.setLogFile();
						lines = 0;
					}
				}
				AbstractLogEntry entry = new StringLogEntry(Level.INFO.name(), SYSTEM_TRACE_ID, "Main thread (" + MAIN_THREAD.getName() + ") stopped. Log thread is stopping.");
				entry.writeTo(INSTANCE.WRITER);
				INSTANCE.WRITER.close();
			}
		} catch (Exception ignored) {
		}
	}
}
