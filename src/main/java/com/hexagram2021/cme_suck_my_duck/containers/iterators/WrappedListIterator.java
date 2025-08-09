package com.hexagram2021.cme_suck_my_duck.containers.iterators;

import com.hexagram2021.cme_suck_my_duck.utils.TraceLogger;

import java.util.ListIterator;

public class WrappedListIterator<T> implements ListIterator<T> {
	final ListIterator<T> wrapped;

	public WrappedListIterator(ListIterator<T> wrapped) {
		if(wrapped instanceof WrappedListIterator) {
			this.wrapped = ((WrappedListIterator<T>)wrapped).wrapped;
		} else {
			this.wrapped = wrapped;
		}
	}

	@Override
	public boolean hasNext() {
		TraceLogger.debug("[Query] hasNext()");
		return this.wrapped.hasNext();
	}

	@Override
	public T next() {
		TraceLogger.debug("[Query] next()");
		return this.wrapped.next();
	}

	@Override
	public boolean hasPrevious() {
		TraceLogger.debug("[Query] hasPrevious()");
		return this.wrapped.hasPrevious();
	}

	@Override
	public T previous() {
		TraceLogger.debug("[Query] previous()");
		return this.wrapped.previous();
	}

	@Override
	public int nextIndex() {
		TraceLogger.debug("[Query] nextIndex()");
		return this.wrapped.nextIndex();
	}

	@Override
	public int previousIndex() {
		TraceLogger.debug("[Query] previousIndex()");
		return this.wrapped.previousIndex();
	}

	@Override
	public void remove() {
		TraceLogger.info("[Modify] remove()");
		this.wrapped.remove();
	}

	@Override
	public void set(T t) {
		TraceLogger.info("[Modify] set(Object)");
		this.wrapped.set(t);
	}

	@Override
	public void add(T t) {
		TraceLogger.info("[Modify] add(Object)");
		this.wrapped.add(t);
	}
}
