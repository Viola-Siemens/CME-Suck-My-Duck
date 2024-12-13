package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.Log;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class Containers {
	public static <T> List<T> newWrappedList(Object wrapped) {
		try {
			return new WrappedList<>((List<T>) wrapped);
		} catch (ClassCastException e) {
			Log.fatal(e);
		}
		return Collections.emptyList();
	}
	public static <T> Set<T> newWrappedSet(Object wrapped) {
		try {
			return new WrappedSet<>((Set<T>) wrapped);
		} catch (ClassCastException e) {
			Log.fatal(e);
		}
		return Collections.emptySet();
	}
	public static <K, V> Map<K, V> newWrappedMap(Object wrapped) {
		try {
			return new WrappedMap<>((Map<K, V>) wrapped);
		} catch (ClassCastException e) {
			Log.fatal(e);
		}
		return Collections.emptyMap();
	}

	private Containers() {
	}
}
