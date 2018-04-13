package com.umeng.analytics.pro;

import java.io.Serializable;

public class ct implements Serializable {
    private final boolean a;
    public final byte b;
    private final String c;
    private final boolean d;

    public ct(byte b, boolean z) {
        this.b = b;
        this.a = false;
        this.c = null;
        this.d = z;
    }

    public ct(byte b) {
        this(b, false);
    }

    public ct(byte b, String str) {
        this.b = b;
        this.a = true;
        this.c = str;
        this.d = false;
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.b == (byte) 12;
    }

    public boolean d() {
        return this.b == dm.m || this.b == dm.k || this.b == dm.l;
    }

    public boolean e() {
        return this.d;
    }
}
