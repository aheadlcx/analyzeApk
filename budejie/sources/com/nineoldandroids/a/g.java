package com.nineoldandroids.a;

import android.view.View;
import com.nineoldandroids.b.a.a;
import com.nineoldandroids.util.c;
import java.util.HashMap;
import java.util.Map;

public final class g extends k {
    private static final Map<String, c> h = new HashMap();
    private Object i;
    private String j;
    private c k;

    public /* synthetic */ a b() {
        return d();
    }

    public /* synthetic */ k b(long j) {
        return a(j);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public /* synthetic */ k e() {
        return d();
    }

    static {
        h.put("alpha", h.a);
        h.put("pivotX", h.b);
        h.put("pivotY", h.c);
        h.put("translationX", h.d);
        h.put("translationY", h.e);
        h.put("rotation", h.f);
        h.put("rotationX", h.g);
        h.put("rotationY", h.h);
        h.put("scaleX", h.i);
        h.put("scaleY", h.j);
        h.put("scrollX", h.k);
        h.put("scrollY", h.l);
        h.put("x", h.m);
        h.put("y", h.n);
    }

    public void a(String str) {
        if (this.f != null) {
            i iVar = this.f[0];
            String c = iVar.c();
            iVar.a(str);
            this.g.remove(c);
            this.g.put(str, iVar);
        }
        this.j = str;
        this.e = false;
    }

    public void a(c cVar) {
        if (this.f != null) {
            i iVar = this.f[0];
            String c = iVar.c();
            iVar.a(cVar);
            this.g.remove(c);
            this.g.put(this.j, iVar);
        }
        if (this.k != null) {
            this.j = cVar.a();
        }
        this.k = cVar;
        this.e = false;
    }

    private g(Object obj, String str) {
        this.i = obj;
        a(str);
    }

    public static g a(Object obj, String str, float... fArr) {
        g gVar = new g(obj, str);
        gVar.a(fArr);
        return gVar;
    }

    public void a(float... fArr) {
        if (this.f != null && this.f.length != 0) {
            super.a(fArr);
        } else if (this.k != null) {
            a(i.a(this.k, fArr));
        } else {
            a(i.a(this.j, fArr));
        }
    }

    public void a() {
        super.a();
    }

    void c() {
        if (!this.e) {
            if (this.k == null && a.a && (this.i instanceof View) && h.containsKey(this.j)) {
                a((c) h.get(this.j));
            }
            for (i a : this.f) {
                a.a(this.i);
            }
            super.c();
        }
    }

    public g a(long j) {
        super.b(j);
        return this;
    }

    void a(float f) {
        super.a(f);
        for (i b : this.f) {
            b.b(this.i);
        }
    }

    public g d() {
        return (g) super.e();
    }

    public String toString() {
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.i;
        if (this.f != null) {
            for (i iVar : this.f) {
                str = str + "\n    " + iVar.toString();
            }
        }
        return str;
    }
}
