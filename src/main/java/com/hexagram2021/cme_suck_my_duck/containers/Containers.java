package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.Log;

import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.hexagram2021.cme_suck_my_duck.utils.SharedConstants.LOG_PATH;

@SuppressWarnings("unchecked")
public final class Containers {
	public static final Log logger;
	
	public static <T> List<T> newWrappedList(Object wrapped) {
		try {
			return new WrappedList<>((List<T>) wrapped);
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Collections.emptyList();
	}
	public static <T> Set<T> newWrappedSet(Object wrapped) {
		try {
			return new WrappedSet<>((Set<T>) wrapped);
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Collections.emptySet();
	}
	public static <K, V> Map<K, V> newWrappedMap(Object wrapped) {
		try {
			return new WrappedMap<>((Map<K, V>) wrapped);
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Collections.emptyMap();
	}

	private Containers() {
	}

	static {
		if(Log.INSTANCE == null) {
			logger = new Log(LOG_PATH, StandardOpenOption.APPEND);
		} else {
			logger = Log.INSTANCE;
		}
	}
}
