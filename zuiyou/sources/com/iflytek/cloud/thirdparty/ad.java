package com.iflytek.cloud.thirdparty;

import java.util.Map;

public class ad {
    private String a;
    private boolean b;
    private String c;
    private Map<String, byte[]> d;
    private long e;
    private String f;
    private String g;

    public ad(String str, String str2, String str3, String str4, Map<String, byte[]> map, long j, boolean z) {
        this.g = str;
        this.c = str2;
        this.a = str3;
        this.f = str4;
        this.d = map;
        this.e = j;
        this.b = z;
    }

    public String a() {
        return this.a;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public String b() {
        return this.c;
    }

    public Map<String, byte[]> c() {
        return this.d;
    }

    public long d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public boolean f() {
        return this.b;
    }
}
