package com.hexagram2021.cme_suck_my_duck.utils;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum LogStrategies implements LogStrategy {
	ALL(o -> true, true),
	NULL(Objects::isNull),
	NOT_NULL(Objects::nonNull);

	@Override
	public boolean test(@Nullable Object o) {
		return this.predicate.test(o);
	}

	public boolean logAnyway() {
		return this.logAnyway;
	}

	private final LogStrategy predicate;
	private final boolean logAnyway;

	private static final Map<String, LogStrategies> BY_NAME;

	LogStrategies(LogStrategy predicate) {
		this.predicate = predicate;
		this.logAnyway = false;
	}
	LogStrategies(LogStrategy predicate, boolean logAnyway) {
		this.predicate = predicate;
		this.logAnyway = logAnyway;
	}

	public static LogStrategies of(String name) {
		return BY_NAME.getOrDefault(name, ALL);
	}

	 static {
		BY_NAME = new HashMap<>();
		 for (LogStrategies strategy : values()) {
			 BY_NAME.put(strategy.name(), strategy);
		 }
	}
}
