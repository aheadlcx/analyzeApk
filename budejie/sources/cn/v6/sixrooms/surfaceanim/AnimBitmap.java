package cn.v6.sixrooms.surfaceanim;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.protocol.PointF;

public class AnimBitmap implements IAnimEntity {
    private Bitmap a;
    private Paint b;
    private AnimMatrix c;
    private int d;
    private int e;
    private int f = 255;
    private float g = 1.0f;
    private float h = 1.0f;
    private float i;
    private float j;
    private PointF k;
    private PointF l;
    private String m;

    public static class AnimMatrix {
        private Matrix a;

        public AnimMatrix(Matrix matrix) {
            this.a = matrix;
        }

        public Matrix getMatrix() {
            return this.a;
        }

        public AnimMatrix setTranslate(float f, float f2) {
            this.a.setTranslate(f, f2);
            return this;
        }

        public AnimMatrix setScale(float f, float f2, float f3, float f4) {
            this.a.setScale(f, f2, f3, f4);
            return this;
        }

        public AnimMatrix setScale(float f, float f2) {
            this.a.setScale(f, f2);
            return this;
        }

        public AnimMatrix setRotate(float f, float f2, float f3) {
            this.a.setRotate(f, f2, f3);
            return this;
        }

        public AnimMatrix setRotate(float f) {
            this.a.setRotate(f);
            return this;
        }

        public AnimMatrix setSinCos(float f, float f2, float f3, float f4) {
            this.a.setSinCos(f, f2, f3, f4);
            return this;
        }

        public AnimMatrix setSinCos(float f, float f2) {
            this.a.setSinCos(f, f2);
            return this;
        }

        public AnimMatrix setSkew(float f, float f2, float f3, float f4) {
            this.a.setSkew(f, f2, f3, f4);
            return this;
        }

        public AnimMatrix setSkew(float f, float f2) {
            this.a.setSkew(f, f2);
            return this;
        }

        public AnimMatrix preTranslate(float f, float f2) {
            this.a.preTranslate(f, f2);
            return this;
        }

        public AnimMatrix preScale(float f, float f2, float f3, float f4) {
            this.a.preScale(f, f2, f3, f4);
            return this;
        }

        public AnimMatrix preScale(float f, float f2) {
            this.a.preScale(f, f2);
            return this;
        }

        public AnimMatrix preRotate(float f, float f2, float f3) {
            this.a.preRotate(f, f2, f3);
            return this;
        }

        public AnimMatrix preRotate(float f) {
            this.a.preRotate(f);
            return this;
        }

        public AnimMatrix preSkew(float f, float f2, float f3, float f4) {
            this.a.preSkew(f, f2, f3, f4);
            return this;
        }

        public AnimMatrix preSkew(float f, float f2) {
            this.a.preSkew(f, f2);
            return this;
        }

        public AnimMatrix preConcat(Matrix matrix) {
            this.a.preConcat(matrix);
            return this;
        }

        public AnimMatrix postTranslate(float f, float f2) {
            this.a.postTranslate(f, f2);
            return this;
        }

        public AnimMatrix postScale(float f, float f2, float f3, float f4) {
            this.a.postScale(f, f2, f3, f4);
            return this;
        }

        public AnimMatrix postScale(float f, float f2) {
            this.a.postScale(f, f2);
            return this;
        }

        public AnimMatrix postRotate(float f, float f2, float f3) {
            this.a.postRotate(f, f2, f3);
            return this;
        }

        public AnimMatrix postRotate(float f) {
            this.a.postRotate(f);
            return this;
        }

        public AnimMatrix postSkew(float f, float f2, float f3, float f4) {
            this.a.postSkew(f, f2, f3, f4);
            return this;
        }

        public AnimMatrix postSkew(float f, float f2) {
            this.a.postSkew(f, f2);
            return this;
        }
    }

    public AnimBitmap(Bitmap bitmap) {
        this.a = bitmap;
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.c = new AnimMatrix(new Matrix());
    }

    public AnimBitmap(Bitmap bitmap, Paint paint) {
        this.a = bitmap;
        this.b = paint;
        this.c = new AnimMatrix(new Matrix());
    }

    public AnimBitmap(Bitmap bitmap, Paint paint, AnimMatrix animMatrix) {
        this.a = bitmap;
        this.b = paint;
        this.c = animMatrix;
    }

    public AnimBitmap setBitmap(Bitmap bitmap) {
        this.a = bitmap;
        return this;
    }

    public AnimBitmap setSkew(float f) {
        this.j = f;
        return this;
    }

