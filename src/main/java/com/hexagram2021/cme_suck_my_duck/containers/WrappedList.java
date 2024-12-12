package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.Log;
import com.hexagram2021.cme_suck_my_duck.utils.SuckTraceException;

import java.util.*;
import java.util.function.UnaryOperator;

public class WrappedList<T> implements List<T> {
	final List<T> wrapped;

	WrappedList(List<T> wrapped) {
		this.wrapped = wrapped;
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
		Log.debug(new SuckTraceException("[Query] containsAll(Object)"));
		return this.wrapped.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return this.wrapped.iterator();
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
		Log.info(new SuckTraceException("[Modify] addAll(Object)"));
		return this.wrapped.add(t);
	}

	@Override
	public boolean remove(Object o) {
		Log.info(new SuckTraceException("[Modify] remove(Object)"));
		return this.wrapped.remove(o);
	}

	@SuppressWarnings("SlowListContainsAll")
	@Override
	public boolean containsAll(Collection<?> c) {
		Log.debug(new SuckTraceException("[Query] containsAll(Collection)"));
		return this.wrapped.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		Log.info(new SuckTraceException("[Modify] addAll(Collection)"));
		return this.wrapped.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		Log.info(new SuckTraceException("[Modify] addAll(int, Collection)"));
		return this.wrapped.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Log.info(new SuckTraceException("[Modify] removeAll(Collection)"));
		return this.wrapped.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		Log.info(new SuckTraceException("[Modify] retainAll(Collection)"));
		return this.wrapped.retainAll(c);
	}

	@Override
	public void replaceAll(UnaryOperator<T> operator) {
		Log.info(new SuckTraceException("[Modify] replaceAll(UnaryOperator)"));
		this.wrapped.replaceAll(operator);
	}

	@Override
	public void sort(Comparator<? super T> c) {
		Log.info(new SuckTraceException("[Modify] sort(Comparator)"));
		this.wrapped.sort(c);
	}

	@Override
	public void clear() {
		Log.info(new SuckTraceException("[Modify] clear()"));
		this.wrapped.clear();
	}

	@Override
	public T get(int index) {
		Log.debug(new SuckTraceException("[Query] get(int)"));
		return this.wrapped.get(index);
	}

	@Override
	public T set(int index, T element) {
		Log.info(new SuckTraceException("[Modify] set(int, Object)"));
		return this.wrapped.set(index, element);
	}

	@Override
	public void add(int index, T element) {
		Log.info(new SuckTraceException("[Modify] add(int, Object)"));
		this.wrapped.add(index, element);
	}

	@Override
	public T remove(int index) {
		Log.info(new SuckTraceException("[Modify] remove(int)"));
		return this.wrapped.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		Log.debug(new SuckTraceException("[Query] indexOf(Object)"));
		return this.wrapped.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		Log.debug(new SuckTraceException("[Query] lastIndexOf(Object)"));
		return this.wrapped.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return this.wrapped.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return this.wrapped.listIterator(index);
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
