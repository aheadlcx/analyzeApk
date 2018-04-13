package com.umeng.analytics.c;

import com.umeng.analytics.f.b;
import com.umeng.analytics.f.c;
import com.umeng.analytics.f.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class a {
    private final int a = 10;
    private final int b = 20;
    private final String c;
    private List<b> d;
    private c e;

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
        if (this.e == null || this.e.i() <= 20) {
            return true;
        }
        return false;
    }

    private boolean g() {
        c cVar = this.e;
        String c = cVar == null ? null : cVar.c();
        int i = cVar == null ? 0 : cVar.i();
        String a = a(f());
        if (a == null || a.equals(c)) {
            return false;
        }
        if (cVar == null) {
            cVar = new c();
        }
        cVar.a(a);
        cVar.a(System.currentTimeMillis());
        cVar.a(i + 1);
        b bVar = new b();
        bVar.a(this.c);
        bVar.c(a);
        bVar.b(c);
        bVar.a(cVar.f());
        if (this.d == null) {
            this.d = new ArrayList(2);
        }
        this.d.add(bVar);
        if (this.d.size() > 10) {
            this.d.remove(0);
        }
        this.e = cVar;
        return true;
    }

    public c d() {
        return this.e;
    }

    public void a(c cVar) {
        this.e = cVar;
    }

    public List<b> e() {
        return this.d;
    }

    public void a(List<b> list) {
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

    public void a(d dVar) {
        this.e = (c) dVar.d().get(this.c);
        List<b> i = dVar.i();
        if (i != null && i.size() > 0) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            for (b bVar : i) {
                if (this.c.equals(bVar.a)) {
                    this.d.add(bVar);
                }
            }
        }
    }
}
