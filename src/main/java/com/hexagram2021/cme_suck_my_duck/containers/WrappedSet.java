package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.SuckTraceException;

import java.util.*;

import static com.hexagram2021.cme_suck_my_duck.containers.Containers.logger;

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
		logger.debug(new SuckTraceException("[Query] contains(Object)"));
		return this.wrapped.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		logger.info(new SuckTraceException("[Iteration] iterator()"));
		return this.wrapped.iterator();
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
		logger.info(new SuckTraceException("[Modify] add(Object)"));
		return this.wrapped.add(t);
	}

	@Override
	public boolean remove(Object o) {
		logger.info(new SuckTraceException("[Modify] remove(Object)"));
		return this.wrapped.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		logger.debug(new SuckTraceException("[Query] containsAll(Collection)"));
		return this.wrapped.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		logger.info(new SuckTraceException("[Modify] addAll(Collection)"));
		return this.wrapped.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		logger.info(new SuckTraceException("[Modify] removeAll(Collection)"));
		return this.wrapped.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		logger.info(new SuckTraceException("[Modify] retainAll(Collection)"));
		return this.wrapped.retainAll(c);
	}

	@Override
	public void clear() {
		logger.info(new SuckTraceException("[Modify] clear()"));
		this.wrapped.clear();
	}

	@Override
	public Spliterator<T> spliterator() {
		return this.wrapped.spliterator();
	}
}
