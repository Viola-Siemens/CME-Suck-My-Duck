package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.Log;
import com.hexagram2021.cme_suck_my_duck.utils.SuckTraceException;

import java.util.*;

public class WrappedSet<T> implements Set<T> {
	final Set<T> wrapped;

	WrappedSet(Set<T> wrapped) {
		this.wrapped = wrapped;
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
		Log.debug(new SuckTraceException("[Query] contains(Object)"));
		return this.wrapped.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		Log.debug(new SuckTraceException("[Iteration] iterator()"));
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
		Log.info(new SuckTraceException("[Modify] add(Object)"));
		return this.wrapped.add(t);
	}

	@Override
	public boolean remove(Object o) {
		Log.info(new SuckTraceException("[Modify] remove(Object)"));
		return this.wrapped.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		Log.debug(new SuckTraceException("[Query] containsAll(Collection)"));
		return this.wrapped.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		Log.info(new SuckTraceException("[Modify] addAll(Collection)"));
		return this.wrapped.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Log.info(new SuckTraceException("[Modify] removeAll(Collection)"));
		return this.wrapped.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		Log.info(new SuckTraceException("[Modify] retainAll(Collection)"));
		return this.wrapped.retainAll(c);
	}

	@Override
	public void clear() {
		Log.info(new SuckTraceException("[Modify] clear()"));
		this.wrapped.clear();
	}

	@Override
	public Spliterator<T> spliterator() {
		return this.wrapped.spliterator();
	}
}