package com.hexagram2021.cme_suck_my_duck.containers.iterators;

import com.hexagram2021.cme_suck_my_duck.containers.AbstractWrappedContainer;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import it.unimi.dsi.fastutil.ints.IntListIterator;

public class IntWrappedListIterator extends AbstractWrappedContainer<IntListIterator> implements IntListIterator {
	public IntWrappedListIterator(IntListIterator wrapped) {
		super(wrapped);
	}
	public IntWrappedListIterator(IntListIterator wrapped, String traceId) {
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
	public int nextInt() {
		this.logQuery("nextInt()");
		try {
			return this.wrapped.nextInt();
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
	public int previousInt() {
		this.logQuery("previousInt()");
		try {
			return this.wrapped.previousInt();
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
	public void set(int t) {
		this.logModify("set(int)");
		try {
			this.wrapped.set(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void add(int t) {
		this.logModify("add(int)");
		try {
			this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
