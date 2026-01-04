package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.objects.Object2LongFunction;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.*;

@SuppressWarnings("deprecation")
public class Object2LongWrappedMap<K> extends AbstractWrappedContainer<Object2LongMap<K>> implements Object2LongMap<K> {
	Object2LongWrappedMap(Object2LongMap<K> wrapped) {
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
	public boolean containsValue(long value) {
		this.logQuery("containsValue(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.containsValue(value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Long get(Object key) {
		this.logQuery("get(Object)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.get(key);
	}

	@Override
	public long put(K key, long value) {
		this.logModify("put(Object, long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.put(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long getLong(Object key) {
		this.logQuery("getLong(Object)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.getLong(key);
	}

	@Override
	public Long remove(Object key) {
		this.logModify("remove(Object)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long removeLong(Object key) {
		this.logModify("removeLong(Object)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.removeLong(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends Long> m) {
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
	public void defaultReturnValue(long rv) {
		this.logModify("defaultReturnValue(long)", Log.LOG_STRATEGY.logAnyway());
		this.wrapped.defaultReturnValue(rv);
	}

	@Override
	public long defaultReturnValue() {
		return this.wrapped.defaultReturnValue();
	}

	@Override
	public ObjectSet<K> keySet() {
		this.logQuery("keySet()", Log.LOG_STRATEGY.logAnyway());
		return new ObjectWrappedSet<>(this.wrapped.keySet(), this.traceId);
	}

	@Override
	public LongCollection values() {
		this.logQuery("values()", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.values();
	}

	@Override
	public ObjectSet<Entry<K>> object2LongEntrySet() {
		this.logQuery("entrySet()", Log.LOG_STRATEGY.logAnyway());
		return new ObjectWrappedSet<>(this.wrapped.object2LongEntrySet(), this.traceId);
	}

	@Override
	public long getOrDefault(Object key, long defaultValue) {
		this.logQuery("getOrDefault(Object, long)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super Long> action) {
		this.logQuery("forEach(BiConsumer)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.forEach(action);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void replaceAll(BiFunction<? super K, ? super Long, ? extends Long> function) {
		this.logModify("replaceAll(BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.replaceAll(function);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long putIfAbsent(K key, long value) {
		this.logModify("putIfAbsent(Object, long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public Long putIfAbsent(K key, Long value) {
		this.logModify("putIfAbsent(Object, Long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(Object key, long value) {
		this.logModify("remove(Object, long)", Log.LOG_STRATEGY.logAnyway());
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
	public boolean replace(K key, long oldValue, long newValue) {
		this.logModify("replace(Object, long, long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long replace(K key, long value) {
		this.logModify("replace(Object, long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean replace(K key, Long oldValue, Long newValue) {
		this.logModify("replace(Object, Long, Long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public Long replace(K key, Long value) {
		this.logModify("replace(Object, Long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long computeIfAbsent(K key, ToLongFunction<? super K> mappingFunction) {
		this.logModify("computeIfAbsent(Object, ToLongFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long computeIfAbsent(K key, Object2LongFunction<? super K> mappingFunction) {
		this.logModify("computeIfAbsent(Object, Object2LongFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Long computeIfAbsent(K key, Function<? super K, ? extends Long> mappingFunction) {
		this.logModify("computeIfAbsent(Object, Function)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public Long computeIfPresent(K key, BiFunction<? super K, ? super Long, ? extends Long> remappingFunction) {
		this.logModify("computeIfPresent(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long computeLongIfPresent(K key, BiFunction<? super K, ? super Long, ? extends Long> remappingFunction) {
		this.logModify("computeLongIfPresent(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeLongIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Long compute(K key, BiFunction<? super K, ? super Long, ? extends Long> remappingFunction) {
		this.logModify("compute(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long computeLong(K key, BiFunction<? super K, ? super Long, ? extends Long> remappingFunction) {
		this.logModify("computeLong(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeLong(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long merge(K key, long value, BiFunction<? super Long, ? super Long, ? extends Long> remappingFunction) {
		this.logModify("merge(Object, long, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long mergeLong(K key, long value, LongBinaryOperator remappingFunction) {
		this.logModify("mergeLong(Object, long, LongBinaryOperator)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.mergeLong(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
