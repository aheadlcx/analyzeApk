package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeCenterCrop extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeCenterCrop();

    private ScalingUtils$ScaleTypeCenterCrop() {
    }

    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float width;
        float f5;
        if (f4 > f3) {
            width = ((((float) rect.width()) - (((float) i) * f4)) * 0.5f) + ((float) rect.left);
            f5 = (float) rect.top;
        } else {
            width = (float) rect.left;
            f5 = ((float) rect.top) + ((((float) rect.height()) - (((float) i2) * f3)) * 0.5f);
            f4 = f3;
        }
        matrix.setScale(f4, f4);
        matrix.postTranslate((float) ((int) (width + 0.5f)), (float) ((int) (f5 + 0.5f)));
    }

    public String toString() {
        return "center_crop";
    }
}
