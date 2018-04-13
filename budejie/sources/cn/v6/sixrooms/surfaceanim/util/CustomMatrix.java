package cn.v6.sixrooms.surfaceanim.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class CustomMatrix extends Matrix {
    private float a;
    private float b;

    public void setTranslate(float f, float f2) {
        this.a = f;
        this.b = f2;
        super.setTranslate(f, f2);
    }

    public void postScaleByMyself(float f, float f2, Bitmap bitmap) {
        postScale(f, f2, this.a + ((float) (bitmap.getWidth() / 2)), this.b + ((float) (bitmap.getHeight() / 2)));
    }

    public void postRotateByMyself(float f, Bitmap bitmap) {
        postRotate(f, this.a + ((float) (bitmap.getWidth() / 2)), this.b + ((float) (bitmap.getHeight() / 2)));
    }
}
