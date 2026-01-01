package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
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
		this.logQuery("containsKey(long)", Log.LOG_STRATEGY.logAnyway());
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
	public V get(long key) {
		this.logQuery("get(long)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.get(key);
	}

	@Override
	public V put(long key, V value) {
		this.logModify("put(long, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.put(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V remove(long key) {
		this.logModify("remove(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}


	public void putAll(Map<? extends Long, ? extends V> m) {
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
	public void defaultReturnValue(V rv) {
		this.logModify("defaultReturnValue(Object)", Log.LOG_STRATEGY.test(rv));
		this.wrapped.defaultReturnValue(rv);
	}

	@Override
	public V defaultReturnValue() {
		return this.wrapped.defaultReturnValue();
	}

	@Override
	public LongSet keySet() {
		this.logQuery("keySet()", Log.LOG_STRATEGY.logAnyway());
		return new LongWrappedSet(this.wrapped.keySet(), this.traceId);
	}

	@Override
	public ObjectCollection<V> values() {
		this.logQuery("values()", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.values();
	}

	@Override
	public ObjectSet<Entry<V>> long2ObjectEntrySet() {
		this.logQuery("long2ObjectEntrySet()", Log.LOG_STRATEGY.logAnyway());
		return new ObjectWrappedSet<>(this.wrapped.long2ObjectEntrySet(), this.traceId);
	}

	@Override
	public V getOrDefault(long key, V defaultValue) {
		this.logQuery("getOrDefault(long, Object)", Log.LOG_STRATEGY.test(defaultValue));
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super Long, ? super V> action) {
		this.logQuery("forEach(BiConsumer)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.forEach(action);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void replaceAll(BiFunction<? super Long, ? super V, ? extends V> function) {
		this.logModify("replaceAll(BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.replaceAll(function);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V putIfAbsent(long key, V value) {
		this.logModify("putIfAbsent(long, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V putIfAbsent(Long key, V value) {
		this.logModify("putIfAbsent(Long, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(long key, Object value) {
		this.logModify("remove(long, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.remove(key, value);
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
	public boolean replace(long key, V oldValue, V newValue) {
		this.logModify("replace(long, Object, Object)", Log.LOG_STRATEGY.test(newValue));
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V replace(long key, V value) {
		this.logModify("replace(long, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean replace(Long key, V oldValue, V newValue) {
		this.logModify("replace(Long, Object, Object)", Log.LOG_STRATEGY.test(newValue));
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V replace(Long key, V value) {
		this.logModify("replace(Long, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(long key, LongFunction<? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(long, LongFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(long key, Long2ObjectFunction<? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(long, Long2ObjectFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(Long key, Function<? super Long, ? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(Long, Function)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V computeIfPresent(long key, BiFunction<? super Long, ? super V, ? extends V> remappingFunction) {
		this.logModify("computeIfPresent(long, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V computeIfPresent(Long key, BiFunction<? super Long, ? super V, ? extends V> remappingFunction) {
		this.logModify("computeIfPresent(Long, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V compute(long key, BiFunction<? super Long, ? super V, ? extends V> remappingFunction) {
		this.logModify("compute(long, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V compute(Long key, BiFunction<? super Long, ? super V, ? extends V> remappingFunction) {
		this.logModify("compute(Long, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V merge(long key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		this.logModify("merge(long, Object, BiFunction)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V merge(Long key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		this.logModify("merge(Long, Object, BiFunction)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
