package com.airbnb.lottie;

import android.graphics.PointF;
import java.util.List;

class aq extends ag<PointF> {
    private final PointF c = new PointF();

    public /* synthetic */ Object a(af afVar, float f) {
        return b(afVar, f);
    }

    aq(List<af<PointF>> list) {
        super(list);
    }

    public PointF b(af<PointF> afVar, float f) {
        PointF pointF = (PointF) afVar.a;
        PointF pointF2 = (PointF) afVar.b;
        this.c.set(pointF.x + ((pointF2.x - pointF.x) * f), ((pointF2.y - pointF.y) * f) + pointF.y);
        return this.c;
    }
}
