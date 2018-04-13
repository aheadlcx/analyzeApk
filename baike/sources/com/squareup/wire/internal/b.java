package com.squareup.wire.internal;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

final class b<T> extends AbstractList<T> implements Serializable, RandomAccess {
    List<T> a;
    private final List<T> b;

    b(List<T> list) {
        this.b = list;
        this.a = list;
    }

    public T get(int i) {
        return this.a.get(i);
    }

    public int size() {
        return this.a.size();
    }

    public T set(int i, T t) {
        if (this.a == this.b) {
            this.a = new ArrayList(this.b);
        }
        return this.a.set(i, t);
    }

    public void add(int i, T t) {
        if (this.a == this.b) {
            this.a = new ArrayList(this.b);
        }
        this.a.add(i, t);
    }

    public T remove(int i) {
        if (this.a == this.b) {
            this.a = new ArrayList(this.b);
        }
        return this.a.remove(i);
    }
}
