package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.g.g;
import com.bumptech.glide.i.h;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class c implements a {
    private static final a a = new a();
    private static final Handler b = new Handler(Looper.getMainLooper(), new b());
    private final List<g> c;
    private final a d;
    private final d e;
    private final com.bumptech.glide.load.b f;
    private final ExecutorService g;
    private final ExecutorService h;
    private final boolean i;
    private boolean j;
    private j<?> k;
    private boolean l;
    private Exception m;
    private boolean n;
    private Set<g> o;
    private h p;
    private g<?> q;
    private volatile Future<?> r;

    static class a {
        a() {
        }

        public <R> g<R> a(j<R> jVar, boolean z) {
            return new g(jVar, z);
        }
    }

    private static class b implements Callback {
        private b() {
        }

        public boolean handleMessage(Message message) {
            if (1 != message.what && 2 != message.what) {
                return false;
            }
            c cVar = (c) message.obj;
            if (1 == message.what) {
                cVar.b();
            } else {
                cVar.c();
            }
            return true;
        }
    }

    public c(com.bumptech.glide.load.b bVar, ExecutorService executorService, ExecutorService executorService2, boolean z, d dVar) {
        this(bVar, executorService, executorService2, z, dVar, a);
    }

    public c(com.bumptech.glide.load.b bVar, ExecutorService executorService, ExecutorService executorService2, boolean z, d dVar, a aVar) {
        this.c = new ArrayList();
        this.f = bVar;
        this.g = executorService;
        this.h = executorService2;
        this.i = z;
        this.e = dVar;
        this.d = aVar;
    }

    public void a(h hVar) {
        this.p = hVar;
        this.r = this.g.submit(hVar);
    }

    public void b(h hVar) {
        this.r = this.h.submit(hVar);
    }

    public void a(g gVar) {
        h.a();
        if (this.l) {
            gVar.a(this.q);
        } else if (this.n) {
            gVar.a(this.m);
        } else {
            this.c.add(gVar);
        }
    }

    public void b(g gVar) {
        h.a();
        if (this.l || this.n) {
            c(gVar);
            return;
        }
        this.c.remove(gVar);
        if (this.c.isEmpty()) {
            a();
        }
    }

    private void c(g gVar) {
        if (this.o == null) {
            this.o = new HashSet();
        }
        this.o.add(gVar);
    }

    private boolean d(g gVar) {
        return this.o != null && this.o.contains(gVar);
    }

    void a() {
        if (!this.n && !this.l && !this.j) {
            this.p.a();
            Future future = this.r;
            if (future != null) {
                future.cancel(true);
            }
            this.j = true;
            this.e.a(this, this.f);
        }
    }

    public void a(j<?> jVar) {
        this.k = jVar;
        b.obtainMessage(1, this).sendToTarget();
    }

    private void b() {
        if (this.j) {
            this.k.d();
        } else if (this.c.isEmpty()) {
            throw new IllegalStateException("Received a resource without any callbacks to notify");
        } else {
            this.q = this.d.a(this.k, this.i);
            this.l = true;
            this.q.e();
            this.e.a(this.f, this.q);
            for (g gVar : this.c) {
                if (!d(gVar)) {
                    this.q.e();
                    gVar.a(this.q);
                }
            }
            this.q.f();
        }
    }

    public void a(Exception exception) {
        this.m = exception;
        b.obtainMessage(2, this).sendToTarget();
    }

    private void c() {
        if (!this.j) {
            if (this.c.isEmpty()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
            this.n = true;
            this.e.a(this.f, null);
            for (g gVar : this.c) {
                if (!d(gVar)) {
                    gVar.a(this.m);
                }
            }
        }
    }
}
