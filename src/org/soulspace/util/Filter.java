package org.soulspace.util;

public interface Filter<T> {
	boolean accept(T t);
}
