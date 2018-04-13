package com.facebook.imagepipeline.producers;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class ad<K, T extends Closeable> implements ai<T> {
    @GuardedBy
    final Map<K, ad$a> a = new HashMap();
    private final ai<T> b;

    protected abstract T a(T t);

    protected abstract K b(aj ajVar);

    protected ad(ai<T> aiVar) {
        this.b = aiVar;
    }

    public void a(j<T> jVar, aj ajVar) {
        Object b = b(ajVar);
        ad$a a;
        do {
            Object obj = null;
            synchronized (this) {
                a = a(b);
                if (a == null) {
                    a = b(b);
                    obj = 1;
                }
            }
        } while (!a.a(jVar, ajVar));
        if (obj != null) {
            ad$a.a(a);
        }
    }

    private synchronized ad$a a(K k) {
        return (ad$a) this.a.get(k);
    }

    private synchronized ad$a b(K k) {
        ad$a ad_a;
        ad_a = new ad$a(this, k);
        this.a.put(k, ad_a);
        return ad_a;
    }

    private synchronized void a(K k, ad$a ad_a) {
        if (this.a.get(k) == ad_a) {
            this.a.remove(k);
        }
    }
}
