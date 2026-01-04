package com.hexagram2021.cme_suck_my_duck.utils;

import javax.annotation.Nullable;
import java.util.function.Predicate;

@FunctionalInterface
public interface LogStrategy extends Predicate<Object> {
	@Override
	boolean test(@Nullable Object o);
}
