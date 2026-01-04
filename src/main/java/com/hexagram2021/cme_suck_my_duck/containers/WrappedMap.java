package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class WrappedMap<K, V> extends AbstractWrappedContainer<Map<K, V>> implements Map<K, V> {
	WrappedMap(Map<K, V> wrapped) {
		super(wrapped);
	}
	WrappedMap(Map<K, V> wrapped, String traceId) {
		super(wrapped, traceId);
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
	public boolean containsValue(Object value) {
		this.logQuery("containsValue(Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.containsValue(value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V get(Object key) {
		this.logQuery("get(Object)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.get(key);
	}

	@Override @Nullable
	public V put(K key, V value) {
		this.logModify("put(Object, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.put(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V remove(Object key) {
		this.logModify("remove(Object)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		this.logModify("putAll(Map)", m.values().stream().anyMatch(Log.LOG_STRATEGY));
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
	public Set<K> keySet() {
		this.logQuery("keySet()", Log.LOG_STRATEGY.logAnyway());
		return new WrappedSet<>(this.wrapped.keySet(), this.traceId);
	}

	@Override
	public Collection<V> values() {
		this.logQuery("values()", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.values();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		this.logQuery("entrySet()", Log.LOG_STRATEGY.logAnyway());
		return new WrappedSet<>(this.wrapped.entrySet(), this.traceId);
	}

	@Override
	public V getOrDefault(Object key, V defaultValue) {
		this.logQuery("getOrDefault(Object, Object)", Log.LOG_STRATEGY.test(defaultValue));
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) {
		this.logQuery("forEach(BiConsumer)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.forEach(action);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
		this.logModify("replaceAll(BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.replaceAll(function);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V putIfAbsent(K key, V value) {
		this.logModify("putIfAbsent(Object, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(Object key, Object value) {
		this.logModify("remove(Object, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.remove(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue) {
		this.logModify("replace(Object, Object, Object)", Log.LOG_STRATEGY.test(newValue));
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V replace(K key, V value) {
		this.logModify("replace(Object, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(Object, Function)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		this.logModify("computeIfPresent(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		this.logModify("compute(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		this.logModify("merge(Object, Object, BiFunction)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
