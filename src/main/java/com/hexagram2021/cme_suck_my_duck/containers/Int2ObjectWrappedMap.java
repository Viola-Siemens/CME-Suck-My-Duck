package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.ints.Int2ObjectFunction;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

public class Int2ObjectWrappedMap<V> extends AbstractWrappedContainer<Int2ObjectMap<V>> implements Int2ObjectMap<V> {
	Int2ObjectWrappedMap(Int2ObjectMap<V> wrapped) {
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
	public boolean containsKey(int key) {
		this.logQuery("containsKey(int)", Log.LOG_STRATEGY.logAnyway());
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
	public V get(int key) {
		this.logQuery("get(int)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.get(key);
	}

	@Override
	public V put(int key, V value) {
		this.logModify("put(int, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.put(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V remove(int key) {
		this.logModify("remove(int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}


	public void putAll(Map<? extends Integer, ? extends V> m) {
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
	public IntSet keySet() {
		this.logQuery("keySet()", Log.LOG_STRATEGY.logAnyway());
		return new IntWrappedSet(this.wrapped.keySet(), this.traceId);
	}

	@Override
	public ObjectCollection<V> values() {
		this.logQuery("values()", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.values();
	}

	@Override
	public ObjectSet<Entry<V>> int2ObjectEntrySet() {
		this.logQuery("int2ObjectEntrySet()", Log.LOG_STRATEGY.logAnyway());
		return new ObjectWrappedSet<>(this.wrapped.int2ObjectEntrySet(), this.traceId);
	}

	@Override
	public V getOrDefault(int key, V defaultValue) {
		this.logQuery("getOrDefault(int, Object)", Log.LOG_STRATEGY.test(defaultValue));
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super Integer, ? super V> action) {
		this.logQuery("forEach(BiConsumer)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.forEach(action);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void replaceAll(BiFunction<? super Integer, ? super V, ? extends V> function) {
		this.logModify("replaceAll(BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.replaceAll(function);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V putIfAbsent(int key, V value) {
		this.logModify("putIfAbsent(int, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V putIfAbsent(Integer key, V value) {
		this.logModify("putIfAbsent(Integer, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(int key, Object value) {
		this.logModify("remove(int, Object)", Log.LOG_STRATEGY.test(value));
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
	public boolean replace(int key, V oldValue, V newValue) {
		this.logModify("replace(int, Object, Object)", Log.LOG_STRATEGY.test(newValue));
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V replace(int key, V value) {
		this.logModify("replace(int, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean replace(Integer key, V oldValue, V newValue) {
		this.logModify("replace(Integer, Object, Object)", Log.LOG_STRATEGY.test(newValue));
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V replace(Integer key, V value) {
		this.logModify("replace(Integer, Object)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(int key, IntFunction<? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(int, IntFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(int key, Int2ObjectFunction<? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(int, Int2ObjectFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V computeIfAbsent(Integer key, Function<? super Integer, ? extends V> mappingFunction) {
		this.logModify("computeIfAbsent(Integer, Function)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V computeIfPresent(int key, BiFunction<? super Integer, ? super V, ? extends V> remappingFunction) {
		this.logModify("computeIfPresent(int, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public V computeIfPresent(Integer key, BiFunction<? super Integer, ? super V, ? extends V> remappingFunction) {
		this.logModify("computeIfPresent(Integer, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V compute(int key, BiFunction<? super Integer, ? super V, ? extends V> remappingFunction) {
		this.logModify("compute(int, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V compute(Integer key, BiFunction<? super Integer, ? super V, ? extends V> remappingFunction) {
		this.logModify("compute(Integer, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V merge(int key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		this.logModify("merge(int, Object, BiFunction)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public V merge(Integer key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		this.logModify("merge(Integer, Object, BiFunction)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
