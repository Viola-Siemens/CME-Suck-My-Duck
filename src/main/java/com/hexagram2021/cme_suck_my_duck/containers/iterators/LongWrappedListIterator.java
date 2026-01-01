package com.hexagram2021.cme_suck_my_duck.containers.iterators;

import com.hexagram2021.cme_suck_my_duck.containers.AbstractWrappedContainer;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.longs.LongListIterator;

public class LongWrappedListIterator extends AbstractWrappedContainer<LongListIterator> implements LongListIterator {
	public LongWrappedListIterator(LongListIterator wrapped) {
		super(wrapped);
	}
	public LongWrappedListIterator(LongListIterator wrapped, String traceId) {
		super(wrapped, traceId);
	}

	@Override
	public boolean hasNext() {
		this.logQuery("hasNext()", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.hasNext();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long nextLong() {
		this.logQuery("nextLong()", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.nextLong();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean hasPrevious() {
		this.logQuery("hasPrevious()", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.hasPrevious();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long previousLong() {
		this.logQuery("previousLong()", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.previousLong();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int nextIndex() {
		this.logQuery("nextIndex()", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.nextIndex();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int previousIndex() {
		this.logQuery("previousIndex()", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.previousIndex();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void remove() {
		this.logModify("remove()", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.remove();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void set(long t) {
		this.logQuery("set(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.set(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void add(long t) {
		this.logModify("add(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
