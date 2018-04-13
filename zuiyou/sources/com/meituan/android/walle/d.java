package com.meituan.android.walle;

final class d<A, B> {
    private final A a;
    private final B b;

    private d(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public static <A, B> d<A, B> a(A a, B b) {
        return new d(a, b);
    }

    public A a() {
        return this.a;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.a == null ? 0 : this.a.hashCode()) + 31) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.a == null) {
            if (dVar.a != null) {
                return false;
            }
        } else if (!this.a.equals(dVar.a)) {
            return false;
        }
        if (this.b == null) {
            if (dVar.b != null) {
                return false;
            }
            return true;
        } else if (this.b.equals(dVar.b)) {
            return true;
        } else {
            return false;
        }
    }
}
