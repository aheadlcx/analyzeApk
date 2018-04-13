package com.budejie.www.widget;

import android.animation.TypeEvaluator;
import android.annotation.SuppressLint;
import android.graphics.PointF;

@SuppressLint({"NewApi"})
public class c implements TypeEvaluator<PointF> {
    private PointF a;
    private PointF b;

    public /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
        return a(f, (PointF) obj, (PointF) obj2);
    }

    public c(PointF pointF, PointF pointF2) {
        this.a = pointF;
        this.b = pointF2;
    }

    public PointF a(float f, PointF pointF, PointF pointF2) {
        float f2 = 1.0f - f;
        PointF pointF3 = new PointF();
        pointF3.x = (((((f2 * f2) * f2) * pointF.x) + ((((3.0f * f2) * f2) * f) * this.a.x)) + ((((3.0f * f2) * f) * f) * this.b.x)) + (((f * f) * f) * pointF2.x);
        pointF3.y = (((((f2 * 3.0f) * f) * f) * this.b.y) + ((((f2 * f2) * f2) * pointF.y) + ((((3.0f * f2) * f2) * f) * this.a.y))) + (((f * f) * f) * pointF2.y);
        return pointF3;
    }
}
