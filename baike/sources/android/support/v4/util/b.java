package android.support.v4.util;

import java.util.Map;

class b extends d<E, E> {
    final /* synthetic */ ArraySet a;

    b(ArraySet arraySet) {
        this.a = arraySet;
    }

    protected int a() {
        return this.a.h;
    }

    protected Object a(int i, int i2) {
        return this.a.g[i];
    }

    protected int a(Object obj) {
        return this.a.indexOf(obj);
    }

    protected int b(Object obj) {
        return this.a.indexOf(obj);
    }

    protected Map<E, E> b() {
        throw new UnsupportedOperationException("not a map");
    }

    protected void a(E e, E e2) {
        this.a.add(e);
    }

    protected E a(int i, E e) {
        throw new UnsupportedOperationException("not a map");
    }

    protected void a(int i) {
        this.a.removeAt(i);
    }

    protected void c() {
        this.a.clear();
    }
}
