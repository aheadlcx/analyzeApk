package com.fasterxml.jackson.databind.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterable<T>, Iterator<T> {
    private final T[] _a;
    private int _index = 0;

    public ArrayIterator(T[] tArr) {
        this._a = tArr;
    }

    public boolean hasNext() {
        return this._index < this._a.length;
    }

    public T next() {
        if (this._index >= this._a.length) {
            throw new NoSuchElementException();
        }
        Object[] objArr = this._a;
        int i = this._index;
        this._index = i + 1;
        return objArr[i];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator() {
        return this;
    }
}
