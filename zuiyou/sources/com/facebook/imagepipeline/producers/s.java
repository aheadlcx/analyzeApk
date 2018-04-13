package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.imagepipeline.g.e;

public class s {
    private final j<e> a;
    private final aj b;
    private long c = 0;

    public s(j<e> jVar, aj ajVar) {
        this.a = jVar;
        this.b = ajVar;
    }

    public j<e> a() {
        return this.a;
    }

    public aj b() {
        return this.b;
    }

    public String c() {
        return this.b.b();
    }

    public al d() {
        return this.b.c();
    }

    public Uri e() {
        return this.b.a().b();
    }

    public long f() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }
}
