package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.Log;

import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.hexagram2021.cme_suck_my_duck.utils.SharedConstants.LOG_PATH;

@SuppressWarnings("unchecked")
public final class Containers {
	public static final Log logger;
	private static final boolean fix;
	
	public static <T> List<T> newWrappedList(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(fix) {
					return new CopyOnWriteArrayList<>((List<T>) wrapped);
				}
				return new WrappedList<>((List<T>) wrapped);
			}
			return (List<T>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Collections.emptyList();
	}
	public static <T> Set<T> newWrappedSet(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(fix) {
					Set<T> ret = ConcurrentHashMap.newKeySet();
					ret.addAll((Set<T>) wrapped);
					return ret;
				}
				return new WrappedSet<>((Set<T>) wrapped);
			}
			return (Set<T>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Collections.emptySet();
	}
	public static <K, V> Map<K, V> newWrappedMap(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(fix) {
					return new ConcurrentHashMap<>((Map<K, V>) wrapped);
				}
				return new WrappedMap<>((Map<K, V>) wrapped);
			}
			return (Map<K, V>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Collections.emptyMap();
	}

	private Containers() {
	}

	static {
		if(Log.INSTANCE == null) {
			logger = new Log(LOG_PATH, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} else {
			logger = Log.INSTANCE;
		}
		boolean fixConcurrent = false;
		try {
			fixConcurrent = Boolean.parseBoolean(System.getProperty("cme_suck_my_duck.fix"));
		} catch (Exception ignored) {
		}
		fix = fixConcurrent;
	}
}
