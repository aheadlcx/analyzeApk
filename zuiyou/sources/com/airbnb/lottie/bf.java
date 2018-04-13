package com.airbnb.lottie;

import android.graphics.PointF;
import java.util.Collections;

class bf extends ag<PointF> {
    private final PointF c = new PointF();
    private final ag<Float> d;
    private final ag<Float> e;

    /* synthetic */ Object a(af afVar, float f) {
        return b(afVar, f);
    }

    public /* synthetic */ Object b() {
        return c();
    }

    bf(ag<Float> agVar, ag<Float> agVar2) {
        super(Collections.emptyList());
        this.d = agVar;
        this.e = agVar2;
    }

    void a(float f) {
        this.d.a(f);
        this.e.a(f);
        this.c.set(((Float) this.d.b()).floatValue(), ((Float) this.e.b()).floatValue());
        for (int i = 0; i < this.a.size(); i++) {
            ((a) this.a.get(i)).a(this.c);
        }
    }

    public PointF c() {
        return b(null, 0.0f);
    }

    PointF b(af<PointF> afVar, float f) {
        return this.c;
    }
}
