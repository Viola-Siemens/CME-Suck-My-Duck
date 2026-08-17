package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.floats.FloatBinaryOperator;
import it.unimi.dsi.fastutil.floats.FloatCollection;
import it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.*;

@SuppressWarnings("deprecation")
public class Object2FloatWrappedMap<K> extends AbstractWrappedContainer<Object2FloatMap<K>> implements Object2FloatMap<K> {
	Object2FloatWrappedMap(Object2FloatMap<K> wrapped) {
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
	public boolean containsValue(float value) {
		this.logQuery("containsValue(float)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.containsValue(value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Float get(Object key) {
		this.logQuery("get(Object)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.get(key);
	}

	@Override
	public float put(K key, float value) {
		this.logModify("put(Object, float)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.put(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public float getFloat(Object key) {
		this.logQuery("getFloat(Object)", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.getFloat(key);
	}

	@Override
	public Float remove(Object key) {
		this.logModify("remove(Object)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public float removeFloat(Object key) {
		this.logModify("removeFloat(Object)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.removeFloat(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends Float> m) {
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
	public void defaultReturnValue(float rv) {
		this.logModify("defaultReturnValue(float)", Log.LOG_STRATEGY.test(rv));
		this.wrapped.defaultReturnValue(rv);
	}

	@Override
	public float defaultReturnValue() {
		return this.wrapped.defaultReturnValue();
	}

	@Override
	public ObjectSet<K> keySet() {
		this.logQuery("keySet()", Log.LOG_STRATEGY.logAnyway());
		return new ObjectWrappedSet<>(this.wrapped.keySet(), this.traceId);
	}

	@Override
	public FloatCollection values() {
		this.logQuery("values()", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.values();
	}

	@Override
	public ObjectSet<Entry<K>> object2FloatEntrySet() {
		this.logQuery("entrySet()", Log.LOG_STRATEGY.logAnyway());
		return new ObjectWrappedSet<>(this.wrapped.object2FloatEntrySet(), this.traceId);
	}

	@Override
	public float getOrDefault(Object key, float defaultValue) {
		this.logQuery("getOrDefault(Object, float)", Log.LOG_STRATEGY.test(defaultValue));
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super Float> action) {
		this.logQuery("forEach(BiConsumer)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.forEach(action);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void replaceAll(BiFunction<? super K, ? super Float, ? extends Float> function) {
		this.logModify("replaceAll(BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.replaceAll(function);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public float putIfAbsent(K key, float value) {
		this.logModify("putIfAbsent(Object, float)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public Float putIfAbsent(K key, Float value) {
		this.logModify("putIfAbsent(Object, Float)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.putIfAbsent(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(Object key, float value) {
		this.logModify("remove(Object, float)", Log.LOG_STRATEGY.test(value));
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
	public boolean replace(K key, float oldValue, float newValue) {
		this.logModify("replace(Object, float, float)", Log.LOG_STRATEGY.test(newValue));
		try {
			return this.wrapped.replace(key, oldValue, newValue);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public float replace(K key, float value) {
		this.logModify("replace(Object, float)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
	
	@Override @Nullable
	public Float replace(K key, Float value) {
		this.logModify("replace(Object, Float)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.replace(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public float computeIfAbsent(K key, Object2FloatFunction<? super K> mappingFunction) {
		this.logModify("computeIfAbsent(Object, Object2FloatFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Float computeIfAbsent(K key, Function<? super K, ? extends Float> mappingFunction) {
		this.logModify("computeIfAbsent(Object, Function)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfAbsent(key, mappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override @Nullable
	public Float computeIfPresent(K key, BiFunction<? super K, ? super Float, ? extends Float> remappingFunction) {
		this.logModify("computeIfPresent(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public float computeFloatIfPresent(K key, BiFunction<? super K, ? super Float, ? extends Float> remappingFunction) {
		this.logModify("computeFloatIfPresent(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeFloatIfPresent(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Float compute(K key, BiFunction<? super K, ? super Float, ? extends Float> remappingFunction) {
		this.logModify("compute(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.compute(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public float computeFloat(K key, BiFunction<? super K, ? super Float, ? extends Float> remappingFunction) {
		this.logModify("computeFloat(Object, BiFunction)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.computeFloat(key, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
	
	@Override
	public float merge(K key, float value, BiFunction<? super Float, ? super Float, ? extends Float> remappingFunction) {
		this.logModify("merge(Object, float, BiFunction)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.merge(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public float mergeFloat(K key, float value, FloatBinaryOperator remappingFunction) {
		this.logModify("mergeFloat(Object, float, FloatBinaryOperator)", Log.LOG_STRATEGY.test(value));
		try {
			return this.wrapped.mergeFloat(key, value, remappingFunction);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
