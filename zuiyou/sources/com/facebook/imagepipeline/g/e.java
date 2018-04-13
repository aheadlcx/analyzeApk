package com.facebook.imagepipeline.g;

import android.util.Pair;
import com.facebook.c.c;
import com.facebook.c.d;
import com.facebook.cache.common.b;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.h;
import com.facebook.common.references.a;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class e implements Closeable {
    @Nullable
    private final a<PooledByteBuffer> a;
    @Nullable
    private final i<FileInputStream> b;
    private c c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    @Nullable
    private b i;

    public e(a<PooledByteBuffer> aVar) {
        this.c = c.a;
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = 1;
        this.h = -1;
        g.a(a.a((a) aVar));
        this.a = aVar.b();
        this.b = null;
    }

    public e(i<FileInputStream> iVar) {
        this.c = c.a;
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = 1;
        this.h = -1;
        g.a(iVar);
        this.a = null;
        this.b = iVar;
    }

    public e(i<FileInputStream> iVar, int i) {
        this((i) iVar);
        this.h = i;
    }

    public static e a(e eVar) {
        return eVar != null ? eVar.a() : null;
    }

    public e a() {
        e eVar;
        if (this.b != null) {
            eVar = new e(this.b, this.h);
        } else {
            a b = a.b(this.a);
            if (b == null) {
                eVar = null;
            } else {
                try {
                    eVar = new e(b);
                } catch (Throwable th) {
                    a.c(b);
                }
            }
            a.c(b);
        }
        if (eVar != null) {
            eVar.b(this);
        }
        return eVar;
    }

    public void close() {
        a.c(this.a);
    }

    public synchronized boolean b() {
        boolean z;
        z = a.a(this.a) || this.b != null;
        return z;
    }

    public a<PooledByteBuffer> c() {
        return a.b(this.a);
    }

    public InputStream d() {
        if (this.b != null) {
            return (InputStream) this.b.b();
        }
        a b = a.b(this.a);
        if (b == null) {
            return null;
        }
        try {
            InputStream hVar = new h((PooledByteBuffer) b.a());
            return hVar;
        } finally {
            a.c(b);
        }
    }

    public void a(c cVar) {
        this.c = cVar;
    }

    public void a(int i) {
        this.f = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public void c(int i) {
        this.d = i;
    }

    public void d(int i) {
        this.g = i;
    }

    public void a(@Nullable b bVar) {
        this.i = bVar;
    }

    public c e() {
        return this.c;
    }

    public int f() {
        return this.d;
    }

    public int g() {
        return this.e;
    }

    public int h() {
        return this.f;
    }

    public int i() {
        return this.g;
    }

    @Nullable
    public b j() {
        return this.i;
    }

    public boolean e(int i) {
        if (this.c != com.facebook.c.b.a || this.b != null) {
            return true;
        }
        g.a(this.a);
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) this.a.a();
        boolean z = pooledByteBuffer.read(i + -2) == (byte) -1 && pooledByteBuffer.read(i - 1) == (byte) -39;
        return z;
    }

    public int k() {
        if (this.a == null || this.a.a() == null) {
            return this.h;
        }
        return ((PooledByteBuffer) this.a.a()).size();
    }

    public void l() {
        Pair m;
        c c = d.c(d());
        this.c = c;
        if (com.facebook.c.b.a(c)) {
            m = m();
        } else {
            m = n();
        }
        if (c != com.facebook.c.b.a || this.d != -1) {
            this.d = 0;
        } else if (m != null) {
            this.d = com.facebook.d.b.a(com.facebook.d.b.a(d()));
        }
    }

    private Pair<Integer, Integer> m() {
        Pair<Integer, Integer> a = com.facebook.d.e.a(d());
        if (a != null) {
            this.e = ((Integer) a.first).intValue();
            this.f = ((Integer) a.second).intValue();
        }
        return a;
    }

    private Pair<Integer, Integer> n() {
        InputStream inputStream = null;
        try {
            inputStream = d();
            Pair<Integer, Integer> a = com.facebook.d.a.a(inputStream);
            if (a != null) {
                this.e = ((Integer) a.first).intValue();
                this.f = ((Integer) a.second).intValue();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
            return a;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
        }
    }

    public void b(e eVar) {
        this.c = eVar.e();
        this.e = eVar.g();
        this.f = eVar.h();
        this.d = eVar.f();
        this.g = eVar.i();
        this.h = eVar.k();
        this.i = eVar.j();
    }

    public static boolean c(e eVar) {
        return eVar.d >= 0 && eVar.e >= 0 && eVar.f >= 0;
    }

    public static void d(@Nullable e eVar) {
        if (eVar != null) {
            eVar.close();
        }
    }

    public static boolean e(@Nullable e eVar) {
        return eVar != null && eVar.b();
    }
}
