package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class n$j extends n$a {
    public static final n$b i = new n$j();

    private n$j() {
    }

    public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float max;
        float f5;
        if (f4 > f3) {
            max = ((float) rect.left) + Math.max(Math.min((((float) rect.width()) * 0.5f) - ((((float) i) * f4) * f), 0.0f), ((float) rect.width()) - (((float) i) * f4));
            f5 = (float) rect.top;
        } else {
            max = (float) rect.left;
            float f6 = (float) rect.top;
            f5 = Math.max(Math.min((((float) rect.height()) * 0.5f) - ((((float) i2) * f3) * f2), 0.0f), ((float) rect.height()) - (((float) i2) * f3)) + f6;
            f4 = f3;
        }
        matrix.setScale(f4, f4);
        matrix.postTranslate((float) ((int) (max + 0.5f)), (float) ((int) (f5 + 0.5f)));
    }

    public String toString() {
        return "focus_crop";
    }
}
