package com.bumptech.glide.load.engine;

import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.g.g;
import com.bumptech.glide.i.h;
import com.bumptech.glide.load.engine.b.i;
import com.bumptech.glide.load.f;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class b implements com.bumptech.glide.load.engine.b.i.a, d, a {
    private final Map<com.bumptech.glide.load.b, c> a;
    private final f b;
    private final i c;
    private final a d;
    private final Map<com.bumptech.glide.load.b, WeakReference<g<?>>> e;
    private final k f;
    private final b g;
    private ReferenceQueue<g<?>> h;

    static class a {
        private final ExecutorService a;
        private final ExecutorService b;
        private final d c;

        public a(ExecutorService executorService, ExecutorService executorService2, d dVar) {
            this.a = executorService;
            this.b = executorService2;
            this.c = dVar;
        }

        public c a(com.bumptech.glide.load.b bVar, boolean z) {
            return new c(bVar, this.a, this.b, z, this.c);
        }
    }

    private static class b implements a {
        private final com.bumptech.glide.load.engine.b.a.a a;
        private volatile com.bumptech.glide.load.engine.b.a b;

        public b(com.bumptech.glide.load.engine.b.a.a aVar) {
            this.a = aVar;
        }

        public com.bumptech.glide.load.engine.b.a a() {
            if (this.b == null) {
                synchronized (this) {
                    if (this.b == null) {
                        this.b = this.a.a();
                    }
                    if (this.b == null) {
                        this.b = new com.bumptech.glide.load.engine.b.b();
                    }
                }
            }
            return this.b;
        }
    }

    public static class c {
        private final c a;
        private final g b;

        public c(g gVar, c cVar) {
            this.b = gVar;
            this.a = cVar;
        }

        public void a() {
            this.a.b(this.b);
        }
    }

    private static class d implements IdleHandler {
        private final Map<com.bumptech.glide.load.b, WeakReference<g<?>>> a;
        private final ReferenceQueue<g<?>> b;

        public d(Map<com.bumptech.glide.load.b, WeakReference<g<?>>> map, ReferenceQueue<g<?>> referenceQueue) {
            this.a = map;
            this.b = referenceQueue;
        }

        public boolean queueIdle() {
            e eVar = (e) this.b.poll();
            if (eVar != null) {
                this.a.remove(eVar.a);
            }
            return true;
        }
    }

    private static class e extends WeakReference<g<?>> {
        private final com.bumptech.glide.load.b a;

        public e(com.bumptech.glide.load.b bVar, g<?> gVar, ReferenceQueue<? super g<?>> referenceQueue) {
            super(gVar, referenceQueue);
            this.a = bVar;
        }
    }

    public b(i iVar, com.bumptech.glide.load.engine.b.a.a aVar, ExecutorService executorService, ExecutorService executorService2) {
        this(iVar, aVar, executorService, executorService2, null, null, null, null, null);
    }

    b(i iVar, com.bumptech.glide.load.engine.b.a.a aVar, ExecutorService executorService, ExecutorService executorService2, Map<com.bumptech.glide.load.b, c> map, f fVar, Map<com.bumptech.glide.load.b, WeakReference<g<?>>> map2, a aVar2, k kVar) {
        this.c = iVar;
        this.g = new b(aVar);
        if (map2 == null) {
            map2 = new HashMap();
        }
        this.e = map2;
        if (fVar == null) {
            fVar = new f();
        }
        this.b = fVar;
        if (map == null) {
            map = new HashMap();
        }
        this.a = map;
        if (aVar2 == null) {
            aVar2 = new a(executorService, executorService2, this);
        }
        this.d = aVar2;
        if (kVar == null) {
            kVar = new k();
        }
        this.f = kVar;
        iVar.a((com.bumptech.glide.load.engine.b.i.a) this);
    }

    public <T, Z, R> c a(com.bumptech.glide.load.b bVar, int i, int i2, com.bumptech.glide.load.a.c<T> cVar, com.bumptech.glide.f.b<T, Z> bVar2, f<Z> fVar, com.bumptech.glide.load.resource.e.c<Z, R> cVar2, Priority priority, boolean z, DiskCacheStrategy diskCacheStrategy, g gVar) {
        h.a();
        long a = com.bumptech.glide.i.d.a();
        com.bumptech.glide.load.b a2 = this.b.a(cVar.b(), bVar, i, i2, bVar2.a(), bVar2.b(), fVar, bVar2.d(), cVar2, bVar2.c());
        j b = b(a2, z);
        if (b != null) {
            gVar.a(b);
            if (Log.isLoggable("Engine", 2)) {
                a("Loaded resource from cache", a, a2);
            }
            return null;
        }
        b = a(a2, z);
        if (b != null) {
            gVar.a(b);
            if (Log.isLoggable("Engine", 2)) {
                a("Loaded resource from active resources", a, a2);
            }
            return null;
        }
        c cVar3 = (c) this.a.get(a2);
        if (cVar3 != null) {
            cVar3.a(gVar);
            if (Log.isLoggable("Engine", 2)) {
                a("Added to existing load", a, a2);
            }
            return new c(gVar, cVar3);
        }
        c a3 = this.d.a(a2, z);
        h hVar = new h(a3, new a(a2, i, i2, cVar, bVar2, fVar, cVar2, this.g, diskCacheStrategy, priority), priority);
        this.a.put(a2, a3);
        a3.a(gVar);
        a3.a(hVar);
        if (Log.isLoggable("Engine", 2)) {
            a("Started new load", a, a2);
        }
        return new c(gVar, a3);
    }

    private static void a(String str, long j, com.bumptech.glide.load.b bVar) {
        Log.v("Engine", str + " in " + com.bumptech.glide.i.d.a(j) + "ms, key: " + bVar);
    }

    private g<?> a(com.bumptech.glide.load.b bVar, boolean z) {
        if (!z) {
            return null;
        }
        g<?> gVar;
        WeakReference weakReference = (WeakReference) this.e.get(bVar);
        if (weakReference != null) {
            gVar = (g) weakReference.get();
            if (gVar != null) {
                gVar.e();
            } else {
                this.e.remove(bVar);
            }
        } else {
            gVar = null;
        }
        return gVar;
    }

    private g<?> b(com.bumptech.glide.load.b bVar, boolean z) {
        if (!z) {
            return null;
        }
        g<?> a = a(bVar);
        if (a == null) {
            return a;
        }
        a.e();
        this.e.put(bVar, new e(bVar, a, b()));
        return a;
    }

    private g<?> a(com.bumptech.glide.load.b bVar) {
        j a = this.c.a(bVar);
        if (a == null) {
            return null;
        }
        if (a instanceof g) {
            return (g) a;
        }
        return new g(a, true);
    }

    public void a(j jVar) {
        h.a();
        if (jVar instanceof g) {
            ((g) jVar).f();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    public void a(com.bumptech.glide.load.b bVar, g<?> gVar) {
        h.a();
        if (gVar != null) {
            gVar.a(bVar, this);
            if (gVar.a()) {
                this.e.put(bVar, new e(bVar, gVar, b()));
            }
        }
        this.a.remove(bVar);
    }

    public void a(c cVar, com.bumptech.glide.load.b bVar) {
        h.a();
        if (cVar.equals((c) this.a.get(bVar))) {
            this.a.remove(bVar);
        }
    }

    public void b(j<?> jVar) {
        h.a();
        this.f.a(jVar);
    }

    public void b(com.bumptech.glide.load.b bVar, g gVar) {
        h.a();
        this.e.remove(bVar);
        if (gVar.a()) {
            this.c.b(bVar, gVar);
        } else {
            this.f.a(gVar);
        }
    }

    public void a() {
        this.g.a().a();
    }

    private ReferenceQueue<g<?>> b() {
        if (this.h == null) {
            this.h = new ReferenceQueue();
            Looper.myQueue().addIdleHandler(new d(this.e, this.h));
        }
        return this.h;
    }
}
