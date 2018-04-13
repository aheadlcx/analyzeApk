package com.umeng.analytics.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class y {
    private final int a = 10;
    private final int b = 20;
    private final String c;
    private List<bk> d;
    private bl e;

    public abstract String f();

    public y(String str) {
        this.c = str;
    }

    public boolean a() {
        return g();
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        if (this.e == null || this.e.i() <= 20) {
            return true;
        }
        return false;
    }

    private boolean g() {
        bl blVar = this.e;
        String c = blVar == null ? null : blVar.c();
        int i = blVar == null ? 0 : blVar.i();
        String a = a(f());
        if (a == null || a.equals(c)) {
            return false;
        }
        if (blVar == null) {
            blVar = new bl();
        }
        blVar.a(a);
        blVar.a(System.currentTimeMillis());
        blVar.a(i + 1);
        bk bkVar = new bk();
        bkVar.a(this.c);
        bkVar.c(a);
        bkVar.b(c);
        bkVar.a(blVar.f());
        if (this.d == null) {
            this.d = new ArrayList(2);
        }
        this.d.add(bkVar);
        if (this.d.size() > 10) {
            this.d.remove(0);
        }
        this.e = blVar;
        return true;
    }

    public bl d() {
        return this.e;
    }

    public void a(bl blVar) {
        this.e = blVar;
    }

    public List<bk> e() {
        return this.d;
    }

    public void a(List<bk> list) {
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

    public void a(bm bmVar) {
        this.e = (bl) bmVar.d().get(this.c);
        List<bk> i = bmVar.i();
        if (i != null && i.size() > 0) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            for (bk bkVar : i) {
                if (this.c.equals(bkVar.a)) {
                    this.d.add(bkVar);
                }
            }
        }
    }
}
