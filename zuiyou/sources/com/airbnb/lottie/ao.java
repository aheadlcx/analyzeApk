package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import java.util.List;

class ao extends ag<PointF> {
    private final PointF c = new PointF();
    private final float[] d = new float[2];
    private an e;
    private PathMeasure f;

    public /* synthetic */ Object a(af afVar, float f) {
        return b(afVar, f);
    }

    ao(List<? extends af<PointF>> list) {
        super(list);
    }

    public PointF b(af<PointF> afVar, float f) {
        an anVar = (an) afVar;
        Path e = anVar.e();
        if (e == null) {
            return (PointF) afVar.a;
        }
        if (this.e != anVar) {
            this.f = new PathMeasure(e, false);
            this.e = anVar;
        }
        this.f.getPosTan(this.f.getLength() * f, this.d, null);
        this.c.set(this.d[0], this.d[1]);
        return this.c;
    }
}
