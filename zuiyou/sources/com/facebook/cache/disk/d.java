package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheEventListener.EvictionReason;
import com.facebook.cache.common.h;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.statfs.StatFsHelper.StorageType;
import com.facebook.common.time.c;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class d implements h, com.facebook.common.a.a {
    private static final Class<?> b = d.class;
    private static final long c = TimeUnit.HOURS.toMillis(2);
    private static final long d = TimeUnit.MINUTES.toMillis(30);
    @GuardedBy
    final Set<String> a;
    private final long e;
    private final long f;
    private final CountDownLatch g;
    private long h;
    private final CacheEventListener i;
    private long j;
    private final long k;
    private final StatFsHelper l;
    private final c m;
    private final g n;
    private final CacheErrorLogger o;
    private final boolean p;
    private final a q;
    private final com.facebook.common.time.a r;
    private final Object s = new Object();
    private boolean t;

    static class a {
        private boolean a = false;
        private long b = -1;
        private long c = -1;

        a() {
        }

        public synchronized boolean a() {
            return this.a;
        }

        public synchronized void b() {
            this.a = false;
            this.c = -1;
            this.b = -1;
        }

        public synchronized void a(long j, long j2) {
            this.c = j2;
            this.b = j;
            this.a = true;
        }

        public synchronized void b(long j, long j2) {
            if (this.a) {
                this.b += j;
                this.c += j2;
            }
        }

        public synchronized long c() {
            return this.b;
        }

        public synchronized long d() {
            return this.c;
        }
    }

    public static class b {
        public final long a;
        public final long b;
        public final long c;

        public b(long j, long j2, long j3) {
            this.a = j;
            this.b = j2;
            this.c = j3;
        }
    }

    public d(c cVar, g gVar, b bVar, CacheEventListener cacheEventListener, CacheErrorLogger cacheErrorLogger, @Nullable com.facebook.common.a.b bVar2, Context context, Executor executor, boolean z) {
        this.e = bVar.b;
        this.f = bVar.c;
        this.h = bVar.c;
        this.l = StatFsHelper.a();
        this.m = cVar;
        this.n = gVar;
        this.j = -1;
        this.i = cacheEventListener;
        this.k = bVar.a;
        this.o = cacheErrorLogger;
        this.q = new a();
        if (bVar2 != null) {
            bVar2.a(this);
        }
        this.r = c.b();
        this.p = z;
        this.a = new HashSet();
        if (this.p) {
            this.g = new CountDownLatch(1);
            executor.execute(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (this.a.s) {
                        this.a.c();
                    }
                    this.a.t = true;
                    this.a.g.countDown();
                }
            });
            return;
        }
        this.g = new CountDownLatch(0);
    }

    public com.facebook.a.a a(com.facebook.cache.common.b bVar) {
        Object a = i.a().a(bVar);
        try {
            com.facebook.a.a aVar;
            synchronized (this.s) {
                List a2 = com.facebook.cache.common.c.a(bVar);
                int i = 0;
                Object obj = null;
                aVar = null;
                while (i < a2.size()) {
                    String str = (String) a2.get(i);
                    a.a(str);
                    com.facebook.a.a b = this.m.b(str, bVar);
                    com.facebook.a.a aVar2;
                    if (b != null) {
                        aVar2 = b;
                        obj = str;
                        aVar = aVar2;
                        break;
                    }
                    i++;
                    aVar2 = b;
                    String str2 = str;
                    aVar = aVar2;
                }
                if (aVar == null) {
                    this.i.b(a);
                    this.a.remove(obj);
                } else {
                    this.i.a(a);
                    this.a.add(obj);
                }
            }
            a.b();
            return aVar;
        } catch (IOException e) {
            try {
                this.o.a(CacheErrorCategory.GENERIC_IO, b, "getResource", e);
                a.a(e);
                this.i.e(a);
                return null;
            } finally {
                a.b();
            }
        }
    }

    private com.facebook.cache.disk.c.b a(String str, com.facebook.cache.common.b bVar) throws IOException {
        a();
        return this.m.a(str, bVar);
    }

    private com.facebook.a.a a(com.facebook.cache.disk.c.b bVar, com.facebook.cache.common.b bVar2, String str) throws IOException {
        com.facebook.a.a a;
        synchronized (this.s) {
            a = bVar.a(bVar2);
            this.a.add(str);
            this.q.b(a.b(), 1);
        }
        return a;
    }

    public com.facebook.a.a a(com.facebook.cache.common.b bVar, h hVar) throws IOException {
        String b;
        com.facebook.cache.disk.c.b a;
        Object a2 = i.a().a(bVar);
        this.i.c(a2);
        synchronized (this.s) {
            b = com.facebook.cache.common.c.b(bVar);
        }
        a2.a(b);
        try {
            a = a(b, bVar);
            a.a(hVar, bVar);
            com.facebook.a.a a3 = a(a, bVar, b);
            a2.a(a3.b()).b(this.q.c());
            this.i.d(a2);
            if (!a.a()) {
                com.facebook.common.c.a.e(b, "Failed to delete temp file");
            }
            a2.b();
            return a3;
        } catch (Throwable e) {
            try {
                a2.a((IOException) e);
                this.i.f(a2);
                com.facebook.common.c.a.b(b, "Failed inserting a file into the cache", e);
                throw e;
            } catch (Throwable th) {
                a2.b();
            }
        } catch (Throwable th2) {
            if (!a.a()) {
                com.facebook.common.c.a.e(b, "Failed to delete temp file");
            }
        }
    }

    private void a() throws IOException {
        synchronized (this.s) {
            boolean c = c();
            b();
            long c2 = this.q.c();
            if (c2 > this.h && !c) {
                this.q.b();
                c();
            }
            if (c2 > this.h) {
                a((this.h * 9) / 10, EvictionReason.CACHE_FULL);
            }
        }
    }

    @GuardedBy
    private void a(long j, EvictionReason evictionReason) throws IOException {
        try {
            Collection<com.facebook.cache.disk.c.a> a = a(this.m.d());
            long c = this.q.c();
            long j2 = c - j;
            int i = 0;
            long j3 = 0;
            for (com.facebook.cache.disk.c.a aVar : a) {
                if (j3 > j2) {
                    break;
                }
                long a2 = this.m.a(aVar);
                this.a.remove(aVar.a());
                if (a2 > 0) {
                    i++;
                    j3 += a2;
                    Object c2 = i.a().a(aVar.a()).a(evictionReason).a(a2).b(c - j3).c(j);
                    this.i.g(c2);
                    c2.b();
                }
                i = i;
                j3 = j3;
            }
            this.q.b(-j3, (long) (-i));
            this.m.b();
        } catch (Throwable e) {
            this.o.a(CacheErrorCategory.EVICTION, b, "evictAboveSize: " + e.getMessage(), e);
            throw e;
        }
    }

    private Collection<com.facebook.cache.disk.c.a> a(Collection<com.facebook.cache.disk.c.a> collection) {
        long a = c + this.r.a();
        Collection arrayList = new ArrayList(collection.size());
        Collection arrayList2 = new ArrayList(collection.size());
        for (com.facebook.cache.disk.c.a aVar : collection) {
            if (aVar.b() > a) {
                arrayList.add(aVar);
            } else {
                arrayList2.add(aVar);
            }
        }
        Collections.sort(arrayList2, this.n.a());
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    @GuardedBy
    private void b() {
        if (this.l.a(this.m.a() ? StorageType.EXTERNAL : StorageType.INTERNAL, this.f - this.q.c())) {
            this.h = this.e;
        } else {
            this.h = this.f;
        }
    }

    public boolean b(com.facebook.cache.common.b bVar) {
        synchronized (this.s) {
            List a = com.facebook.cache.common.c.a(bVar);
            for (int i = 0; i < a.size(); i++) {
                if (this.a.contains((String) a.get(i))) {
                    return true;
                }
            }
            return false;
        }
    }

    @GuardedBy
    private boolean c() {
        long a = this.r.a();
        if (!this.q.a() || this.j == -1 || a - this.j > d) {
            return d();
        }
        return false;
    }

    @GuardedBy
    private boolean d() {
        Object obj = null;
        int i = 0;
        int i2 = 0;
        long j = -1;
        long a = this.r.a();
        long j2 = a + c;
        if (this.p && this.a.isEmpty()) {
            Set set = this.a;
        } else if (this.p) {
            Object hashSet = new HashSet();
        } else {
            Collection collection = null;
        }
        try {
            long j3 = 0;
            int i3 = 0;
            for (com.facebook.cache.disk.c.a aVar : this.m.d()) {
                long max;
                int i4;
                int i5;
                Object obj2;
                int i6 = i3 + 1;
                j3 += aVar.d();
                if (aVar.b() > j2) {
                    int i7 = i + 1;
                    i = (int) (((long) i2) + aVar.d());
                    max = Math.max(aVar.b() - a, j);
                    i4 = i;
                    i5 = i7;
                    obj2 = 1;
                } else {
                    if (this.p) {
                        set.add(aVar.a());
                    }
                    long j4 = j;
                    i4 = i2;
                    i5 = i;
                    max = j4;
                    obj2 = obj;
                }
                obj = obj2;
                i3 = i6;
                i = i5;
                i2 = i4;
                j = max;
            }
            if (obj != null) {
                this.o.a(CacheErrorCategory.READ_INVALID_ENTRY, b, "Future timestamp found in " + i + " files , with a total size of " + i2 + " bytes, and a maximum time delta of " + j + Parameters.MESSAGE_SEQ, null);
            }
            if (!(this.q.d() == ((long) i3) && this.q.c() == j3)) {
                if (this.p && this.a != set) {
                    this.a.clear();
                    this.a.addAll(set);
                }
                this.q.a(j3, (long) i3);
            }
            this.j = a;
            return true;
        } catch (Throwable e) {
            this.o.a(CacheErrorCategory.GENERIC_IO, b, "calcFileCacheSize: " + e.getMessage(), e);
            return false;
        }
    }
}
