package android.support.v4.util;

import java.util.Map;

class a extends d<K, V> {
    final /* synthetic */ ArrayMap a;

    a(ArrayMap arrayMap) {
        this.a = arrayMap;
    }

    protected int a() {
        return this.a.h;
    }

    protected Object a(int i, int i2) {
        return this.a.g[(i << 1) + i2];
    }

    protected int a(Object obj) {
        return this.a.indexOfKey(obj);
    }

    protected int b(Object obj) {
        return this.a.a(obj);
    }

    protected Map<K, V> b() {
        return this.a;
    }

    protected void a(K k, V v) {
        this.a.put(k, v);
    }

    protected V a(int i, V v) {
        return this.a.setValueAt(i, v);
    }

    protected void a(int i) {
        this.a.removeAt(i);
    }

    protected void c() {
        this.a.clear();
    }
}
