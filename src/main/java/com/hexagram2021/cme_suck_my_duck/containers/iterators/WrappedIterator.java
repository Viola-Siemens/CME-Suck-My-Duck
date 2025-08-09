package com.hexagram2021.cme_suck_my_duck.containers.iterators;

import com.hexagram2021.cme_suck_my_duck.utils.TraceLogger;

import java.util.Iterator;

public class WrappedIterator<T> implements Iterator<T> {
	final Iterator<T> wrapped;

	public WrappedIterator(Iterator<T> wrapped) {
		if(wrapped instanceof WrappedIterator) {
			this.wrapped = ((WrappedIterator<T>)wrapped).wrapped;
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
	public void remove() {
		TraceLogger.info("[Modify] remove()");
		this.wrapped.remove();
	}
}
