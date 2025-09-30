package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.*;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.ints.*;
import it.unimi.dsi.fastutil.longs.*;
import it.unimi.dsi.fastutil.objects.*;

import static com.hexagram2021.cme_suck_my_duck.containers.Containers.logger;
import static com.hexagram2021.cme_suck_my_duck.containers.Containers.transformToThreadSafe;

@SuppressWarnings("unchecked")
public final class FastContainers {
	public static IntList newIntWrappedList(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(transformToThreadSafe) {
					return IntLists.synchronize((IntList) wrapped);
				}
				return new IntWrappedList((IntList) wrapped);
			}
			return (IntList) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return IntLists.emptyList();
	}

	public static LongList newLongWrappedList(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(transformToThreadSafe) {
					return LongLists.synchronize((LongList) wrapped);
				}
				return new LongWrappedList((LongList) wrapped);
			}
			return (LongList) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return LongLists.emptyList();
	}

	public static <T> ObjectList<T> newObjectWrappedList(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(transformToThreadSafe) {
					return ObjectLists.synchronize((ObjectList<T>) wrapped);
				}
				return new ObjectWrappedList<>((ObjectList<T>) wrapped);
			}
			return (ObjectList<T>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return ObjectLists.emptyList();
	}

	public static IntSet newIntWrappedSet(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(transformToThreadSafe) {
					return IntSets.synchronize((IntSet) wrapped);
				}
				return new IntWrappedSet((IntSet) wrapped);
			}
			return (IntSet) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return IntSets.emptySet();
	}
	public static LongSet newLongWrappedSet(Object wrapped) {
		try {
			if(Log.canWrap()) {
				if(transformToThreadSafe) {
					return LongSets.synchronize((LongSet) wrapped);
				}
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
				if(transformToThreadSafe) {
					return ObjectSets.synchronize((ObjectSet<T>) wrapped);
				}
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
				if(transformToThreadSafe) {
					return Int2ObjectMaps.synchronize((Int2ObjectMap<V>) wrapped);
				}
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
				if(transformToThreadSafe) {
					return Object2IntMaps.synchronize((Object2IntMap<K>) wrapped);
				}
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
				if(transformToThreadSafe) {
					return Long2ObjectMaps.synchronize((Long2ObjectMap<V>) wrapped);
				}
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
				if(transformToThreadSafe) {
					return Object2LongMaps.synchronize((Object2LongMap<K>) wrapped);
				}
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

	public static IntListIterator newIntListIterator(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new IntWrappedListIterator((IntListIterator) wrapped);
			}
			return (IntListIterator) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return IntIterators.EMPTY_ITERATOR;
	}
	public static LongListIterator newLongListIterator(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new LongWrappedListIterator((LongListIterator) wrapped);
			}
			return (LongListIterator) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return LongIterators.EMPTY_ITERATOR;
	}
	public static <T> ObjectListIterator<T> newObjectListIterator(Object wrapped) {
		try {
			if(Log.canWrap()) {
				return new ObjectWrappedListIterator<>((ObjectListIterator<T>) wrapped);
			}
			return (ObjectListIterator<T>) wrapped;
		} catch (ClassCastException e) {
			logger.fatal(e);
		}
		return ObjectIterators.EMPTY_ITERATOR;
	}

	private FastContainers() {
	}
}
