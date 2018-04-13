package com.liulishuo.filedownloader.e;

import android.util.SparseArray;

public class b<T extends a> {
    private final SparseArray<T> a = new SparseArray();

    public T a(int i) {
        return (a) this.a.get(i);
    }

    public T b(int i) {
        T a = a(i);
        if (a == null) {
            return null;
        }
        this.a.remove(i);
        return a;
    }

    public void a(T t) {
        this.a.remove(t.c());
        this.a.put(t.c(), t);
    }

    public void a(int i, int i2, int i3) {
        a a = a(i);
        if (a != null) {
            a.a(3);
            a.a(i2, i3);
        }
    }

    public void a(int i, int i2) {
        a a = a(i);
        if (a != null) {
            a.a(i2);
            a.a(false);
        }
    }
}
