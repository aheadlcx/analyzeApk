package com.bumptech.glide.load.resource.e;

import com.bumptech.glide.i.g;
import java.util.HashMap;
import java.util.Map;

public class d {
    private static final g a = new g();
    private final Map<g, c<?, ?>> b = new HashMap();

    public <Z, R> void a(Class<Z> cls, Class<R> cls2, c<Z, R> cVar) {
        this.b.put(new g(cls, cls2), cVar);
    }

    public <Z, R> c<Z, R> a(Class<Z> cls, Class<R> cls2) {
        if (cls.equals(cls2)) {
            return e.b();
        }
        synchronized (a) {
            a.a(cls, cls2);
            c<Z, R> cVar = (c) this.b.get(a);
        }
        if (cVar != null) {
            return cVar;
        }
        throw new IllegalArgumentException("No transcoder registered for " + cls + " and " + cls2);
    }
}
