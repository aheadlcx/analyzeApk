package com.bumptech.glide.load.b;

import com.bumptech.glide.i.e;
import com.bumptech.glide.i.h;
import java.util.Queue;

public class k<A, B> {
    private final e<a<A>, B> a;

    static final class a<A> {
        private static final Queue<a<?>> a = h.a(0);
        private int b;
        private int c;
        private A d;

        static <A> a<A> a(A a, int i, int i2) {
            a<A> aVar = (a) a.poll();
            if (aVar == null) {
                aVar = new a();
            }
            aVar.b(a, i, i2);
            return aVar;
        }

        private a() {
        }

        private void b(A a, int i, int i2) {
            this.d = a;
            this.c = i;
            this.b = i2;
        }

        public void a() {
            a.offer(this);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.c == aVar.c && this.b == aVar.b && this.d.equals(aVar.d)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.b * 31) + this.c) * 31) + this.d.hashCode();
        }
    }

    public k() {
        this(250);
    }

    public k(int i) {
        this.a = new e<a<A>, B>(this, i) {
            final /* synthetic */ k a;

            protected void a(a<A> aVar, B b) {
                aVar.a();
            }
        };
    }

    public B a(A a, int i, int i2) {
        Object a2 = a.a(a, i, i2);
        B b = this.a.b(a2);
        a2.a();
        return b;
    }

    public void a(A a, int i, int i2, B b) {
        this.a.b(a.a(a, i, i2), b);
    }
}
