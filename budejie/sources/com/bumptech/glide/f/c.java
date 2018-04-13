package com.bumptech.glide.f;

import com.bumptech.glide.i.g;
import java.util.HashMap;
import java.util.Map;

public class c {
    private static final g a = new g();
    private final Map<g, b<?, ?>> b = new HashMap();

    public <T, Z> void a(Class<T> cls, Class<Z> cls2, b<T, Z> bVar) {
        this.b.put(new g(cls, cls2), bVar);
    }

    public <T, Z> b<T, Z> a(Class<T> cls, Class<Z> cls2) {
        synchronized (a) {
            a.a(cls, cls2);
            b<T, Z> bVar = (b) this.b.get(a);
        }
        if (bVar == null) {
            return d.e();
        }
        return bVar;
    }
}
