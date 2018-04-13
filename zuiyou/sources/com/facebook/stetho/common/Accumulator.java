package com.facebook.stetho.common;

public interface Accumulator<E> {
    void store(E e);
}
