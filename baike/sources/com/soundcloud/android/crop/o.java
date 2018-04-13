package com.soundcloud.android.crop;

import android.graphics.Bitmap;
import android.graphics.Matrix;

class o {
    private Bitmap a;
    private int b;

    public o(Bitmap bitmap, int i) {
        this.a = bitmap;
        this.b = i % 360;
    }

    public void setRotation(int i) {
        this.b = i;
    }

    public int getRotation() {
        return this.b;
    }

    public Bitmap getBitmap() {
        return this.a;
    }

    public void setBitmap(Bitmap bitmap) {
        this.a = bitmap;
    }

    public Matrix getRotateMatrix() {
        Matrix matrix = new Matrix();
        if (!(this.a == null || this.b == 0)) {
            matrix.preTranslate((float) (-(this.a.getWidth() / 2)), (float) (-(this.a.getHeight() / 2)));
            matrix.postRotate((float) this.b);
            matrix.postTranslate((float) (getWidth() / 2), (float) (getHeight() / 2));
        }
        return matrix;
    }

    public boolean isOrientationChanged() {
        return (this.b / 90) % 2 != 0;
    }

    public int getHeight() {
        if (this.a == null) {
            return 0;
        }
        if (isOrientationChanged()) {
            return this.a.getWidth();
        }
        return this.a.getHeight();
    }

    public int getWidth() {
        if (this.a == null) {
            return 0;
        }
        if (isOrientationChanged()) {
            return this.a.getHeight();
        }
        return this.a.getWidth();
    }

    public void recycle() {
        if (this.a != null) {
            this.a.recycle();
            this.a = null;
        }
    }
}
