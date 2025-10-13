package com.hexagram2021.cme_suck_my_duck.containers;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;

import javax.annotation.CheckForNull;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class WrappedMultimap<K, V> extends AbstractWrappedContainer<Multimap<K, V>> implements Multimap<K, V> {
	WrappedMultimap(Multimap<K, V> wrapped) {
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
	public boolean containsKey(@CheckForNull Object key) {
		this.logQuery("containsKey(Object)");
		try {
			return this.wrapped.containsKey(key);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean containsValue(@CheckForNull Object value) {
		this.logQuery("containsValue(Object)");
		try {
			return this.wrapped.containsValue(value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean containsEntry(@CheckForNull Object key, @CheckForNull Object value) {
		this.logQuery("containsEntry(Object, Object)");
		try {
			return this.wrapped.containsEntry(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Collection<V> get(K key) {
		this.logQuery("get(Object)");
		return this.wrapped.get(key);
	}

	@Override
	public boolean put(K key, V value) {
		this.logModify("put(Object, Object)");
		try {
			return this.wrapped.put(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
		this.logModify("remove(Object, Object)");
		try {
			return this.wrapped.remove(key, value);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean putAll(K key, Iterable<? extends V> values) {
		this.logModify("putAll(Object, Iterable)");
		try {
			return this.wrapped.putAll(key, values);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean putAll(Multimap<? extends K, ? extends V> m) {
		this.logModify("putAll(Multimap)");
		try {
			return this.wrapped.putAll(m);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Collection<V> replaceValues(K key, Iterable<? extends V> values) {
		this.logModify("replaceValues(Object, Iterable)");
		try {
			return this.wrapped.replaceValues(key, values);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Collection<V> removeAll(@CheckForNull Object key) {
		this.logModify("removeAll(Object)");
		try {
			return this.wrapped.removeAll(key);
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
	public Set<K> keySet() {
		this.logQuery("keySet()");
		return new WrappedSet<>(this.wrapped.keySet(), this.traceId);
	}

	@Override
	public Multiset<K> keys() {
		this.logQuery("keys()");
		return new WrappedMultiset<>(this.wrapped.keys(), this.traceId);
	}

	@Override
	public Collection<V> values() {
		this.logQuery("values()");
		return this.wrapped.values();
	}

	@Override
	public Collection<Map.Entry<K, V>> entries() {
		this.logQuery("entries()");
		return this.wrapped.entries();
	}

	@Override
	public Map<K, Collection<V>> asMap() {
		return new WrappedMap<>(this.wrapped.asMap(), this.traceId);
	}
}
