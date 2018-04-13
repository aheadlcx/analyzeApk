package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class n$f extends n$a {
    public static final n$b i = new n$f();

    private n$f() {
    }

    public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float min = Math.min(f3, f4);
        float width = ((float) rect.left) + ((((float) rect.width()) - (((float) i) * min)) * 0.5f);
        float height = ((float) rect.top) + ((((float) rect.height()) - (((float) i2) * min)) * 0.5f);
        matrix.setScale(min, min);
        matrix.postTranslate((float) ((int) (width + 0.5f)), (float) ((int) (height + 0.5f)));
    }

    public String toString() {
        return "fit_center";
    }
}
