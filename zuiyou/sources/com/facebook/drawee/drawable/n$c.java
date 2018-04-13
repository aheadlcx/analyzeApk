package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class n$c extends n$a {
    public static final n$b i = new n$c();

    private n$c() {
    }

    public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        matrix.setTranslate((float) ((int) ((((float) rect.left) + (((float) (rect.width() - i)) * 0.5f)) + 0.5f)), (float) ((int) ((((float) rect.top) + (((float) (rect.height() - i2)) * 0.5f)) + 0.5f)));
    }

    public String toString() {
        return "center";
    }
}
