package com.bumptech.glide.g.a;

public class g<R> implements d<R> {
    private final a a;
    private c<R> b;

    g(a aVar) {
        this.a = aVar;
    }

    public c<R> a(boolean z, boolean z2) {
        if (z || !z2) {
            return e.b();
        }
        if (this.b == null) {
            this.b = new f(this.a);
        }
        return this.b;
    }
}
