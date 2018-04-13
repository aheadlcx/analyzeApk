package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeCenter extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeCenter();

    private ScalingUtils$ScaleTypeCenter() {
    }

    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        matrix.setTranslate((float) ((int) ((((float) rect.left) + (((float) (rect.width() - i)) * 0.5f)) + 0.5f)), (float) ((int) ((((float) rect.top) + (((float) (rect.height() - i2)) * 0.5f)) + 0.5f)));
    }

    public String toString() {
        return "center";
    }
}