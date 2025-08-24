package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.*;
import com.hexagram2021.cme_suck_my_duck.utils.Log;

import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.hexagram2021.cme_suck_my_duck.utils.SharedConstants.LOG_PATH;

@SuppressWarnings("unchecked")
public final class Containers {
	public static final Log logger;
	private static final boolean transformToThreadSafe;
	
	public static <T> List<T> newWrappedList(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(transformToThreadSafe) {
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
				if(transformToThreadSafe) {
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
				if(transformToThreadSafe) {
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
	public static <T> Iterator<T> newIterator(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new WrappedIterator<>((Iterator<T>) wrapped);
			}
			return (Iterator<T>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Collections.emptyIterator();
	}
	public static <T> ListIterator<T> newListIterator(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new WrappedListIterator<>((ListIterator<T>) wrapped);
			}
			return (ListIterator<T>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Collections.emptyListIterator();
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
			fixConcurrent = Boolean.parseBoolean(System.getProperty("cme_suck_my_duck.transform_to_thread_safe"));
		} catch (Exception ignored) {
		}
		transformToThreadSafe = fixConcurrent;
	}
}
