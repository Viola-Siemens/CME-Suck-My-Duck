package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;

import static com.hexagram2021.cme_suck_my_duck.containers.Containers.logger;

@SuppressWarnings("unchecked")
public final class FastContainers {
	public static <V> Int2ObjectMap<V> newInt2ObjectWrappedMap(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new Int2ObjectWrappedMap<>((Int2ObjectMap<V>) wrapped);
			}
			return (Int2ObjectMap<V>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Int2ObjectMaps.emptyMap();
	}
	public static <K> Object2IntMap<K> newObject2IntWrappedMap(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new Object2IntWrappedMap<>((Object2IntMap<K>) wrapped);
			}
			return (Object2IntMap<K>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Object2IntMaps.emptyMap();
	}

	private FastContainers() {
	}
}
