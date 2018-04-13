package cn.v6.sixrooms.surfaceanim;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneType;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;

public class SimpleAnimBitmap implements IAnimEntity {
    private Bitmap a;
    private Paint b;
    private Matrix c;
    private float d;
    private float e;
    private float[] f;
    private float[] g;
    private float h;

    public SimpleAnimBitmap(IAnimSceneType iAnimSceneType, int i) {
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = new float[2];
        this.g = new float[2];
        this.a = AnimSceneResManager.getInstance().getBitmap(iAnimSceneType, i);
        this.b = new Paint();
        this.c = new Matrix();
    }

    public SimpleAnimBitmap(IAnimSceneType iAnimSceneType, Bitmap bitmap) {
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = new float[2];
        this.g = new float[2];
        this.a = bitmap;
        this.b = new Paint();
        this.c = new Matrix();
    }

    public SimpleAnimBitmap(IAnimSceneType iAnimSceneType, int i, boolean z) {
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = new float[2];
        this.g = new float[2];
        this.a = AnimSceneResManager.getInstance().getBitmap(iAnimSceneType, i);
        this.b = new Paint();
        if (z) {
            this.c = new Matrix();
        }
    }

    public SimpleAnimBitmap(IAnimSceneType iAnimSceneType, Bitmap bitmap, boolean z) {
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = new float[2];
        this.g = new float[2];
        this.a = bitmap;
        this.b = new Paint();
        if (z) {
            this.c = new Matrix();
        }
    }

    public void setOffset(float f, float f2) {
        this.d = f;
        this.e = f2;
    }

    public void initMatrix() {
        if (this.c == null) {
            this.c = new Matrix();
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.a = bitmap;
    }

    public void setBitmap(IAnimSceneType iAnimSceneType, int i) {
        this.a = AnimSceneResManager.getInstance().getBitmap(iAnimSceneType, i);
    }

    public Bitmap getBitmap() {
        return this.a;
    }

    public int getBitmapWidth() {
        return this.a.getWidth();
    }

    public int getBitmapHeight() {
        return this.a.getHeight();
    }

    public void setPaint(Paint paint) {
        this.b = paint;
    }

    public void setMatrix(Matrix matrix) {
        this.c = matrix;
    }

    public Paint getPaint() {
        return this.b;
    }

    public Matrix getMatrix() {
        return this.c;
    }

    public void setMatrixTranslate(float f, float f2) {
        if (this.c != null) {
            this.f[0] = f;
            this.f[1] = f2;
            this.c.setTranslate(AnimSceneResManager.getInstance().getScalePx(this.d + f), AnimSceneResManager.getInstance().getScalePx(this.e + f2));
        }
    }

    public float[] getMatrixTranslate() {
        return this.f;
    }

    public void setMatrixScale(float f, float f2) {
        if (this.c != null) {
            this.g[0] = f;
            this.g[1] = f2;
            this.c.setScale(f, f2);
        }
    }

    public void setMatrixScale(float f, float f2, float f3, float f4) {
        if (this.c != null) {
            this.g[0] = f;
            this.g[1] = f2;
            this.c.setScale(f, f2, AnimSceneResManager.getInstance().getScalePx(this.d + f3), AnimSceneResManager.getInstance().getScalePx(this.e + f4));
        }
    }

    public float[] getMatrixScale() {
        return this.g;
    }

    public void setMatrixRotate(float f) {
        if (this.c != null) {
            this.h = f;
            this.c.setRotate(f);
        }
    }

    public void setMatrixRotate(float f, float f2, float f3) {
        if (this.c != null) {
            this.h = f;
            this.c.setRotate(f, AnimSceneResManager.getInstance().getScalePx(this.d + f2), AnimSceneResManager.getInstance().getScalePx(this.e + f3));
        }
    }

    public float getMatrixRotate() {
        return this.h;
    }

    public void postMatrixTranslate(float f, float f2) {
        if (this.c != null) {
            this.c.postTranslate(AnimSceneResManager.getInstance().getScalePx(this.d + f), AnimSceneResManager.getInstance().getScalePx(this.e + f2));
        }
    }

    public void postMatrixScale(float f, float f2, float f3, float f4) {
        if (this.c != null) {
            this.c.postScale(f, f2, AnimSceneResManager.getInstance().getScalePx(this.d + f3), AnimSceneResManager.getInstance().getScalePx(this.e + f4));
        }
    }

    public void postMatrixScale(float f, float f2) {
        if (this.c != null) {
            this.c.postScale(f, f2);
        }
    }

    public void postMatrixRotate(float f) {
        if (this.c != null) {
            this.c.postRotate(f);
        }
    }

    public void postMatrixRotate(float f, float f2, float f3) {
        if (this.c != null) {
            this.c.postRotate(f, AnimSceneResManager.getInstance().getScalePx(this.d + f2), AnimSceneResManager.getInstance().getScalePx(this.e + f3));
        }
    }

    public void postRotateByMyself(float f) {
        if (this.c != null) {
            this.c.postRotate(f, AnimSceneResManager.getInstance().getScalePx((this.f[0] + this.d) + ((float) (this.a.getWidth() / 2))), AnimSceneResManager.getInstance().getScalePx((this.f[1] + this.e) + ((float) (this.a.getHeight() / 2))));
        }
    }

    public void postScaleByMyself(float f, float f2) {
        if (this.c != null) {
            this.c.postScale(f, f2, AnimSceneResManager.getInstance().getScalePx((this.f[0] + this.d) + ((float) (this.a.getWidth() / 2))), AnimSceneResManager.getInstance().getScalePx((this.f[1] + this.e) + ((float) (this.a.getHeight() / 2))));
        }
    }

    public void pretMatrixRotate(float f, float f2, float f3) {
        if (this.c != null) {
            this.c.preRotate(f, AnimSceneResManager.getInstance().getScalePx(this.d + f2), AnimSceneResManager.getInstance().getScalePx(this.e + f3));
        }
    }

    public void preMatrixTranslate(float f, float f2) {
        if (this.c != null) {
            this.c.preTranslate(AnimSceneResManager.getInstance().getScalePx(this.d + f), AnimSceneResManager.getInstance().getScalePx(this.e + f2));
        }
    }

    public void preMatrixScale(float f, float f2, float f3, float f4) {
        if (this.c != null) {
            this.c.preScale(f, f2, AnimSceneResManager.getInstance().getScalePx(this.d + f3), AnimSceneResManager.getInstance().getScalePx(this.e + f4));
        }
    }

    public void preMatrixScale(float f, float f2) {
        if (this.c != null) {
            this.c.preScale(f, f2);
        }
    }

    public void preMatrixRotate(float f) {
        if (this.c != null) {
            this.c.preRotate(f);
        }
    }

    public void preMatrixRotate(float f, float f2, float f3) {
        if (this.c != null) {
            this.c.preRotate(f, AnimSceneResManager.getInstance().getScalePx(this.d + f2), AnimSceneResManager.getInstance().getScalePx(this.e + f3));
        }
    }

    public void draw(Canvas canvas) {
        if (this.c != null && this.a != null) {
            canvas.drawBitmap(this.a, this.c, this.b);
        }
    }
}
