package com.hexagram2021.cme_suck_my_duck.containers.iterators;

import com.hexagram2021.cme_suck_my_duck.containers.AbstractWrappedContainer;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import it.unimi.dsi.fastutil.objects.ObjectIterator;

public class ObjectWrappedIterator<T> extends AbstractWrappedContainer<ObjectIterator<T>> implements ObjectIterator<T> {
	public ObjectWrappedIterator(ObjectIterator<T> wrapped) {
		super(wrapped);
	}
	public ObjectWrappedIterator(ObjectIterator<T> wrapped, String traceId) {
		super(wrapped, traceId);
	}

	@Override
	public boolean hasNext() {
		this.logQuery("hasNext()");
		try {
			return this.wrapped.hasNext();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public T next() {
		this.logQuery("next()");
		try {
			return this.wrapped.next();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void remove() {
		this.logModify("remove()");
		try {
			this.wrapped.remove();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
