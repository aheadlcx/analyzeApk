package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.proto.b;
import com.umeng.commonsdk.statistics.proto.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class a {
    private final int a = 10;
    private final int b = 20;
    private final String c;
    private List<com.umeng.commonsdk.statistics.proto.a> d;
    private b e;

    public abstract String f();

    public a(String str) {
        this.c = str;
    }

    public boolean a() {
        return g();
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        if (this.e == null || this.e.h() <= 20) {
            return true;
        }
        return false;
    }

    private boolean g() {
        b bVar = this.e;
        String b = bVar == null ? null : bVar.b();
        int h = bVar == null ? 0 : bVar.h();
        String a = a(f());
        if (a == null || a.equals(b)) {
            return false;
        }
        if (bVar == null) {
            bVar = new b();
        }
        bVar.a(a);
        bVar.a(System.currentTimeMillis());
        bVar.a(h + 1);
        com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
        aVar.a(this.c);
        aVar.c(a);
        aVar.b(b);
        aVar.a(bVar.e());
        if (this.d == null) {
            this.d = new ArrayList(2);
        }
        this.d.add(aVar);
        if (this.d.size() > 10) {
            this.d.remove(0);
        }
        this.e = bVar;
        return true;
    }

    public b d() {
        return this.e;
    }

    public void a(b bVar) {
        this.e = bVar;
    }

    public List<com.umeng.commonsdk.statistics.proto.a> e() {
        return this.d;
    }

    public void a(List<com.umeng.commonsdk.statistics.proto.a> list) {
        this.d = list;
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0 || "0".equals(trim) || "unknown".equals(trim.toLowerCase(Locale.US))) {
            return null;
        }
        return trim;
    }

    public void a(c cVar) {
        this.e = (b) cVar.c().get(this.c);
        List<com.umeng.commonsdk.statistics.proto.a> h = cVar.h();
        if (h != null && h.size() > 0) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            for (com.umeng.commonsdk.statistics.proto.a aVar : h) {
                if (this.c.equals(aVar.a)) {
                    this.d.add(aVar);
                }
            }
        }
    }
}
