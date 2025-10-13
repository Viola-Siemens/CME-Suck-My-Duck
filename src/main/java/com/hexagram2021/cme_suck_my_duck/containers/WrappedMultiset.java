package com.hexagram2021.cme_suck_my_duck.containers;

import com.google.common.collect.Multiset;
import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedIterator;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;

import javax.annotation.CheckForNull;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

public class WrappedMultiset<T> extends AbstractWrappedContainer<Multiset<T>> implements Multiset<T> {
	WrappedMultiset(Multiset<T> wrapped) {
		super(wrapped);
	}
	WrappedMultiset(Multiset<T> wrapped, String traceId) {
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
	public int count(@CheckForNull Object element) {
		this.logQuery("count(Object)");
		return this.wrapped.count(element);
	}

	@Override
	public int add(T element, int occurrences) {
		this.logModify("add(Object, int)");
		try {
			return this.wrapped.add(element, occurrences);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean add(T element) {
		this.logModify("add(Object)");
		try {
			return this.wrapped.add(element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int remove(@CheckForNull Object element, int occurrences) {
		this.logModify("remove(Object, int)");
		try {
			return this.wrapped.remove(element, occurrences);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(@CheckForNull Object element) {
		this.logModify("remove(Object)");
		try {
			return this.wrapped.remove(element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int setCount(T element, int count) {
		this.logModify("setCount(Object, int)");
		try {
			return this.wrapped.setCount(element, count);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean setCount(T element, int oldCount, int newCount) {
		this.logModify("setCount(Object, int, int)");
		try {
			return this.wrapped.setCount(element, oldCount, newCount);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Set<T> elementSet() {
		return new WrappedSet<>(this.wrapped.elementSet(), this.traceId);
	}

	@Override
	public Set<Entry<T>> entrySet() {
		return new WrappedSet<>(this.wrapped.entrySet(), this.traceId);
	}

	@Override
	public Iterator<T> iterator() {
		this.logIteration("iterator()");
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
	public boolean contains(@CheckForNull Object element) {
		this.logQuery("contains(Object)");
		try {
			return this.wrapped.contains(element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean containsAll(Collection<?> elements) {
		this.logQuery("containsAll(Collection)");
		try {
			return this.wrapped.containsAll(elements);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		this.logModify("addAll(Collection)");
		try {
			return this.wrapped.addAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		this.logModify("removeAll(Collection)");
		try {
			return this.wrapped.removeAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		this.logModify("retainAll(Collection)");
		try {
			return this.wrapped.retainAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Spliterator<T> spliterator() {
		this.logIteration("spliterator()");
		return this.wrapped.spliterator();
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
	public int hashCode() {
		return this.wrapped.hashCode();
	}

	@Override
	public String toString() {
		return this.wrapped.toString();
	}
}
