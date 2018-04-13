package com.squareup.wire.internal;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

final class a<T> extends AbstractList<T> implements Serializable, RandomAccess {
    private final ArrayList<T> a;

    a(List<T> list) {
        this.a = new ArrayList(list);
    }

    public int size() {
        return this.a.size();
    }

    public T get(int i) {
        return this.a.get(i);
    }

    public Object[] toArray() {
        return this.a.toArray();
    }
}
