package com.facebook.drawee.controller;

import com.facebook.datasource.a;
import com.facebook.datasource.b;

class a$1 extends a<T> {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ a c;

    a$1(a aVar, String str, boolean z) {
        this.c = aVar;
        this.a = str;
        this.b = z;
    }

    public void b(b<T> bVar) {
        boolean b = bVar.b();
        float g = bVar.g();
        Object d = bVar.d();
        if (d != null) {
            a.a(this.c, this.a, bVar, d, g, b, this.b);
        } else if (b) {
            a.a(this.c, this.a, bVar, new NullPointerException(), true);
        }
    }

    public void c(b<T> bVar) {
        a.a(this.c, this.a, bVar, bVar.f(), true);
    }

    public void a(b<T> bVar) {
        boolean b = bVar.b();
        a.a(this.c, this.a, bVar, bVar.g(), b);
    }
}
