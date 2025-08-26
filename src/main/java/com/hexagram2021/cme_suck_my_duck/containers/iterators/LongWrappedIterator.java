package com.hexagram2021.cme_suck_my_duck.containers.iterators;

import com.hexagram2021.cme_suck_my_duck.containers.AbstractWrappedContainer;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import it.unimi.dsi.fastutil.longs.LongIterator;

public class LongWrappedIterator extends AbstractWrappedContainer<LongIterator> implements LongIterator {
	public LongWrappedIterator(LongIterator wrapped) {
		super(wrapped);
	}
	public LongWrappedIterator(LongIterator wrapped, String traceId) {
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
	public long nextLong() {
		this.logQuery("nextLong()");
		try {
			return this.wrapped.nextLong();
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
