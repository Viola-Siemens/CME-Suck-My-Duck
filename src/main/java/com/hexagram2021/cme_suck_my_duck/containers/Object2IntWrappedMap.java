package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.Object2IntFunction;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.*;

@SuppressWarnings("deprecation")
public class Object2IntWrappedMap<K> extends AbstractWrappedContainer<Object2IntMap<K>> implements Object2IntMap<K> {
	Object2IntWrappedMap(Object2IntMap<K> wrapped) {
		super(wrapped);
	}

	@Override
	public int size() {
		return this.wrapped.size();
	}

	@Override
	public boolean isEmpty() {
		return this.wrapped.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		this.logQuery("containsKey(Object)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.containsKey(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean containsValue(int value) {
		this.logQuery("containsValue(int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.containsValue(value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Integer get(Object key) {
		this.logQuery("get(Object)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.get(key);
	}

	@Override
	public int put(K key, int value) {
		this.logModify("put(Object, int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.put(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int getInt(Object key) {
		this.logQuery("getInt(Object)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.getInt(key);
	}

	@Override
	public Integer remove(Object key) {
		this.logModify("remove(Object)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int removeInt(Object key) {
		this.logModify("removeInt(Object)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.removeInt(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends Integer> m) {
		this.logModify("putAll(Map)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.putAll(m);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void clear() {
		this.logModify("clear()", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.clear();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void defaultReturnValue(int rv) {
		this.logModify("defaultReturnValue(int)", Log.LOG_STRATEGY.logAnyway());
		this.wrapped.defaultReturnValue(rv);
	}

	@Override
	public int defaultReturnValue() {
		return this.wrapped.defaultReturnValue();
	}

	@Override
	public ObjectSet<K> keySet() {
		this.logQuery("keySet()", Log.LOG_STRATEGY.logAnyway());
		return new ObjectWrappedSet<>(this.wrapped.keySet(), this.traceId);
	}

	@Override
	public IntCollection values() {
		this.logQuery("values()", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.values();
	}

	@Override
	public ObjectSet<Entry<K>> object2IntEntrySet() {
		this.logQuery("entrySet()", Log.LOG_STRATEGY.logAnyway());
		return new ObjectWrappedSet<>(this.wrapped.object2IntEntrySet(), this.traceId);
	}

	@Override
	public int getOrDefault(Object key, int defaultValue) {
		this.logQuery("getOrDefault(Object, int)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super Integer> action) {
		this.logQuery("forEach(BiConsumer)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.forEach(action);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void replaceAll(BiFunction<? super K, ? super Integer, ? extends Integer> function) {
		this.logModify("replaceAll(BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.replaceAll(function);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int putIfAbsent(K key, int value) {
		this.logModify("putIfAbsent(Object, int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public Integer putIfAbsent(K key, Integer value) {
		this.logModify("putIfAbsent(Object, Integer)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(Object key, int value) {
		this.logModify("remove(Object, int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(Object key, Object value) {
		this.logModify("remove(Object, Object)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean replace(K key, int oldValue, int newValue) {
		this.logModify("replace(Object, int, int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int replace(K key, int value) {
		this.logModify("replace(Object, int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean replace(K key, Integer oldValue, Integer newValue) {
		this.logModify("replace(Object, Integer, Integer)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public Integer replace(K key, Integer value) {
		this.logModify("replace(Object, Integer)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int computeIfAbsent(K key, ToIntFunction<? super K> mappingFunction) {
		this.logModify("computeIfAbsent(Object, ToIntFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int computeIfAbsent(K key, Object2IntFunction<? super K> mappingFunction) {
		this.logModify("computeIfAbsent(Object, Object2IntFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Integer computeIfAbsent(K key, Function<? super K, ? extends Integer> mappingFunction) {
		this.logModify("computeIfAbsent(Object, Function)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public Integer computeIfPresent(K key, BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
		this.logModify("computeIfPresent(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int computeIntIfPresent(K key, BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
		this.logModify("computeIntIfPresent(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIntIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Integer compute(K key, BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
		this.logModify("compute(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int computeInt(K key, BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
		this.logModify("computeInt(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeInt(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int merge(K key, int value, BiFunction<? super Integer, ? super Integer, ? extends Integer> remappingFunction) {
		this.logModify("merge(Object, int, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int mergeInt(K key, int value, IntBinaryOperator remappingFunction) {
		this.logModify("mergeInt(Object, int, IntBinaryOperator)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.mergeInt(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
