package com.facebook.imagepipeline.g;

import com.facebook.imagepipeline.animated.base.k;

public class a extends c {
    private k a;

    public a(k kVar) {
        this.a = kVar;
    }

    public synchronized int a() {
        return c() ? 0 : this.a.a().a();
    }

    public synchronized int b() {
        return c() ? 0 : this.a.a().b();
    }

    public void close() {
        synchronized (this) {
            if (this.a == null) {
                return;
            }
            k kVar = this.a;
            this.a = null;
            kVar.d();
        }
    }

    public synchronized boolean c() {
        return this.a == null;
    }

    public synchronized int d() {
        return c() ? 0 : this.a.a().g();
    }

    public boolean e() {
        return true;
    }

    public synchronized k f() {
        return this.a;
    }
}
