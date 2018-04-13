package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class n$i extends n$a {
    public static final n$b i = new n$i();

    private n$i() {
    }

    public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float f5 = (float) rect.left;
        float f6 = (float) rect.top;
        matrix.setScale(f3, f4);
        matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f6 + 0.5f)));
    }

    public String toString() {
        return "fit_xy";
    }
}
