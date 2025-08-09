package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedIterator;
import com.hexagram2021.cme_suck_my_duck.containers.iterators.WrappedListIterator;
import com.hexagram2021.cme_suck_my_duck.utils.TraceLogger;

import java.util.*;
import java.util.function.UnaryOperator;

public class WrappedList<T> implements List<T> {
	final List<T> wrapped;

	WrappedList(List<T> wrapped) {
		if(wrapped instanceof WrappedList) {
			this.wrapped = ((WrappedList<T>)wrapped).wrapped;
		} else {
			this.wrapped = wrapped;
		}
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
		TraceLogger.debug("[Query] contains(Object)");
		return this.wrapped.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		TraceLogger.info("[Iteration] iterator()");
		return new WrappedIterator<>(this.wrapped.iterator());
	}

	@Override
	public Object[] toArray() {
		return this.wrapped.toArray();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return this.wrapped.toArray(a);
	}

	@Override
	public boolean add(T t) {
		TraceLogger.info("[Modify] add(Object)");
		return this.wrapped.add(t);
	}

	@Override
	public boolean remove(Object o) {
		TraceLogger.info("[Modify] remove(Object)");
		return this.wrapped.remove(o);
	}

	@SuppressWarnings("SlowListContainsAll")
	@Override
	public boolean containsAll(Collection<?> c) {
		TraceLogger.debug("[Query] containsAll(Collection)");
		return this.wrapped.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		TraceLogger.info("[Modify] addAll(Collection)");
		return this.wrapped.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		TraceLogger.info("[Modify] addAll(int, Collection)");
		return this.wrapped.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		TraceLogger.info("[Modify] removeAll(Collection)");
		return this.wrapped.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		TraceLogger.info("[Modify] retainAll(Collection)");
		return this.wrapped.retainAll(c);
	}

	@Override
	public void replaceAll(UnaryOperator<T> operator) {
		TraceLogger.info("[Modify] replaceAll(UnaryOperator)");
		this.wrapped.replaceAll(operator);
	}

	@Override
	public void sort(Comparator<? super T> c) {
		TraceLogger.info("[Modify] sort(Comparator)");
		this.wrapped.sort(c);
	}

	@Override
	public void clear() {
		TraceLogger.info("[Modify] clear()");
		this.wrapped.clear();
	}

	@Override
	public T get(int index) {
		TraceLogger.debug("[Query] get(int)");
		return this.wrapped.get(index);
	}

	@Override
	public T set(int index, T element) {
		TraceLogger.info("[Modify] set(int, Object)");
		return this.wrapped.set(index, element);
	}

	@Override
	public void add(int index, T element) {
		TraceLogger.info("[Modify] add(int, Object)");
		this.wrapped.add(index, element);
	}

	@Override
	public T remove(int index) {
		TraceLogger.info("[Modify] remove(int)");
		return this.wrapped.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		TraceLogger.debug("[Query] indexOf(Object)");
		return this.wrapped.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		TraceLogger.debug("[Query] lastIndexOf(Object)");
		return this.wrapped.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		TraceLogger.info("[Iteration] listIterator()");
		return new WrappedListIterator<>(this.wrapped.listIterator());
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		TraceLogger.info("[Iteration] listIterator(int)");
		return new WrappedListIterator<>(this.wrapped.listIterator(index));
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return this.wrapped.subList(fromIndex, toIndex);
	}

	@Override
	public Spliterator<T> spliterator() {
		return this.wrapped.spliterator();
	}
}
