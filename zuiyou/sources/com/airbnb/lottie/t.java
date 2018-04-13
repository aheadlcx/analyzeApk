package com.airbnb.lottie;

import android.graphics.PointF;

class t {
    private final PointF a;
    private final PointF b;
    private final PointF c;

    t() {
        this.a = new PointF();
        this.b = new PointF();
        this.c = new PointF();
    }

    t(PointF pointF, PointF pointF2, PointF pointF3) {
        this.a = pointF;
        this.b = pointF2;
        this.c = pointF3;
    }

    void a(float f, float f2) {
        this.a.set(f, f2);
    }

    PointF a() {
        return this.a;
    }

    void b(float f, float f2) {
        this.b.set(f, f2);
    }

    PointF b() {
        return this.b;
    }

    void c(float f, float f2) {
        this.c.set(f, f2);
    }

    PointF c() {
        return this.c;
    }
}
