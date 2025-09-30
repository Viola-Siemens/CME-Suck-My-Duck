package com.hexagram2021.cme_suck_my_duck.containers.iterators;

import com.hexagram2021.cme_suck_my_duck.containers.AbstractWrappedContainer;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;

public class ObjectWrappedListIterator<K> extends AbstractWrappedContainer<ObjectListIterator<K>> implements ObjectListIterator<K> {
	public ObjectWrappedListIterator(ObjectListIterator<K> wrapped) {
		super(wrapped);
	}
	public ObjectWrappedListIterator(ObjectListIterator<K> wrapped, String traceId) {
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
	public K next() {
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
	public K previous() {
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
	public void set(K t) {
		this.logModify("set(Object)");
		try {
			this.wrapped.set(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void add(K t) {
		this.logModify("add(Object)");
		try {
			this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
