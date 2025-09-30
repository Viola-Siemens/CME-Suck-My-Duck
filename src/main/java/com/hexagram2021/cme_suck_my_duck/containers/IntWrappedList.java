package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.IntWrappedListIterator;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import it.unimi.dsi.fastutil.ints.*;

import java.util.Collection;
import java.util.List;

public class IntWrappedList extends AbstractWrappedContainer<IntList> implements IntList {
	IntWrappedList(IntList wrapped) {
		super(wrapped);
	}
	IntWrappedList(IntList wrapped, String traceId) {
		super(wrapped, traceId);
	}

	@Override
	public int size() {
		return this.wrapped.size();
	}

	@Override
	public void size(int size) {
		this.logModify("size(int)");
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
	public boolean contains(int o) {
		this.logQuery("contains(int)");
		try {
			return this.wrapped.contains(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public IntListIterator iterator() {
		this.logIteration("iterator()");
		return new IntWrappedListIterator(this.wrapped.iterator(), this.traceId);
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
		this.logQuery("containsAll(Collection)");
		try {
			return this.wrapped.containsAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		this.logModify("addAll(Collection)");
		try {
			return this.wrapped.addAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends Integer> c) {
		this.logModify("addAll(int, Collection)");
		try {
			return this.wrapped.addAll(index, c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		this.logModify("removeAll(Collection)");
		try {
			return this.wrapped.removeAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		this.logModify("retainAll(Collection)");
		try {
			return this.wrapped.retainAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int[] toIntArray() {
		try {
			return this.wrapped.toIntArray();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int[] toArray(int[] a) {
		try {
			return this.wrapped.toArray(a);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean add(int t) {
		this.logModify("add(int)");
		try {
			return this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean rem(int o) {
		this.logModify("rem(int)");
		try {
			return this.wrapped.rem(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean containsAll(IntCollection c) {
		this.logQuery("containsAll(IntCollection)");
		try {
			return this.wrapped.containsAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(IntCollection c) {
		this.logModify("addAll(IntCollection)");
		try {
			return this.wrapped.addAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(int index, IntCollection c) {
		this.logModify("addAll(int, IntCollection)");
		try {
			return this.wrapped.addAll(index, c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean removeAll(IntCollection c) {
		this.logModify("removeAll(IntCollection)");
		try {
			return this.wrapped.removeAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean retainAll(IntCollection c) {
		this.logModify("retainAll(IntCollection)");
		try {
			return this.wrapped.retainAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void replaceAll(IntUnaryOperator operator) {
		this.logModify("replaceAll(IntUnaryOperator)");
		try {
			this.wrapped.replaceAll(operator);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void sort(IntComparator c) {
		this.logModify("sort(IntComparator)");
		try {
			this.wrapped.sort(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void unstableSort(IntComparator c) {
		this.logModify("unstableSort(IntComparator)");
		try {
			this.wrapped.unstableSort(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void clear() {
		this.logModify("clear()");
		try {
			this.wrapped.clear();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int getInt(int index) {
		this.logQuery("get(int)");
		try {
			return this.wrapped.getInt(index);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int set(int index, int element) {
		this.logModify("set(int, int)");
		try {
			return this.wrapped.set(index, element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void add(int index, int element) {
		this.logModify("add(int, int)");
		try {
			this.wrapped.add(index, element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int removeInt(int index) {
		this.logModify("removeInt(int)");
		try {
			return this.wrapped.removeInt(index);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int indexOf(int o) {
		this.logQuery("indexOf(int)");
		try {
			return this.wrapped.indexOf(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int lastIndexOf(int o) {
		this.logQuery("lastIndexOf(int)");
		try {
			return this.wrapped.lastIndexOf(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public IntListIterator listIterator() {
		this.logIteration("listIterator()");
		return new IntWrappedListIterator(this.wrapped.listIterator(), this.traceId);
	}

	@Override
	public IntListIterator listIterator(int index) {
		this.logIteration("listIterator(int)");
		return new IntWrappedListIterator(this.wrapped.listIterator(index), this.traceId);
	}

	@Override
	public IntList subList(int fromIndex, int toIndex) {
		try {
			return new IntWrappedList(this.wrapped.subList(fromIndex, toIndex), this.traceId);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void getElements(int from, int[] a, int offset, int length) {
		this.logQuery("getElements(int, int[], int, int)");
		try {
			this.wrapped.getElements(from, a, offset, length);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void removeElements(int from, int to) {
		this.logModify("removeElements(int, int)");
		try {
			this.wrapped.removeElements(from, to);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void addElements(int index, int[] a) {
		this.logModify("addElements(int, int[])");
		try {
			this.wrapped.addElements(index, a);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void addElements(int index, int[] a, int offset, int length) {
		this.logModify("addElements(int, int[], int, int)");
		try {
			this.wrapped.addElements(index, a, offset, length);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void setElements(int index, int[] a, int offset, int length) {
		this.logModify("setElements(int, int[], int, int)");
		try {
			this.wrapped.setElements(index, a, offset, length);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public IntSpliterator spliterator() {
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
	public int compareTo(List<? extends Integer> o) {
		this.logQuery("compareTo(List)");
		try {
			return this.wrapped.compareTo(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
