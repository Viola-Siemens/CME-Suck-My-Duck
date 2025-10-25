package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedIterator;
import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedListIterator;
import com.hexagram2021.cme_suck_my_duck.exceptions.TracedException;
import com.hexagram2021.cme_suck_my_duck.utils.TraceIdGenerator;
import com.hexagram2021.cme_suck_my_duck.utils.TraceLogger;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@SuppressWarnings("java:S1948")
public class WrappedArrayList<T> extends ArrayList<T> {
	private final ArrayList<T> wrapped;
	private final String traceId;

	WrappedArrayList(ArrayList<T> wrapped) {
		if(wrapped instanceof WrappedArrayList) {
			this.wrapped = ((WrappedArrayList<T>)wrapped).wrapped;
		} else {
			this.wrapped = wrapped;
		}
		this.traceId = TraceIdGenerator.generateTraceId();
	}

	protected void logQuery(String signature) {
		TraceLogger.debug(this.traceId, "[Query] " + signature);
	}
	protected void logIteration(String signature) {
		TraceLogger.info(this.traceId, "[Iteration] " + signature);
	}
	protected void logModify(String signature) {
		TraceLogger.info(this.traceId, "[Modify] " + signature);
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
		this.logQuery("contains(Object)");
		try {
			return this.wrapped.contains(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public Iterator<T> iterator() {
		this.logIteration("iterator()");
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
		this.logModify("add(Object)");
		try {
			return this.wrapped.add(t);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean remove(Object o) {
		this.logModify("remove(Object)");
		try {
			return this.wrapped.remove(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

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
	public boolean addAll(Collection<? extends T> c) {
		this.logModify("addAll(Collection)");
		try {
			return this.wrapped.addAll(c);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
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
	public void replaceAll(UnaryOperator<T> operator) {
		this.logModify("replaceAll(UnaryOperator)");
		try {
			this.wrapped.replaceAll(operator);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void sort(Comparator<? super T> c) {
		this.logModify("sort(Comparator)");
		try {
			this.wrapped.sort(c);
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
	public T get(int index) {
		this.logQuery("get(int)");
		try {
			return this.wrapped.get(index);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public T set(int index, T element) {
		this.logQuery("set(int, Object)");
		try {
			return this.wrapped.set(index, element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void add(int index, T element) {
		this.logModify("add(int, Object)");
		try {
			this.wrapped.add(index, element);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public T remove(int index) {
		this.logModify("remove(int)");
		try {
			return this.wrapped.remove(index);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int indexOf(Object o) {
		this.logQuery("indexOf(Object)");
		try {
			return this.wrapped.indexOf(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public int lastIndexOf(Object o) {
		this.logQuery("lastIndexOf(Object)");
		try {
			return this.wrapped.lastIndexOf(o);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public ListIterator<T> listIterator() {
		this.logIteration("listIterator()");
		return new WrappedListIterator<>(this.wrapped.listIterator(), this.traceId);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		this.logIteration("listIterator(int)");
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
		this.logIteration("spliterator()");
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
	public void trimToSize() {
		this.logModify("trimToSize()");
		try {
			this.wrapped.trimToSize();
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void ensureCapacity(int minCapacity) {
		this.logModify("ensureCapacity(int)");
		try {
			this.wrapped.ensureCapacity(minCapacity);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public boolean removeIf(Predicate<? super T> filter) {
		this.logModify("removeIf(Predicate)");
		try {
			return this.wrapped.removeIf(filter);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}

	@Override
	public void forEach(Consumer<? super T> action) {
		this.logIteration("forEach(Consumer)");
		try {
			this.wrapped.forEach(action);
		} catch (RuntimeException e) {
			throw TracedException.create(this.traceId, e);
		}
	}
}
