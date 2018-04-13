package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.g.g;

class h implements com.bumptech.glide.load.engine.executor.a, Runnable {
    private final Priority a;
    private final a b;
    private final a<?, ?, ?> c;
    private b d = b.CACHE;
    private volatile boolean e;

    interface a extends g {
        void b(h hVar);
    }

    private enum b {
        CACHE,
        SOURCE
    }

    public h(a aVar, a<?, ?, ?> aVar2, Priority priority) {
        this.b = aVar;
        this.c = aVar2;
        this.a = priority;
    }

    public void a() {
        this.e = true;
        this.c.d();
    }

    public void run() {
        Exception exception = null;
        if (!this.e) {
            j d;
            try {
                d = d();
            } catch (Throwable e) {
                if (Log.isLoggable("EngineRunnable", 2)) {
                    Log.v("EngineRunnable", "Exception decoding", e);
                }
                Throwable th = e;
                Object obj = null;
            }
            if (this.e) {
                if (d != null) {
                    d.d();
                }
            } else if (d == null) {
                a(exception);
            } else {
                a(d);
            }
        }
    }

    private boolean c() {
        return this.d == b.CACHE;
    }

    private void a(j jVar) {
        this.b.a(jVar);
    }

    private void a(Exception exception) {
        if (c()) {
            this.d = b.SOURCE;
            this.b.b(this);
            return;
        }
        this.b.a(exception);
    }

    private j<?> d() throws Exception {
        if (c()) {
            return e();
        }
        return f();
    }

    private j<?> e() throws Exception {
        j<?> a;
        try {
            a = this.c.a();
        } catch (Exception e) {
            if (Log.isLoggable("EngineRunnable", 3)) {
                Log.d("EngineRunnable", "Exception decoding result from cache: " + e);
            }
            a = null;
        }
        if (a == null) {
            return this.c.b();
        }
        return a;
    }

    private j<?> f() throws Exception {
        return this.c.c();
    }

    public int b() {
        return this.a.ordinal();
    }
}
