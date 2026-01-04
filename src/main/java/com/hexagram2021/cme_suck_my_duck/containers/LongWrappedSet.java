package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.LongWrappedIterator;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSpliterator;

import java.util.Collection;

public class LongWrappedSet extends AbstractWrappedContainer<LongSet> implements LongSet {
	LongWrappedSet(LongSet wrapped) {
		super(wrapped);
	}
	LongWrappedSet(LongSet wrapped, String traceId) {
		super(wrapped, traceId);
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
	public boolean contains(long o) {
		this.logQuery("contains(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.contains(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long[] toLongArray() {
		try {
			return this.wrapped.toLongArray();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long[] toArray(long[] a) {
		try {
			return this.wrapped.toArray(a);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public LongIterator iterator() {
		this.logIteration("iterator()", Log.LOG_STRATEGY.logAnyway());
		return new LongWrappedIterator(this.wrapped.iterator(), this.traceId);
	}

	@Override
	public Object[] toArray() {
		try {
			return this.wrapped.toArray();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		try {
			return this.wrapped.toArray(a);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean add(long t) {
		this.logModify("add(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(long o) {
		this.logModify("remove(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		this.logQuery("containsAll(Collection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.containsAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(Collection<? extends Long> c) {
		this.logModify("addAll(Collection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.addAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		this.logModify("removeAll(Collection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.removeAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		this.logModify("retainAll(Collection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.retainAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean containsAll(LongCollection c) {
		this.logQuery("containsAll(LongCollection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.containsAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(LongCollection c) {
		this.logModify("addAll(LongCollection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.addAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean removeAll(LongCollection c) {
		this.logModify("removeAll(LongCollection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.removeAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean retainAll(LongCollection c) {
		this.logModify("retainAll(LongCollection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.retainAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void clear() {
		this.logModify("clear()", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.clear();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public LongSpliterator spliterator() {
		this.logIteration("spliterator()", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.spliterator();
	}
}
