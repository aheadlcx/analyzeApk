package com.bumptech.glide;

import com.bumptech.glide.load.b.l;

public final class k$b<A, T> {
    final /* synthetic */ k a;
    private final l<A, T> b;
    private final Class<T> c;

    public final class a {
        final /* synthetic */ k$b a;
        private final A b;
        private final Class<A> c;
        private final boolean d = true;

        a(k$b k_b, A a) {
            this.a = k_b;
            this.b = a;
            this.c = k.a(a);
        }

        public <Z> f<A, T, Z> a(Class<Z> cls) {
            f<A, T, Z> fVar = (f) k.e(this.a.a).a(new f(k.a(this.a.a), k.b(this.a.a), this.c, this.a.b, this.a.c, cls, k.c(this.a.a), k.d(this.a.a), k.e(this.a.a)));
            if (this.d) {
                fVar.b(this.b);
            }
            return fVar;
        }
    }

    k$b(k kVar, l<A, T> lVar, Class<T> cls) {
        this.a = kVar;
        this.b = lVar;
        this.c = cls;
    }

    public a a(A a) {
        return new a(this, a);
    }
}
