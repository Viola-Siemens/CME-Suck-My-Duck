package com.hexagram2021.cme_suck_my_duck.containers;

import java.util.Deque;

public abstract class WrappedDeque<T> extends AbstractWrappedContainer<Deque<T>> implements Deque<T> {
	WrappedDeque(Deque<T> wrapped) {
		super(wrapped);
	}
	WrappedDeque(Deque<T> wrapped, String traceId) {
		super(wrapped, traceId);
	}
	//TODO
}
