package com.facebook.cache.disk;

import com.facebook.cache.common.CacheEventListener.EvictionReason;
import com.facebook.cache.common.a;
import com.facebook.cache.common.b;
import com.facebook.infer.annotation.ReturnsOwnership;
import java.io.IOException;

public class i implements a {
    private static final Object a = new Object();
    private static i b;
    private static int c;
    private b d;
    private String e;
    private long f;
    private long g;
    private long h;
    private IOException i;
    private EvictionReason j;
    private i k;

    @ReturnsOwnership
    public static i a() {
        synchronized (a) {
            if (b != null) {
                i iVar = b;
                b = iVar.k;
                iVar.k = null;
                c--;
                return iVar;
            }
            return new i();
        }
    }

    private i() {
    }

    public i a(b bVar) {
        this.d = bVar;
        return this;
    }

    public i a(String str) {
        this.e = str;
        return this;
    }

    public i a(long j) {
        this.f = j;
        return this;
    }

    public i b(long j) {
        this.h = j;
        return this;
    }

    public i c(long j) {
        this.g = j;
        return this;
    }

    public i a(IOException iOException) {
        this.i = iOException;
        return this;
    }

    public i a(EvictionReason evictionReason) {
        this.j = evictionReason;
        return this;
    }

    public void b() {
        synchronized (a) {
            if (c < 5) {
                c();
                c++;
                if (b != null) {
                    this.k = b;
                }
                b = this;
            }
        }
    }

    private void c() {
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = null;
    }
}
