package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedIterator;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

public class WrappedSet<T> extends AbstractWrappedContainer<Set<T>> implements Set<T> {
	WrappedSet(Set<T> wrapped) {
		super(wrapped);
	}
	WrappedSet(Set<T> wrapped, String traceId) {
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
	public boolean contains(Object o) {
		this.logQuery("contains(Object)", Log.LOG_STRATEGY.test(o));
		try {
			return this.wrapped.contains(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Iterator<T> iterator() {
		this.logIteration("iterator()", Log.LOG_STRATEGY.logAnyway());
		return new WrappedIterator<>(this.wrapped.iterator(), this.traceId);
	}

	@Override
	public Object[] toArray() {
		try {
			return this.wrapped.toArray();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		try {
			return this.wrapped.toArray(a);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean add(T t) {
		this.logModify("add(Object)", Log.LOG_STRATEGY.test(t));
		try {
			return this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(Object o) {
		this.logModify("remove(Object)", Log.LOG_STRATEGY.test(o));
		try {
			return this.wrapped.remove(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		this.logQuery("containsAll(Collection)", c.stream().anyMatch(Log.LOG_STRATEGY));
		try {
			return this.wrapped.containsAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		this.logModify("addAll(Collection)", c.stream().anyMatch(Log.LOG_STRATEGY));
		try {
			return this.wrapped.addAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		this.logModify("removeAll(Collection)", c.stream().anyMatch(Log.LOG_STRATEGY));
		try {
			return this.wrapped.removeAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		this.logModify("retainAll(Collection)", c.stream().anyMatch(Log.LOG_STRATEGY));
		try {
			return this.wrapped.retainAll(c);
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
	public Spliterator<T> spliterator() {
		this.logIteration("spliterator()", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.spliterator();
	}
}
