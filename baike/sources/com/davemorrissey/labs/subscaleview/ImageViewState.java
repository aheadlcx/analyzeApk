package com.davemorrissey.labs.subscaleview;

import android.graphics.PointF;
import java.io.Serializable;

public class ImageViewState implements Serializable {
    private float a;
    private float b;
    private float c;
    private int d;

    public ImageViewState(float f, PointF pointF, int i) {
        this.a = f;
        this.b = pointF.x;
        this.c = pointF.y;
        this.d = i;
    }

    public float getScale() {
        return this.a;
    }

    public PointF getCenter() {
        return new PointF(this.b, this.c);
    }

    public int getOrientation() {
        return this.d;
    }
}