    public String getText() {
        return this.m;
    }

    public void setText(String str) {
        this.m = str;
    }

    public Bitmap getBitmap() {
        return this.a;
    }

    public float getSkew() {
        return this.j;
    }

    public AnimBitmap setPaint(Paint paint) {
        this.b = paint;
        return this;
    }

    public AnimBitmap setMatrix(Matrix matrix) {
        this.c = new AnimMatrix(matrix);
        return this;
    }

    public AnimBitmap setTranslate(int i, int i2) {
        this.d = i;
        this.e = i2;
        return this;
    }

    public AnimBitmap setTranslateX(int i) {
        this.d = i;
        return this;
    }

    public AnimBitmap setTranslateY(int i) {
        this.e = i;
        return this;
    }

    public AnimBitmap setAlpha(int i) {
        this.f = i;
        return this;
    }

    public int getWidth() {
        return this.a.getWidth();
    }

    public int getHeight() {
        return this.a.getHeight();
    }

    public AnimBitmap setRotate(float f) {
        this.i = f;
        return this;
    }

    public Paint getPaint() {
        return this.b;
    }

    public AnimMatrix getMatrix() {
        return this.c;
    }

    public int getTranslateX() {
        return this.d;
    }

    public int getTranslateY() {
        return this.e;
    }

    public int getAlpha() {
        return this.f;
    }

    public float getScaleX() {
        return this.g;
    }

    public float getScaleY() {
        return this.h;
    }

    public AnimBitmap setScale(float f) {
        this.g = f;
        this.h = f;
        return this;
    }

    public AnimBitmap setScaleX(float f) {
        this.g = f;
        return this;
    }

    public AnimBitmap setScaleY(float f) {
        this.h = f;
        return this;
    }

    public void setScaleAxisPoint(PointF pointF) {
        this.k = pointF;
    }

    public PointF getScaleAxisPoint() {
        return this.k;
    }

    public void setRotateAxisPoint(PointF pointF) {
        this.l = pointF;
    }

    public PointF getRotateAxisPoint() {
        return this.l;
    }

    public float getRotate() {
        return this.i;
    }

    public AnimBitmap animScaleWithCentre() {
        this.c.setTranslate((float) this.d, (float) this.e).preScale(this.g, this.h, (float) (this.d + (this.a.getWidth() / 2)), (float) (this.e + (this.a.getHeight() / 2)));
        return this;
    }

    public AnimBitmap animRotateWithCentre() {
        this.c.setTranslate((float) this.d, (float) this.e).postRotate(this.i, (float) (this.d + (this.a.getWidth() / 2)), (float) (this.e + (this.a.getHeight() / 2)));
        return this;
    }

    public AnimBitmap animAlpha() {
        this.b.setAlpha(this.f);
        return this;
    }

    public AnimBitmap animTranslate() {
        this.c.setTranslate((float) this.d, (float) this.e);
        return this;
    }

    public AnimBitmap animPostScale() {
        if (this.a != null) {
            if (this.k == null) {
                this.c.postScale(this.g, this.h, (float) (this.d + (this.a.getWidth() / 2)), (float) (this.e + (this.a.getHeight() / 2)));
            } else {
                this.c.postScale(this.g, this.h, ((float) this.d) + (((float) this.a.getWidth()) * this.k.getX()), ((float) this.e) + (((float) this.a.getHeight()) * this.k.getY()));
            }
        }
        return this;
    }

    public AnimBitmap animPostRotate() {
        if (this.a != null) {
            if (this.l == null) {
                this.c.postRotate(this.i, (float) (this.d + (this.a.getWidth() / 2)), (float) (this.e + (this.a.getHeight() / 2)));
            } else {
                this.c.postRotate(this.i, ((float) this.d) + (((float) this.a.getWidth()) * this.l.getX()), ((float) this.e) + (((float) this.a.getHeight()) * this.l.getY()));
            }
        }
        return this;
    }

    public AnimBitmap animPostRotate(int i, int i2) {
        this.c.postRotate(this.i, (float) i, (float) i2);
        return this;
    }

    public void recycle() {
        if (this.a != null && !this.a.isRecycled()) {
            this.a.recycle();
            this.a = null;
        }
    }

    public void draw(Canvas canvas) {
        if (this.a != null) {
            canvas.drawBitmap(this.a, this.c.getMatrix(), this.b);
        }
        if (this.m != null) {
            canvas.drawText(this.m, (float) this.d, (float) this.e, this.b);
        }
    }
}
