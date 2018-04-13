package com.airbnb.lottie;

import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

abstract class n<K, A> {
    final List<a<A>> a = new ArrayList();
    final List<? extends af<K>> b;
    private boolean c = false;
    private float d = 0.0f;
    @Nullable
    private af<K> e;

    interface a<A> {
        void a(A a);
    }

    abstract A a(af<K> afVar, float f);

    n(List<? extends af<K>> list) {
        this.b = list;
    }

    void a() {
        this.c = true;
    }

    void a(a<A> aVar) {
        this.a.add(aVar);
    }

    void b(a<A> aVar) {
        this.a.remove(aVar);
    }

    void a(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (f < e()) {
            f = 0.0f;
        } else if (f > f()) {
            f = 1.0f;
        }
        if (f != this.d) {
            this.d = f;
            Object b = b();
            for (int i = 0; i < this.a.size(); i++) {
                ((a) this.a.get(i)).a(b);
            }
        }
    }

    private af<K> c() {
        int i = 0;
        if (this.b.isEmpty()) {
            throw new IllegalStateException("There are no keyframes");
        } else if (this.e != null && this.e.a(this.d)) {
            return this.e;
        } else {
            af<K> afVar = (af) this.b.get(0);
            if (this.d < afVar.a()) {
                this.e = afVar;
                return afVar;
            }
            while (!afVar.a(this.d) && i < this.b.size()) {
                afVar = (af) this.b.get(i);
                i++;
            }
            this.e = afVar;
            return afVar;
        }
    }

    private float d() {
        if (this.c) {
            return 0.0f;
        }
        af c = c();
        if (c.c()) {
            return 0.0f;
        }
        return c.c.getInterpolation((this.d - c.a()) / (c.b() - c.a()));
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    private float e() {
        return this.b.isEmpty() ? 0.0f : ((af) this.b.get(0)).a();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    private float f() {
        return this.b.isEmpty() ? 1.0f : ((af) this.b.get(this.b.size() - 1)).b();
    }

    public A b() {
        return a(c(), d());
    }
}
