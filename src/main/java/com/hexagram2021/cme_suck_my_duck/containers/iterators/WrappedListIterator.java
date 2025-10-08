package com.hexagram2021.cme_suck_my_duck.containers.iterators;

import com.hexagram2021.cme_suck_my_duck.containers.AbstractWrappedContainer;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;

import java.util.ListIterator;

public class WrappedListIterator<T> extends AbstractWrappedContainer<ListIterator<T>> implements ListIterator<T> {
	public WrappedListIterator(ListIterator<T> wrapped) {
		super(wrapped);
	}
	public WrappedListIterator(ListIterator<T> wrapped, String traceId) {
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
	public boolean hasPrevious() {
		this.logQuery("hasPrevious()");
		try {
			return this.wrapped.hasPrevious();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public T previous() {
		this.logQuery("previous()");
		try {
			return this.wrapped.previous();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int nextIndex() {
		this.logQuery("nextIndex()");
		try {
			return this.wrapped.nextIndex();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int previousIndex() {
		this.logQuery("previousIndex()");
		try {
			return this.wrapped.previousIndex();
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

	@Override
	public void set(T t) {
		this.logQuery("set(Object)");
		try {
			this.wrapped.set(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void add(T t) {
		this.logModify("add(Object)");
		try {
			this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
