package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import it.unimi.dsi.fastutil.longs.Long2ObjectFunction;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.LongFunction;

public class Long2ObjectWrappedMap<V> extends AbstractWrappedContainer<Long2ObjectMap<V>> implements Long2ObjectMap<V> {
	Long2ObjectWrappedMap(Long2ObjectMap<V> wrapped) {
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
	public boolean containsKey(long key) {
		this.logQuery("containsKey(long)");
		try {
			return this.wrapped.containsKey(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean containsValue(Object value) {
		this.logQuery("containsValue(Object)");
		try {
			return this.wrapped.containsValue(value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V get(long key) {
		this.logQuery("get(long)");
		return this.wrapped.get(key);
	}

	@Override
	public V put(long key, V value) {
		this.logModify("put(long, Object)");
		try {
			return this.wrapped.put(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V remove(long key) {
		this.logModify("remove(long)");
		try {
			return this.wrapped.remove(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}


	public void putAll(Map<? extends Long, ? extends V> m) {
		this.logModify("putAll(Map)");
		try {
			this.wrapped.putAll(m);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void clear() {
		this.logModify("clear()");
		try {
			this.wrapped.clear();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void defaultReturnValue(V rv) {
		this.logModify("defaultReturnValue(Object)");
		this.wrapped.defaultReturnValue(rv);
	}

	@Override
	public V defaultReturnValue() {
		return this.wrapped.defaultReturnValue();
	}

	@Override
	public LongSet keySet() {
		this.logQuery("keySet()");
		return new LongWrappedSet(this.wrapped.keySet(), this.traceId);
	}

	@Override
	public ObjectCollection<V> values() {
		this.logQuery("values()");
		return this.wrapped.values();
	}

	@Override
	public ObjectSet<Entry<V>> long2ObjectEntrySet() {
		this.logQuery("long2ObjectEntrySet()");
		return new ObjectWrappedSet<>(this.wrapped.long2ObjectEntrySet(), this.traceId);
	}

	@Override
	public V getOrDefault(long key, V defaultValue) {
		this.logQuery("getOrDefault(long, Object)");
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super Long, ? super V> action) {
		this.logQuery("forEach(BiConsumer)");
		try {
			this.wrapped.forEach(action);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void replaceAll(BiFunction<? super Long, ? super V, ? extends V> function) {
		this.logModify("replaceAll(BiFunction)");
		try {
			this.wrapped.replaceAll(function);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V putIfAbsent(long key, V value) {
		this.logModify("putIfAbsent(long, Object)");
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V putIfAbsent(Long key, V value) {
		this.logModify("putIfAbsent(Long, Object)");
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(long key, Object value) {
		this.logModify("remove(long, Object)");
		try {
			return this.wrapped.remove(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(Object key, Object value) {
		this.logModify("remove(Object, Object)");
		try {
			return this.wrapped.remove(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean replace(long key, V oldValue, V newValue) {
		this.logModify("replace(long, Object, Object)");
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V replace(long key, V value) {
		this.logModify("replace(long, Object)");
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean replace(Long key, V oldValue, V newValue) {
		this.logModify("replace(Long, Object, Object)");
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V replace(Long key, V value) {
		this.logModify("replace(Long, Object)");
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(long key, LongFunction<? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(long, LongFunction)");
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(long key, Long2ObjectFunction<? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(long, Long2ObjectFunction)");
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(Long key, Function<? super Long, ? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(Long, Function)");
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V computeIfPresent(long key, BiFunction<? super Long, ? super V, ? extends V> remappingFunction) {
		this.logModify("computeIfPresent(long, BiFunction)");
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V computeIfPresent(Long key, BiFunction<? super Long, ? super V, ? extends V> remappingFunction) {
		this.logModify("computeIfPresent(Long, BiFunction)");
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V compute(long key, BiFunction<? super Long, ? super V, ? extends V> remappingFunction) {
		this.logModify("compute(long, BiFunction)");
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V compute(Long key, BiFunction<? super Long, ? super V, ? extends V> remappingFunction) {
		this.logModify("compute(Long, BiFunction)");
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V merge(long key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		this.logModify("merge(long, Object, BiFunction)");
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V merge(Long key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		this.logModify("merge(Long, Object, BiFunction)");
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
