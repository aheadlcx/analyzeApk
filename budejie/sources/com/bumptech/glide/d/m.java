package com.bumptech.glide.d;

import com.bumptech.glide.g.c;
import com.bumptech.glide.i.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public class m {
    private final Set<c> a = Collections.newSetFromMap(new WeakHashMap());
    private final List<c> b = new ArrayList();
    private boolean c;

    public void a(c cVar) {
        this.a.add(cVar);
        if (this.c) {
            this.b.add(cVar);
        } else {
            cVar.b();
        }
    }

    public void b(c cVar) {
        this.a.remove(cVar);
        this.b.remove(cVar);
    }

    public void a() {
        this.c = true;
        for (c cVar : h.a(this.a)) {
            if (cVar.f()) {
                cVar.e();
                this.b.add(cVar);
            }
        }
    }

    public void b() {
        this.c = false;
        for (c cVar : h.a(this.a)) {
            if (!(cVar.g() || cVar.i() || cVar.f())) {
                cVar.b();
            }
        }
        this.b.clear();
    }

    public void c() {
        for (c d : h.a(this.a)) {
            d.d();
        }
        this.b.clear();
    }

    public void d() {
        for (c cVar : h.a(this.a)) {
            if (!(cVar.g() || cVar.i())) {
                cVar.e();
                if (this.c) {
                    this.b.add(cVar);
                } else {
                    cVar.b();
                }
            }
        }
    }
}
