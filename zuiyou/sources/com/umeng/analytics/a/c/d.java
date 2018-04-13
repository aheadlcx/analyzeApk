package com.umeng.analytics.a.c;

import java.io.Serializable;

public class d implements Serializable {
    private static final long a = 1;
    private String b;
    private long c;
    private long d;
    private String e;

    private d() {
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = null;
    }

    public d(String str, long j, long j2) {
        this(str, j, j2, null);
    }

    public d(String str, long j, long j2, String str2) {
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.b = str;
        this.c = j;
        this.d = j2;
        this.e = str2;
    }

    public d a() {
        this.d++;
        return this;
    }

    public String b() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public long d() {
        return this.c;
    }

    public long e() {
        return this.d;
    }

    public d a(d dVar) {
        this.d = dVar.e() + this.d;
        this.c = dVar.d();
        return this;
    }
}
