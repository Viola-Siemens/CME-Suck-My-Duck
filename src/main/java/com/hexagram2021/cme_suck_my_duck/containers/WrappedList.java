package com.hexagram2021.cme_suck_my_duck.containers;

import com.hexagram2021.cme_suck_my_duck.utils.SuckTraceException;

import java.util.*;
import java.util.function.UnaryOperator;

import static com.hexagram2021.cme_suck_my_duck.containers.Containers.logger;

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
		logger.debug(new SuckTraceException("[Query] contains(Object)"));
		return this.wrapped.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		logger.debug(new SuckTraceException("[Iteration] iterator()"));
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
		logger.info(new SuckTraceException("[Modify] add(Object)"));
		return this.wrapped.add(t);
	}

	@Override
	public boolean remove(Object o) {
		logger.info(new SuckTraceException("[Modify] remove(Object)"));
		return this.wrapped.remove(o);
	}

	@SuppressWarnings("SlowListContainsAll")
	@Override
	public boolean containsAll(Collection<?> c) {
		logger.debug(new SuckTraceException("[Query] containsAll(Collection)"));
		return this.wrapped.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		logger.info(new SuckTraceException("[Modify] addAll(Collection)"));
		return this.wrapped.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		logger.info(new SuckTraceException("[Modify] addAll(int, Collection)"));
		return this.wrapped.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		logger.info(new SuckTraceException("[Modify] removeAll(Collection)"));
		return this.wrapped.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		logger.info(new SuckTraceException("[Modify] retainAll(Collection)"));
		return this.wrapped.retainAll(c);
	}

	@Override
	public void replaceAll(UnaryOperator<T> operator) {
		logger.info(new SuckTraceException("[Modify] replaceAll(UnaryOperator)"));
		this.wrapped.replaceAll(operator);
	}

	@Override
	public void sort(Comparator<? super T> c) {
		logger.info(new SuckTraceException("[Modify] sort(Comparator)"));
		this.wrapped.sort(c);
	}

	@Override
	public void clear() {
		logger.info(new SuckTraceException("[Modify] clear()"));
		this.wrapped.clear();
	}

	@Override
	public T get(int index) {
		logger.debug(new SuckTraceException("[Query] get(int)"));
		return this.wrapped.get(index);
	}

	@Override
	public T set(int index, T element) {
		logger.info(new SuckTraceException("[Modify] set(int, Object)"));
		return this.wrapped.set(index, element);
	}

	@Override
	public void add(int index, T element) {
		logger.info(new SuckTraceException("[Modify] add(int, Object)"));
		this.wrapped.add(index, element);
	}

	@Override
	public T remove(int index) {
		logger.info(new SuckTraceException("[Modify] remove(int)"));
		return this.wrapped.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		logger.debug(new SuckTraceException("[Query] indexOf(Object)"));
		return this.wrapped.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		logger.debug(new SuckTraceException("[Query] lastIndexOf(Object)"));
		return this.wrapped.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		logger.debug(new SuckTraceException("[Iteration] listIterator()"));
		return this.wrapped.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		logger.debug(new SuckTraceException("[Iteration] listIterator(int)"));
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
