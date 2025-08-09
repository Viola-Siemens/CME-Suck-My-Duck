package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.TraceLogger;
import it.unimi.dsi.fastutil.ints.IntCollection;
import it.unimi.dsi.fastutil.objects.Object2IntFunction;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.*;

@SuppressWarnings("deprecation")
public class Object2IntWrappedMap<K> implements Object2IntMap<K> {
	final Object2IntMap<K> wrapped;

	public Object2IntWrappedMap(Object2IntMap<K> wrapped) {
		if(wrapped instanceof Object2IntWrappedMap) {
			this.wrapped = ((Object2IntWrappedMap<K>)wrapped).wrapped;
		} else {
			this.wrapped = wrapped;
		}
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
		TraceLogger.debug("[Query] containsKey(Object)");
		return this.wrapped.containsKey(key);
	}

	@Override
	public boolean containsValue(int value) {
		TraceLogger.debug("[Query] containsValue(int)");
		return this.wrapped.containsValue(value);
	}

	@Override
	public Integer get(Object key) {
		TraceLogger.debug("[Query] get(Object)");
		return this.wrapped.get(key);
	}

	@Override
	public int put(K key, int value) {
		TraceLogger.info("[Modify] put(Object, int)");
		return this.wrapped.put(key, value);
	}

	@Override
	public int getInt(Object key) {
		TraceLogger.debug("[Query] getInt(Object)");
		return this.wrapped.getInt(key);
	}

	@Override
	public Integer remove(Object key) {
		TraceLogger.info("[Modify] remove(Object)");
		return this.wrapped.remove(key);
	}

	@Override
	public int removeInt(Object key) {
		TraceLogger.info("[Modify] removeInt(Object)");
		return this.wrapped.removeInt(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends Integer> m) {
		TraceLogger.info("[Modify] putAll(Map)");
		this.wrapped.putAll(m);
	}

	@Override
	public void clear() {
		TraceLogger.info("[Modify] clear()");
		this.wrapped.clear();
	}

	@Override
	public void defaultReturnValue(int rv) {
		TraceLogger.info("[Modify] defaultReturnValue(int)");
		this.wrapped.defaultReturnValue(rv);
	}

	@Override
	public int defaultReturnValue() {
		return this.wrapped.defaultReturnValue();
	}

	@Override
	public ObjectSet<K> keySet() {
		TraceLogger.debug("[Query] keySet()");
		return this.wrapped.keySet();
	}

	@Override
	public IntCollection values() {
		TraceLogger.debug("[Query] values()");
		return this.wrapped.values();
	}

	@Override
	public ObjectSet<Entry<K>> object2IntEntrySet() {
		TraceLogger.debug("[Query] entrySet()");
		return this.wrapped.object2IntEntrySet();
	}

	@Override
	public int getOrDefault(Object key, int defaultValue) {
		TraceLogger.debug("[Query] getOrDefault(Object, int)");
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super Integer> action) {
		TraceLogger.debug("[Query] forEach(BiConsumer)");
		this.wrapped.forEach(action);
	}

	@Override
	public void replaceAll(BiFunction<? super K, ? super Integer, ? extends Integer> function) {
		TraceLogger.info("[Modify] replaceAll(BiFunction)");
		this.wrapped.replaceAll(function);
	}

	@Override
	public int putIfAbsent(K key, int value) {
		TraceLogger.info("[Modify] putIfAbsent(Object, int)");
		return this.wrapped.putIfAbsent(key, value);
	}

	@Override
	public Integer putIfAbsent(K key, Integer value) {
		TraceLogger.info("[Modify] putIfAbsent(Object, Integer)");
		return this.wrapped.putIfAbsent(key, value);
	}

	@Override
	public boolean remove(Object key, int value) {
		TraceLogger.info("[Modify] remove(Object, int)");
		return this.wrapped.remove(key, value);
	}

	@Override
	public boolean remove(Object key, Object value) {
		TraceLogger.info("[Modify] remove(Object, Object)");
		return this.wrapped.remove(key, value);
	}

	@Override
	public boolean replace(K key, int oldValue, int newValue) {
		TraceLogger.info("[Modify] replace(Object, int, int)");
		return this.wrapped.replace(key, oldValue, newValue);
	}

	@Override
	public int replace(K key, int value) {
		TraceLogger.info("[Modify] replace(Object, int)");
		return this.wrapped.replace(key, value);
	}

	@Override
	public boolean replace(K key, Integer oldValue, Integer newValue) {
		TraceLogger.info("[Modify] replace(Object, Integer, Integer)");
		return this.wrapped.replace(key, oldValue, newValue);
	}

	@Override
	public Integer replace(K key, Integer value) {
		TraceLogger.info("[Modify] replace(Object, Integer)");
		return this.wrapped.replace(key, value);
	}

	@Override
	public int computeIfAbsent(K key, ToIntFunction<? super K> mappingFunction) {
		TraceLogger.info("[Modify] computeIfAbsent(Object, ToIntFunction)");
		return this.wrapped.computeIfAbsent(key, mappingFunction);
	}

	@Override
	public int computeIfAbsent(K key, Object2IntFunction<? super K> mappingFunction) {
		TraceLogger.info("[Modify] computeIfAbsent(Object, Object2IntFunction)");
		return this.wrapped.computeIfAbsent(key, mappingFunction);
	}

	@Override
	public Integer computeIfAbsent(K key, Function<? super K, ? extends Integer> mappingFunction) {
		TraceLogger.info("[Modify] computeIfAbsent(Object, Function)");
		return this.wrapped.computeIfAbsent(key, mappingFunction);
	}

	@Override @Nullable
	public Integer computeIfPresent(K key, BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
		TraceLogger.info("[Modify] computeIfPresent(Object, BiFunction)");
		return this.wrapped.computeIfPresent(key, remappingFunction);
	}

	@Override
	public int computeIntIfPresent(K key, BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
		TraceLogger.info("[Modify] computeIntIfPresent(Object, BiFunction)");
		return this.wrapped.computeIntIfPresent(key, remappingFunction);
	}

	@Override
	public Integer compute(K key, BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
		TraceLogger.info("[Modify] compute(Object, BiFunction)");
		return this.wrapped.compute(key, remappingFunction);
	}

	@Override
	public int computeInt(K key, BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
		TraceLogger.info("[Modify] computeInt(Object, BiFunction)");
		return this.wrapped.computeInt(key, remappingFunction);
	}

	@Override
	public int merge(K key, int value, BiFunction<? super Integer, ? super Integer, ? extends Integer> remappingFunction) {
		TraceLogger.info("[Modify] merge(Object, int, BiFunction)");
		return this.wrapped.merge(key, value, remappingFunction);
	}

	@Override
	public int mergeInt(K key, int value, IntBinaryOperator remappingFunction) {
		TraceLogger.info("[Modify] mergeInt(Object, int, IntBinaryOperator)");
		return this.wrapped.mergeInt(key, value, remappingFunction);
	}
}
