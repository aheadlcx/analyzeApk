package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.FloatRange;

class al {
    static PointF a(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    static void a(ax axVar, Path path) {
        path.reset();
        PointF a = axVar.a();
        path.moveTo(a.x, a.y);
        for (int i = 0; i < axVar.c().size(); i++) {
            t tVar = (t) axVar.c().get(i);
            path.cubicTo(tVar.a().x, tVar.a().y, tVar.b().x, tVar.b().y, tVar.c().x, tVar.c().y);
        }
        if (axVar.b()) {
            path.close();
        }
    }

    static float a(float f, float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3) {
        return ((f2 - f) * f3) + f;
    }

    static int a(int i, int i2, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (int) (((float) i) + (((float) (i2 - i)) * f));
    }
}
