package com.facebook.cache.common;

public class f implements CacheEventListener {
    private static f a = null;

    private f() {
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f();
            }
            fVar = a;
        }
        return fVar;
    }

    public void a(a aVar) {
    }

    public void b(a aVar) {
    }

    public void c(a aVar) {
    }

    public void d(a aVar) {
    }

    public void e(a aVar) {
    }

    public void f(a aVar) {
    }

    public void g(a aVar) {
    }
}
