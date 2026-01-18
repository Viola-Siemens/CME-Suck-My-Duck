package com.hexagram2021.cme_suck_my_duck.utils;

import com.google.common.util.concurrent.AtomicDouble;
import org.apache.commons.lang3.math.Fraction;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableFloat;

import java.math.BigDecimal;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;

public final class NumberFunctions {
	public static boolean is(Number number, long expected) {
		if(number instanceof Double || number instanceof Float ||
				(number instanceof BigDecimal && ((BigDecimal) number).scale() > 0) ||
				number instanceof DoubleAccumulator || number instanceof Fraction ||
				number instanceof DoubleAdder || number instanceof AtomicDouble ||
				number instanceof MutableDouble || number instanceof MutableFloat) {
			return Math.abs(number.doubleValue() - expected) < 1e-6;
		}
		return number.longValue() == expected;
	}

	private NumberFunctions() {
	}
}
