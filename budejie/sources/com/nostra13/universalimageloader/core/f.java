package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.c.a;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class f {
    final e a;
    private Executor b;
    private Executor c;
    private Executor d;
    private final Map<Integer, String> e = Collections.synchronizedMap(new HashMap());
    private final Map<String, ReentrantLock> f = new WeakHashMap();
    private final AtomicBoolean g = new AtomicBoolean(false);
    private final AtomicBoolean h = new AtomicBoolean(false);
    private final AtomicBoolean i = new AtomicBoolean(false);
    private final Object j = new Object();

    f(e eVar) {
        this.a = eVar;
        this.b = eVar.g;
        this.c = eVar.h;
        this.d = a.a();
    }

    void a(final h hVar) {
        this.d.execute(new Runnable(this) {
            final /* synthetic */ f b;

            public void run() {
                File a = this.b.a.p.a(hVar.a());
                Object obj = (a == null || !a.exists()) ? null : 1;
                this.b.g();
                if (obj != null) {
                    this.b.c.execute(hVar);
                } else {
                    this.b.b.execute(hVar);
                }
            }
        });
    }

    void a(i iVar) {
        g();
        this.c.execute(iVar);
    }

    private void g() {
        if (!this.a.i && ((ExecutorService) this.b).isShutdown()) {
            this.b = h();
        }
        if (!this.a.j && ((ExecutorService) this.c).isShutdown()) {
            this.c = h();
        }
    }

    private Executor h() {
        return a.a(this.a.k, this.a.l, this.a.m);
    }

    String a(a aVar) {
        return (String) this.e.get(Integer.valueOf(aVar.f()));
    }

    void a(a aVar, String str) {
        this.e.put(Integer.valueOf(aVar.f()), str);
    }

    void b(a aVar) {
        this.e.remove(Integer.valueOf(aVar.f()));
    }

    void a() {
        this.g.set(true);
    }

    void b() {
        this.g.set(false);
        synchronized (this.j) {
            this.j.notifyAll();
        }
    }

    void a(Runnable runnable) {
        this.d.execute(runnable);
    }

    public ReentrantLock a(String str) {
        ReentrantLock reentrantLock = (ReentrantLock) this.f.get(str);
        if (reentrantLock != null) {
            return reentrantLock;
        }
        reentrantLock = new ReentrantLock();
        this.f.put(str, reentrantLock);
        return reentrantLock;
    }

    AtomicBoolean c() {
        return this.g;
    }

    Object d() {
        return this.j;
    }

    boolean e() {
        return this.h.get();
    }

    boolean f() {
        return this.i.get();
    }
}
