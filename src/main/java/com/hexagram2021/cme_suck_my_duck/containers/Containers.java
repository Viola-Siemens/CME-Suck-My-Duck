package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedIterator;
import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedListIterator;
import com.hexagram2021.cme_suck_my_duck.utils.Log;

import java.nio.file.StandardOpenOption;
import java.util.*;

import static com.hexagram2021.cme_suck_my_duck.utils.SharedConstants.LOG_PATH;

@SuppressWarnings("unchecked")
public final class Containers {
	public static final Log logger;
	static final boolean TRANSFORM_TO_THREAD_SAFE;
	public static final boolean OUTPUT_CLASS_BINARY;
	
	public static <T> List<T> newWrappedList(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(TRANSFORM_TO_THREAD_SAFE) {
					return Collections.synchronizedList((List<T>) wrapped);
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
				if(TRANSFORM_TO_THREAD_SAFE) {
					return Collections.synchronizedSet((Set<T>) wrapped);
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
				if(TRANSFORM_TO_THREAD_SAFE) {
					return Collections.synchronizedMap((Map<K, V>) wrapped);
				}
				return new WrappedMap<>((Map<K, V>) wrapped);
			}
			return (Map<K, V>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Collections.emptyMap();
	}
	public static <T> Iterator<T> newWrappedIterator(Object wrapped) {
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
	public static <T> ListIterator<T> newWrappedListIterator(Object wrapped) {
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

	/**
	 * @deprecated Not recommend to use this method, only when you find wrapped list can't work.
	 */
	@SuppressWarnings({"java:S1133", "java:S1319"})
	@Deprecated
	public static <T> ArrayList<T> newWrappedArrayList(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new WrappedArrayList<>((ArrayList<T>) wrapped);
			}
			return (ArrayList<T>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return new ArrayList<>();
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
			// Ignored
		}
		TRANSFORM_TO_THREAD_SAFE = fixConcurrent;
		boolean outputClassBinary = false;
		try {
			outputClassBinary = Boolean.parseBoolean(System.getProperty("cme_suck_my_duck.output_class_binary"));
		} catch (Exception ignored) {
			// Ignored
		}
		OUTPUT_CLASS_BINARY = outputClassBinary;
	}
}
