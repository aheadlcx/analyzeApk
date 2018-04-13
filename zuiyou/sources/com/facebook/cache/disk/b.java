package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.e;
import com.facebook.cache.common.f;
import com.facebook.common.a.c;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import java.io.File;
import javax.annotation.Nullable;

public class b {
    private final int a;
    private final String b;
    private final i<File> c;
    private final long d;
    private final long e;
    private final long f;
    private final g g;
    private final CacheErrorLogger h;
    private final CacheEventListener i;
    private final com.facebook.common.a.b j;
    private final Context k;
    private final boolean l;

    private b(b$a b_a) {
        CacheErrorLogger a;
        CacheEventListener a2;
        com.facebook.common.a.b a3;
        this.a = b$a.a(b_a);
        this.b = (String) g.a(b$a.b(b_a));
        this.c = (i) g.a(b$a.c(b_a));
        this.d = b$a.d(b_a);
        this.e = b$a.e(b_a);
        this.f = b$a.f(b_a);
        this.g = (g) g.a(b$a.g(b_a));
        if (b$a.h(b_a) == null) {
            a = e.a();
        } else {
            a = b$a.h(b_a);
        }
        this.h = a;
        if (b$a.i(b_a) == null) {
            a2 = f.a();
        } else {
            a2 = b$a.i(b_a);
        }
        this.i = a2;
        if (b$a.j(b_a) == null) {
            a3 = c.a();
        } else {
            a3 = b$a.j(b_a);
        }
        this.j = a3;
        this.k = b$a.k(b_a);
        this.l = b$a.l(b_a);
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public i<File> c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public long e() {
        return this.e;
    }

    public long f() {
        return this.f;
    }

    public g g() {
        return this.g;
    }

    public CacheErrorLogger h() {
        return this.h;
    }

    public CacheEventListener i() {
        return this.i;
    }

    public com.facebook.common.a.b j() {
        return this.j;
    }

    public Context k() {
        return this.k;
    }

    public boolean l() {
        return this.l;
    }

    public static b$a a(@Nullable Context context) {
        return new b$a(context, null);
    }
}
