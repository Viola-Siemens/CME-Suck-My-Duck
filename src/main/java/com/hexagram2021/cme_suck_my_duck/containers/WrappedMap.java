package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.TraceLogger;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.*;

public class WrappedMap<K, V> implements Map<K, V> {
	final Map<K, V> wrapped;

	public WrappedMap(Map<K, V> wrapped) {
		if(wrapped instanceof WrappedMap) {
			this.wrapped = ((WrappedMap<K, V>)wrapped).wrapped;
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
	public boolean containsValue(Object value) {
		TraceLogger.debug("[Query] containsValue(Object)");
		return this.wrapped.containsValue(value);
	}

	@Override
	public V get(Object key) {
		TraceLogger.debug("[Query] get(Object)");
		return this.wrapped.get(key);
	}

	@Override
	public V put(K key, V value) {
		TraceLogger.info("[Modify] put(Object, Object)");
		return this.wrapped.put(key, value);
	}

	@Override
	public V remove(Object key) {
		TraceLogger.info("[Modify] remove(Object)");
		return this.wrapped.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		TraceLogger.info("[Modify] putAll(Map)");
		this.wrapped.putAll(m);
	}

	@Override
	public void clear() {
		TraceLogger.info("[Modify] clear()");
		this.wrapped.clear();
	}

	@Override
	public Set<K> keySet() {
		TraceLogger.debug("[Query] keySet()");
		return this.wrapped.keySet();
	}

	@Override
	public Collection<V> values() {
		TraceLogger.debug("[Query] values()");
		return this.wrapped.values();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		TraceLogger.debug("[Query] entrySet()");
		return this.wrapped.entrySet();
	}

	@Override
	public V getOrDefault(Object key, V defaultValue) {
		TraceLogger.debug("[Query] getOrDefault(Object, Object)");
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) {
		TraceLogger.debug("[Query] forEach(BiConsumer)");
		this.wrapped.forEach(action);
	}

	@Override
	public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
		TraceLogger.info("[Modify] replaceAll(BiFunction)");
		this.wrapped.replaceAll(function);
	}

	@Override
	public V putIfAbsent(K key, V value) {
		TraceLogger.info("[Modify] putIfAbsent(Object, Object)");
		return this.wrapped.putIfAbsent(key, value);
	}

	@Override
	public boolean remove(Object key, Object value) {
		TraceLogger.info("[Modify] remove(Object, Object)");
		return this.wrapped.remove(key, value);
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue) {
		TraceLogger.info("[Modify] replace(Object, Object, Object)");
		return this.wrapped.replace(key, oldValue, newValue);
	}

	@Override
	public V replace(K key, V value) {
		TraceLogger.info("[Modify] replace(Object, Object)");
		return this.wrapped.replace(key, value);
	}

	@Override
	public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
		TraceLogger.info("[Modify] computeIfAbsent(Object, Function)");
		return this.wrapped.computeIfAbsent(key, mappingFunction);
	}

	@Override @Nullable
	public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		TraceLogger.info("[Modify] computeIfPresent(Object, BiFunction)");
		return this.wrapped.computeIfPresent(key, remappingFunction);
	}

	@Override
	public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
		TraceLogger.info("[Modify] compute(Object, BiFunction)");
		return this.wrapped.compute(key, remappingFunction);
	}

	@Override
	public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		TraceLogger.info("[Modify] merge(Object, Object, BiFunction)");
		return this.wrapped.merge(key, value, remappingFunction);
	}
}
