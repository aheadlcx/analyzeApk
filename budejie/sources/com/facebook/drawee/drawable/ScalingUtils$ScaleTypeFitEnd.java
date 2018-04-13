package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeFitEnd extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeFitEnd();

    private ScalingUtils$ScaleTypeFitEnd() {
    }

    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float min = Math.min(f3, f4);
        float width = ((float) rect.left) + (((float) rect.width()) - (((float) i) * min));
        float height = ((float) rect.top) + (((float) rect.height()) - (((float) i2) * min));
        matrix.setScale(min, min);
        matrix.postTranslate((float) ((int) (width + 0.5f)), (float) ((int) (height + 0.5f)));
    }

    public String toString() {
        return "fit_end";
    }
}
