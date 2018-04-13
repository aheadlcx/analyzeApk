package com.c.a;

import android.support.v4.util.SparseArrayCompat;

public class b<T> {
    SparseArrayCompat<a<T>> a = new SparseArrayCompat();

    public int a() {
        return this.a.size();
    }

    public b<T> a(a<T> aVar) {
        int size = this.a.size();
        if (aVar != null) {
            this.a.put(size, aVar);
        }
        return this;
    }

    public b<T> a(int i, a<T> aVar) {
        if (this.a.get(i) != null) {
            throw new IllegalArgumentException("An ItemViewDelegate is already registered for the viewType = " + i + ". Already registered ItemViewDelegate is " + this.a.get(i));
        }
        this.a.put(i, aVar);
        return this;
    }

    public int a(T t, int i) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (((a) this.a.valueAt(size)).a((Object) t, i)) {
                return this.a.keyAt(size);
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegate added that matches position=" + i + " in data source");
    }

    public void a(c cVar, T t, int i) {
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            a aVar = (a) this.a.valueAt(i2);
            if (aVar.a((Object) t, i)) {
                aVar.a(cVar, t, i);
                return;
            }
        }
        throw new IllegalArgumentException("No ItemViewDelegateManager added that matches position=" + i + " in data source");
    }

    public a a(int i) {
        return (a) this.a.get(i);
    }
}
