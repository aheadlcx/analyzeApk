package com.umeng.analytics.a.c;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class e implements Serializable {
    private static final long a = 1;
    private List<String> b = new ArrayList();
    private String c;
    private long d;
    private long e;
    private String f;

    public e(List<String> list, long j, String str, long j2) {
        this.b = list;
        this.d = j;
        this.c = str;
        this.e = j2;
        f();
    }

    private void f() {
        this.f = com.umeng.analytics.a.d.e.a(this.e);
    }

    public List<String> a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public long c() {
        return this.d;
    }

    public long d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }
}
