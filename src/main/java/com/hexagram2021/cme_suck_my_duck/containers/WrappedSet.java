package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedIterator;
import com.hexagram2021.cme_suck_my_duck.utils.TraceLogger;

import java.util.*;

public class WrappedSet<T> implements Set<T> {
	final Set<T> wrapped;

	WrappedSet(Set<T> wrapped) {
		if(wrapped instanceof WrappedSet) {
			this.wrapped = ((WrappedSet<T>)wrapped).wrapped;
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
	public boolean contains(Object o) {
		TraceLogger.debug("[Query] contains(Object)");
		return this.wrapped.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		TraceLogger.info("[Iteration] iterator()");
		return new WrappedIterator<>(this.wrapped.iterator());
	}

	@Override
	public Object[] toArray() {
		return this.wrapped.toArray();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return this.wrapped.toArray(a);
	}

	@Override
	public boolean add(T t) {
		TraceLogger.info("[Modify] add(Object)");
		return this.wrapped.add(t);
	}

	@Override
	public boolean remove(Object o) {
		TraceLogger.info("[Modify] remove(Object)");
		return this.wrapped.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		TraceLogger.debug("[Query] containsAll(Collection)");
		return this.wrapped.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		TraceLogger.info("[Modify] addAll(Collection)");
		return this.wrapped.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		TraceLogger.info("[Modify] removeAll(Collection)");
		return this.wrapped.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		TraceLogger.info("[Modify] retainAll(Collection)");
		return this.wrapped.retainAll(c);
	}

	@Override
	public void clear() {
		TraceLogger.info("[Modify] clear()");
		this.wrapped.clear();
	}

	@Override
	public Spliterator<T> spliterator() {
		return this.wrapped.spliterator();
	}
}
