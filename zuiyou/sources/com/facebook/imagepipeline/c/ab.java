package com.facebook.imagepipeline.c;

import com.facebook.cache.common.b;
import com.facebook.common.internal.g;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.g.e;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

public class ab {
    private static final Class<?> a = ab.class;
    @GuardedBy
    private Map<b, e> b = new HashMap();

    private ab() {
    }

    public static ab a() {
        return new ab();
    }

    public synchronized void a(b bVar, e eVar) {
        g.a((Object) bVar);
        g.a(e.e(eVar));
        e.d((e) this.b.put(bVar, e.a(eVar)));
        b();
    }

    public synchronized boolean b(b bVar, e eVar) {
        boolean z;
        g.a((Object) bVar);
        g.a((Object) eVar);
        g.a(e.e(eVar));
        e eVar2 = (e) this.b.get(bVar);
        if (eVar2 == null) {
            z = false;
        } else {
            a c = eVar2.c();
            a c2 = eVar.c();
            if (!(c == null || c2 == null)) {
                try {
                    if (c.a() == c2.a()) {
                        this.b.remove(bVar);
                        a.c(c2);
                        a.c(c);
                        e.d(eVar2);
                        b();
                        z = true;
                    }
                } catch (Throwable th) {
                    a.c(c2);
                    a.c(c);
                    e.d(eVar2);
                }
            }
            a.c(c2);
            a.c(c);
            e.d(eVar2);
            z = false;
        }
        return z;
    }

    public synchronized e a(b bVar) {
        e eVar;
        g.a((Object) bVar);
        eVar = (e) this.b.get(bVar);
        if (eVar != null) {
            synchronized (eVar) {
                if (e.e(eVar)) {
                    e a = e.a(eVar);
                    eVar = a;
                } else {
                    this.b.remove(bVar);
                    com.facebook.common.c.a.b(a, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(eVar)), bVar.a(), Integer.valueOf(System.identityHashCode(bVar)));
                    eVar = null;
                }
            }
        }
        return eVar;
    }

    public synchronized boolean b(b bVar) {
        boolean z;
        g.a((Object) bVar);
        if (this.b.containsKey(bVar)) {
            e eVar = (e) this.b.get(bVar);
            synchronized (eVar) {
                if (e.e(eVar)) {
                    z = true;
                } else {
                    this.b.remove(bVar);
                    com.facebook.common.c.a.b(a, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(eVar)), bVar.a(), Integer.valueOf(System.identityHashCode(bVar)));
                    z = false;
                }
            }
        } else {
            z = false;
        }
        return z;
    }

    private synchronized void b() {
        com.facebook.common.c.a.a(a, "Count = %d", Integer.valueOf(this.b.size()));
    }
}
