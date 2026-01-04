package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedIterator;
import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedListIterator;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.Log;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.UnaryOperator;

public class WrappedList<T> extends AbstractWrappedContainer<List<T>> implements List<T> {
	WrappedList(List<T> wrapped) {
		super(wrapped);
	}
	WrappedList(List<T> wrapped, String traceId) {
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
	public boolean contains(Object o) {
		this.logQuery("contains(Object)", Log.LOG_STRATEGY.test(o));
		try {
			return this.wrapped.contains(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Iterator<T> iterator() {
		this.logIteration("iterator()", Log.LOG_STRATEGY.logAnyway());
		return new WrappedIterator<>(this.wrapped.iterator(), this.traceId);
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

	@Override
	public boolean add(T t) {
		this.logModify("add(Object)", Log.LOG_STRATEGY.test(t));
		try {
			return this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(Object o) {
		this.logModify("remove(Object)", Log.LOG_STRATEGY.test(o));
		try {
			return this.wrapped.remove(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@SuppressWarnings("SlowListContainsAll")
	@Override
	public boolean containsAll(Collection<?> c) {
		this.logQuery("containsAll(Collection)", c.stream().anyMatch(Log.LOG_STRATEGY));
		try {
			return this.wrapped.containsAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		this.logModify("addAll(Collection)", c.stream().anyMatch(Log.LOG_STRATEGY));
		try {
			return this.wrapped.addAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		this.logModify("addAll(int, Collection)", c.stream().anyMatch(Log.LOG_STRATEGY));
		try {
			return this.wrapped.addAll(index, c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		this.logModify("removeAll(Collection)", c.stream().anyMatch(Log.LOG_STRATEGY));
		try {
			return this.wrapped.removeAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		this.logModify("retainAll(Collection)", c.stream().anyMatch(Log.LOG_STRATEGY));
		try {
			return this.wrapped.retainAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void replaceAll(UnaryOperator<T> operator) {
		this.logModify("replaceAll(UnaryOperator)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.replaceAll(operator);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void sort(@Nullable Comparator<? super T> c) {
		this.logModify("sort(Comparator)", Log.LOG_STRATEGY.logAnyway());
		try {
			this.wrapped.sort(c);
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
	public T get(int index) {
		this.logQuery("get(int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.get(index);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public T set(int index, T element) {
		this.logQuery("set(int, Object)", Log.LOG_STRATEGY.test(element));
		try {
			return this.wrapped.set(index, element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void add(int index, T element) {
		this.logModify("add(int, Object)", Log.LOG_STRATEGY.test(element));
		try {
			this.wrapped.add(index, element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public T remove(int index) {
		this.logModify("remove(int)", Log.LOG_STRATEGY.logAnyway());
		try {
			return this.wrapped.remove(index);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int indexOf(Object o) {
		this.logQuery("indexOf(Object)", Log.LOG_STRATEGY.test(o));
		try {
			return this.wrapped.indexOf(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int lastIndexOf(Object o) {
		this.logQuery("lastIndexOf(Object)", Log.LOG_STRATEGY.test(o));
		try {
			return this.wrapped.lastIndexOf(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public ListIterator<T> listIterator() {
		this.logIteration("listIterator()", Log.LOG_STRATEGY.logAnyway());
		return new WrappedListIterator<>(this.wrapped.listIterator(), this.traceId);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		this.logIteration("listIterator(int)", Log.LOG_STRATEGY.logAnyway());
		return new WrappedListIterator<>(this.wrapped.listIterator(index), this.traceId);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		try {
			return new WrappedList<>(this.wrapped.subList(fromIndex, toIndex), this.traceId);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Spliterator<T> spliterator() {
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
}
