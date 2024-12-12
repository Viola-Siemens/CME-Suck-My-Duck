package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.Log;

import java.util.Collections;
import java.util.List;

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

	private Containers() {
	}
}
