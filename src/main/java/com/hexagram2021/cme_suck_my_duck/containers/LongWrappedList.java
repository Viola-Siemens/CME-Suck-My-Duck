package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.LongWrappedListIterator;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;
import it.unimi.dsi.fastutil.longs.*;

import java.util.Collection;
import java.util.List;

public class LongWrappedList extends AbstractWrappedContainer<LongList> implements LongList {
	LongWrappedList(LongList wrapped) {
		super(wrapped);
	}
	LongWrappedList(LongList wrapped, String traceId) {
		super(wrapped, traceId);
	}

	@Override
	public int size() {
		return this.wrapped.size();
	}

	@Override
	public void size(int size) {
		this.logModify("size(int)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.size(size);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
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
	public LongListIterator iterator() {
		this.logIteration("iterator()", Log.LOG_STRATEGY.logAnyway());
		return new LongWrappedListIterator(this.wrapped.iterator(), this.traceId);
	}

	@Override
	public Object[] toArray() {
		return this.wrapped.toArray();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		try {
			return this.wrapped.toArray(a);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@SuppressWarnings("SlowListContainsAll")
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
	public boolean addAll(int index, Collection<? extends Long> c) {
		this.logModify("addAll(int, Collection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.addAll(index, c);
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
	public boolean add(long t) {
		this.logModify("add(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean rem(long o) {
		this.logModify("rem(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.rem(o);
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
	public boolean addAll(int index, LongCollection c) {
		this.logModify("addAll(int, LongCollection)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.addAll(index, c);
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
	public void replaceAll(LongUnaryOperator operator) {
		this.logModify("replaceAll(LongUnaryOperator)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.replaceAll(operator);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void sort(LongComparator c) {
		this.logModify("sort(LongComparator)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.sort(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void unstableSort(LongComparator c) {
		this.logModify("unstableSort(LongComparator)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.unstableSort(c);
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
	public long getLong(int index) {
		this.logQuery("get(int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.getLong(index);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long set(int index, long element) {
		this.logQuery("set(int, long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.set(index, element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void add(int index, long element) {
		this.logModify("add(int, long)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.add(index, element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public long removeLong(int index) {
		this.logModify("removeLong(int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.removeLong(index);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int indexOf(long o) {
		this.logQuery("indexOf(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.indexOf(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int lastIndexOf(long o) {
		this.logQuery("lastIndexOf(long)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.lastIndexOf(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public LongListIterator listIterator() {
		this.logIteration("listIterator()", Log.LOG_STRATEGY.logAnyway());
		return new LongWrappedListIterator(this.wrapped.listIterator(), this.traceId);
	}

	@Override
	public LongListIterator listIterator(int index) {
		this.logIteration("listIterator(int)", Log.LOG_STRATEGY.logAnyway());
		return new LongWrappedListIterator(this.wrapped.listIterator(index), this.traceId);
	}

	@Override
	public LongList subList(int fromIndex, int toIndex) {
		try {
			return new LongWrappedList(this.wrapped.subList(fromIndex, toIndex), this.traceId);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void getElements(int from, long[] a, int offset, int length) {
		this.logQuery("getElements(int, long[], int, int)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.getElements(from, a, offset, length);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void removeElements(int from, int to) {
		this.logModify("removeElements(int, int)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.removeElements(from, to);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void addElements(int index, long[] a) {
		this.logModify("addElements(int, long[])", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.addElements(index, a);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void addElements(int index, long[] a, int offset, int length) {
		this.logModify("addElements(int, long[], int, int)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.addElements(index, a, offset, length);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void setElements(int index, long[] a, int offset, int length) {
		this.logModify("setElements(int, int[], int, int)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.setElements(index, a, offset, length);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public LongSpliterator spliterator() {
		this.logIteration("spliterator()", Log.LOG_STRATEGY.logAnyway());
		return this.wrapped.spliterator();
	}

	@Override
	public boolean equals(Object o) {
		try {
			return this.wrapped.equals(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
	@Override
	public int hashCode() {
		try {
			return this.wrapped.hashCode();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int compareTo(List<? extends Long> o) {
		this.logQuery("compareTo(List)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.compareTo(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
