package com.facebook.imagepipeline.c;

import com.android.internal.util.Predicate;
import com.facebook.common.references.a;

public class p<K, V> implements t<K, V> {
    private final t<K, V> a;
    private final v b;

    public p(t<K, V> tVar, v vVar) {
        this.a = tVar;
        this.b = vVar;
    }

    public a<V> a(K k) {
        a<V> a = this.a.a(k);
        if (a == null) {
            this.b.a();
        } else {
            this.b.a(k);
        }
        return a;
    }

    public a<V> a(K k, a<V> aVar) {
        this.b.b();
        return this.a.a(k, aVar);
    }

    public int a(Predicate<K> predicate) {
        return this.a.a(predicate);
    }
}
