package com.hexagram2021.cme_suck_my_duck.containers;

import com.google.common.collect.*;
import com.hexagram2021.cme_suck_my_duck.utils.Log;

import static com.hexagram2021.cme_suck_my_duck.containers.Containers.logger;
import static com.hexagram2021.cme_suck_my_duck.containers.Containers.TRANSFORM_TO_THREAD_SAFE;

@SuppressWarnings("unchecked")
public final class GuavaContainers {
	public static <K, V> BiMap<K, V> newWrappedBiMap(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(TRANSFORM_TO_THREAD_SAFE) {
					return Maps.synchronizedBiMap((BiMap<K, V>) wrapped);
				}
				return new WrappedBiMap<>((BiMap<K, V>) wrapped);
			}
			return (BiMap<K, V>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return ImmutableBiMap.of();
	}

	public static <T> Multiset<T> newWrappedMultiset(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(TRANSFORM_TO_THREAD_SAFE) {
					throw new UnsupportedOperationException("Cannot transform Multiset to thread-safe.");
				}
				return new WrappedMultiset<>((Multiset<T>) wrapped);
			}
			return (Multiset<T>) wrapped;
		} catch (UnsupportedOperationException | ClassCastException e) {
			logger.fatal(e);
		}
		return ImmutableMultiset.of();
	}

	public static <K, V> Multimap<K, V> newWrappedMultimap(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(TRANSFORM_TO_THREAD_SAFE) {
					return Multimaps.synchronizedMultimap((Multimap<K, V>) wrapped);
				}
				return new WrappedMultimap<>((Multimap<K, V>) wrapped);
			}
			return (Multimap<K, V>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return ImmutableMultimap.of();
	}

	private GuavaContainers() {
	}
}
