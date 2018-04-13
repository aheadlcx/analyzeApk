package com.facebook.imagepipeline.c;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.android.internal.util.Predicate;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.b.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class h<K, V> implements com.facebook.common.memory.b, t<K, V> {
    static final long a = TimeUnit.MINUTES.toMillis(5);
    @GuardedBy
    final g<K, b<K, V>> b;
    @GuardedBy
    final g<K, b<K, V>> c;
    @GuardedBy
    final Map<Bitmap, Object> d = new WeakHashMap();
    @GuardedBy
    protected u e;
    private final ac<V> f;
    private final h$a g;
    private final i<u> h;
    @GuardedBy
    private long i;

    static class b<K, V> {
        public final K a;
        public final a<V> b;
        public int c = 0;
        public boolean d = false;
        @Nullable
        public final h$c<K> e;

        private b(K k, a<V> aVar, @Nullable h$c<K> h_c) {
            this.a = g.a(k);
            this.b = (a) g.a(a.b(aVar));
            this.e = h_c;
        }

        static <K, V> b<K, V> a(K k, a<V> aVar, @Nullable h$c<K> h_c) {
            return new b(k, aVar, h_c);
        }
    }

    public h(ac<V> acVar, h$a h_a, i<u> iVar, f fVar, boolean z) {
        this.f = acVar;
        this.b = new g(a((ac) acVar));
        this.c = new g(a((ac) acVar));
        this.g = h_a;
        this.h = iVar;
        this.e = (u) this.h.b();
        this.i = SystemClock.uptimeMillis();
        if (z) {
            fVar.a(new h$1(this));
        }
    }

    private ac<b<K, V>> a(ac<V> acVar) {
        return new h$2(this, acVar);
    }

    public a<V> a(K k, a<V> aVar) {
        return a(k, aVar, null);
    }

    public a<V> a(K k, a<V> aVar, h$c<K> h_c) {
        b bVar;
        a i;
        a<V> a;
        g.a(k);
        g.a(aVar);
        c();
        synchronized (this) {
            bVar = (b) this.b.b(k);
            b bVar2 = (b) this.c.b(k);
            if (bVar2 != null) {
                f(bVar2);
                i = i(bVar2);
            } else {
                i = null;
            }
            if (b(aVar.a())) {
                bVar2 = b.a(k, aVar, h_c);
                this.c.a(k, bVar2);
                a = a(bVar2);
            } else {
                a = null;
            }
        }
        a.c(i);
        d(bVar);
        d();
        return a;
    }

    private synchronized boolean b(V v) {
        boolean z;
        int a = this.f.a(v);
        z = a <= this.e.e && a() <= this.e.b - 1 && b() <= this.e.a - a;
        return z;
    }

    @Nullable
    public a<V> a(K k) {
        b bVar;
        a<V> a;
        g.a(k);
        synchronized (this) {
            bVar = (b) this.b.b(k);
            b bVar2 = (b) this.c.a((Object) k);
            if (bVar2 != null) {
                a = a(bVar2);
            } else {
                a = null;
            }
        }
        d(bVar);
        c();
        d();
        return a;
    }

    private synchronized a<V> a(b<K, V> bVar) {
        g(bVar);
        return a.a(bVar.b.a(), new h$3(this, bVar));
    }

    private void b(b<K, V> bVar) {
        a i;
        g.a(bVar);
        synchronized (this) {
            h(bVar);
            boolean c = c((b) bVar);
            i = i(bVar);
        }
        a.c(i);
        if (!c) {
            bVar = null;
        }
        e(bVar);
        c();
        d();
    }

    private synchronized boolean c(b<K, V> bVar) {
        boolean z;
        if (bVar.d || bVar.c != 0) {
            z = false;
        } else {
            this.b.a(bVar.a, bVar);
            z = true;
        }
        return z;
    }

    public int a(Predicate<K> predicate) {
        ArrayList a;
        ArrayList a2;
        synchronized (this) {
            a = this.b.a((Predicate) predicate);
            a2 = this.c.a((Predicate) predicate);
            c(a2);
        }
        a(a2);
        b(a);
        c();
        d();
        return a2.size();
    }

    private synchronized void c() {
        if (this.i + a <= SystemClock.uptimeMillis()) {
            this.i = SystemClock.uptimeMillis();
            this.e = (u) this.h.b();
        }
    }

    private void d() {
        ArrayList a;
        synchronized (this) {
            a = a(Math.min(this.e.d, this.e.b - a()), Math.min(this.e.c, this.e.a - b()));
            c(a);
        }
        a(a);
        b(a);
    }

    @Nullable
    private synchronized ArrayList<b<K, V>> a(int i, int i2) {
        ArrayList<b<K, V>> arrayList;
        int max = Math.max(i, 0);
        int max2 = Math.max(i2, 0);
        if (this.b.a() > max || this.b.b() > max2) {
            arrayList = new ArrayList();
            while (true) {
                if (this.b.a() <= max && this.b.b() <= max2) {
                    break;
                }
                Object c = this.b.c();
                this.b.b(c);
                arrayList.add(this.c.b(c));
            }
        } else {
            arrayList = null;
        }
        return arrayList;
    }

    private void a(@Nullable ArrayList<b<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a.c(i((b) it.next()));
            }
        }
    }

    private void b(@Nullable ArrayList<b<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                d((b) it.next());
            }
        }
    }

    private static <K, V> void d(@Nullable b<K, V> bVar) {
        if (bVar != null && bVar.e != null) {
            bVar.e.a(bVar.a, false);
        }
    }

    private static <K, V> void e(@Nullable b<K, V> bVar) {
        if (bVar != null && bVar.e != null) {
            bVar.e.a(bVar.a, true);
        }
    }

    private synchronized void c(@Nullable ArrayList<b<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                f((b) it.next());
            }
        }
    }

    private synchronized void f(b<K, V> bVar) {
        boolean z = true;
        synchronized (this) {
            g.a(bVar);
            if (bVar.d) {
                z = false;
            }
            g.b(z);
            bVar.d = true;
        }
    }

    private synchronized void g(b<K, V> bVar) {
        g.a(bVar);
        g.b(!bVar.d);
        bVar.c++;
    }

    private synchronized void h(b<K, V> bVar) {
        g.a(bVar);
        g.b(bVar.c > 0);
        bVar.c--;
    }

    @Nullable
    private synchronized a<V> i(b<K, V> bVar) {
        a<V> aVar;
        g.a(bVar);
        aVar = (bVar.d && bVar.c == 0) ? bVar.b : null;
        return aVar;
    }

    public synchronized int a() {
        return this.c.a() - this.b.a();
    }

    public synchronized int b() {
        return this.c.b() - this.b.b();
    }
}
