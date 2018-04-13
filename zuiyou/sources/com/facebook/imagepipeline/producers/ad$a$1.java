package com.facebook.imagepipeline.producers;

import android.util.Pair;
import java.util.List;

class ad$a$1 extends e {
    final /* synthetic */ Pair a;
    final /* synthetic */ ad$a b;

    ad$a$1(ad$a ad_a, Pair pair) {
        this.b = ad_a;
        this.a = pair;
    }

    public void a() {
        List list;
        List list2;
        d dVar;
        List list3 = null;
        synchronized (this.b) {
            boolean remove = ad$a.b(this.b).remove(this.a);
            if (!remove) {
                list = null;
                list2 = null;
                dVar = null;
            } else if (ad$a.b(this.b).isEmpty()) {
                list2 = null;
                dVar = ad$a.c(this.b);
                list = null;
            } else {
                List d = ad$a.d(this.b);
                list2 = ad$a.e(this.b);
                list = list2;
                list2 = d;
                dVar = null;
                list3 = ad$a.f(this.b);
            }
        }
        d.b(list2);
        d.d(list);
        d.c(list3);
        if (dVar != null) {
            dVar.i();
        }
        if (remove) {
            ((j) this.a.first).b();
        }
    }

    public void b() {
        d.b(ad$a.d(this.b));
    }

    public void c() {
        d.c(ad$a.f(this.b));
    }

    public void d() {
        d.d(ad$a.e(this.b));
    }
}
