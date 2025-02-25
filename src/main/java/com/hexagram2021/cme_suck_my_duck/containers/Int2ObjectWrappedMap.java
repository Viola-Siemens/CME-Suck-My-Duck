package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.SuckTraceException;

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

import static com.hexagram2021.cme_suck_my_duck.containers.Containers.logger;

public class Int2ObjectWrappedMap<V> implements Int2ObjectMap<V> {
	final Int2ObjectMap<V> wrapped;

	public Int2ObjectWrappedMap(Int2ObjectMap<V> wrapped) {
		if(wrapped instanceof Int2ObjectWrappedMap) {
			this.wrapped = ((Int2ObjectWrappedMap<V>)wrapped).wrapped;
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
	public boolean containsKey(int key) {
		logger.debug(new SuckTraceException("[Query] containsKey(int)"));
		return this.wrapped.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		logger.debug(new SuckTraceException("[Query] containsValue(Object)"));
		return this.wrapped.containsValue(value);
	}

	@Override
	public V get(int key) {
		logger.debug(new SuckTraceException("[Query] get(int)"));
		return this.wrapped.get(key);
	}

	@Override
	public V put(int key, V value) {
		logger.info(new SuckTraceException("[Modify] put(int, Object)"));
		return this.wrapped.put(key, value);
	}

	@Override
	public V remove(int key) {
		logger.info(new SuckTraceException("[Modify] remove(int)"));
		return this.wrapped.remove(key);
	}


	public void putAll(Map<? extends Integer, ? extends V> m) {
		logger.info(new SuckTraceException("[Modify] putAll(Map)"));
		this.wrapped.putAll(m);
	}

	@Override
	public void clear() {
		logger.info(new SuckTraceException("[Modify] clear()"));
		this.wrapped.clear();
	}

	@Override
	public void defaultReturnValue(V rv) {
		logger.info(new SuckTraceException("[Modify] defaultReturnValue(Object)"));
		this.wrapped.defaultReturnValue(rv);
	}

	@Override
	public V defaultReturnValue() {
		return this.wrapped.defaultReturnValue();
	}

	@Override
	public IntSet keySet() {
		logger.debug(new SuckTraceException("[Query] keySet()"));
		return this.wrapped.keySet();
	}

	@Override
	public ObjectCollection<V> values() {
		logger.debug(new SuckTraceException("[Query] values()"));
		return this.wrapped.values();
	}

	@Override
	public ObjectSet<Entry<V>> int2ObjectEntrySet() {
		logger.debug(new SuckTraceException("[Query] entrySet()"));
		return this.wrapped.int2ObjectEntrySet();
	}

	@Override
	public V getOrDefault(int key, V defaultValue) {
		logger.debug(new SuckTraceException("[Query] getOrDefault(int, Object)"));
		return this.wrapped.getOrDefault(key, defaultValue);
	}

	@Override
	public void forEach(BiConsumer<? super Integer, ? super V> action) {
		logger.debug(new SuckTraceException("[Query] forEach(BiConsumer)"));
		this.wrapped.forEach(action);
	}

	@Override
	public void replaceAll(BiFunction<? super Integer, ? super V, ? extends V> function) {
		logger.info(new SuckTraceException("[Modify] replaceAll(BiFunction)"));
		this.wrapped.replaceAll(function);
	}

	@Override
	public V putIfAbsent(int key, V value) {
		logger.info(new SuckTraceException("[Modify] putIfAbsent(int, Object)"));
		return this.wrapped.putIfAbsent(key, value);
	}

	@Override
	public V putIfAbsent(Integer key, V value) {
		logger.info(new SuckTraceException("[Modify] putIfAbsent(Integer, Object)"));
		return this.wrapped.putIfAbsent(key, value);
	}

	@Override
	public boolean remove(int key, Object value) {
		logger.info(new SuckTraceException("[Modify] remove(int, Object)"));
		return this.wrapped.remove(key, value);
	}

	@Override
	public boolean remove(Object key, Object value) {
		logger.info(new SuckTraceException("[Modify] remove(Object, Object)"));
		return this.wrapped.remove(key, value);
	}

	@Override
	public boolean replace(int key, V oldValue, V newValue) {
		logger.info(new SuckTraceException("[Modify] replace(int, Object, Object)"));
		return this.wrapped.replace(key, oldValue, newValue);
	}

	@Override
	public V replace(int key, V value) {
		logger.info(new SuckTraceException("[Modify] replace(int, Object)"));
		return this.wrapped.replace(key, value);
	}

	@Override
	public boolean replace(Integer key, V oldValue, V newValue) {
		logger.info(new SuckTraceException("[Modify] replace(Integer, Object, Object)"));
		return this.wrapped.replace(key, oldValue, newValue);
	}

	@Override
	public V replace(Integer key, V value) {
		logger.info(new SuckTraceException("[Modify] replace(Integer, Object)"));
		return this.wrapped.replace(key, value);
	}

	@Override
	public V computeIfAbsent(int key, IntFunction<? extends V> mappingFunction) {
		logger.info(new SuckTraceException("[Modify] computeIfAbsent(int, IntFunction)"));
		return this.wrapped.computeIfAbsent(key, mappingFunction);
	}

	@Override
	public V computeIfAbsent(int key, Int2ObjectFunction<? extends V> mappingFunction) {
		logger.info(new SuckTraceException("[Modify] computeIfAbsent(int, Int2ObjectFunction)"));
		return this.wrapped.computeIfAbsent(key, mappingFunction);
	}

	@Override
	public V computeIfAbsent(Integer key, Function<? super Integer, ? extends V> mappingFunction) {
		logger.info(new SuckTraceException("[Modify] computeIfAbsent(Integer, Function)"));
		return this.wrapped.computeIfAbsent(key, mappingFunction);
	}

	@Override @Nullable
	public V computeIfPresent(int key, BiFunction<? super Integer, ? super V, ? extends V> remappingFunction) {
		logger.info(new SuckTraceException("[Modify] computeIfPresent(int, BiFunction)"));
		return this.wrapped.computeIfPresent(key, remappingFunction);
	}

	@Override @Nullable
	public V computeIfPresent(Integer key, BiFunction<? super Integer, ? super V, ? extends V> remappingFunction) {
		logger.info(new SuckTraceException("[Modify] computeIfPresent(Integer, BiFunction)"));
		return this.wrapped.computeIfPresent(key, remappingFunction);
	}

	@Override
	public V compute(int key, BiFunction<? super Integer, ? super V, ? extends V> remappingFunction) {
		logger.info(new SuckTraceException("[Modify] compute(int, BiFunction)"));
		return this.wrapped.compute(key, remappingFunction);
	}

	@Override
	public V compute(Integer key, BiFunction<? super Integer, ? super V, ? extends V> remappingFunction) {
		logger.info(new SuckTraceException("[Modify] compute(Integer, BiFunction)"));
		return this.wrapped.compute(key, remappingFunction);
	}

	@Override
	public V merge(int key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		logger.info(new SuckTraceException("[Modify] merge(int, Object, BiFunction)"));
		return this.wrapped.merge(key, value, remappingFunction);
	}

	@Override
	public V merge(Integer key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
		logger.info(new SuckTraceException("[Modify] merge(Integer, Object, BiFunction)"));
		return this.wrapped.merge(key, value, remappingFunction);
	}
}
