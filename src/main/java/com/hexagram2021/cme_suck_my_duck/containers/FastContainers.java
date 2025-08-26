package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.IntWrappedIterator;
import com.hexagram2021.cme_suck_my_duck.containers.iterators.LongWrappedIterator;
import com.hexagram2021.cme_suck_my_duck.containers.iterators.ObjectWrappedIterator;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.ints.*;
import it.unimi.dsi.fastutil.longs.*;
import it.unimi.dsi.fastutil.objects.*;

import static com.hexagram2021.cme_suck_my_duck.containers.Containers.logger;

@SuppressWarnings("unchecked")
public final class FastContainers {
	public static IntSet newIntWrappedMap(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new IntWrappedSet((IntSet) wrapped);
			}
			return (IntSet) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return IntSets.emptySet();
	}
	public static LongSet newLongWrappedMap(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new LongWrappedSet((LongSet) wrapped);
			}
			return (LongSet) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return LongSets.emptySet();
	}
	public static <T> ObjectSet<T> newObjectWrappedSet(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new ObjectWrappedSet<>((ObjectSet<T>) wrapped);
			}
			return (ObjectSet<T>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return ObjectSets.emptySet();
	}
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
	public static <V> Long2ObjectMap<V> newLong2ObjectWrappedMap(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new Long2ObjectWrappedMap<>((Long2ObjectMap<V>) wrapped);
			}
			return (Long2ObjectMap<V>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Long2ObjectMaps.emptyMap();
	}
	public static <K> Object2LongMap<K> newObject2LongWrappedMap(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new Object2LongWrappedMap<>((Object2LongMap<K>) wrapped);
			}
			return (Object2LongMap<K>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return Object2LongMaps.emptyMap();
	}
	public static IntIterator newIntIterator(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new IntWrappedIterator((IntIterator) wrapped);
			}
			return (IntIterator) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return IntIterators.EMPTY_ITERATOR;
	}
	public static LongIterator newLongIterator(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new LongWrappedIterator((LongIterator) wrapped);
			}
			return (LongIterator) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return LongIterators.EMPTY_ITERATOR;
	}
	public static <T> ObjectIterator<T> newObjectIterator(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new ObjectWrappedIterator<>((ObjectIterator<T>) wrapped);
			}
			return (ObjectIterator<T>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return ObjectIterators.EMPTY_ITERATOR;
	}

	private FastContainers() {
	}
}
