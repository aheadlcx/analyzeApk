package com.soundcloud.android.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

abstract class ImageViewTouchBase extends ImageView {
    private final Matrix a;
    private final float[] b;
    private Runnable c;
    protected Matrix d;
    protected Matrix e;
    protected final o f;
    int g;
    int h;
    float i;
    protected Handler j;
    private Recycler k;

    public interface Recycler {
        void recycle(Bitmap bitmap);
    }

    public ImageViewTouchBase(Context context) {
        super(context);
        this.d = new Matrix();
        this.e = new Matrix();
        this.a = new Matrix();
        this.b = new float[9];
        this.f = new o(null, 0);
        this.g = -1;
        this.h = -1;
        this.j = new Handler();
        c();
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new Matrix();
        this.e = new Matrix();
        this.a = new Matrix();
        this.b = new float[9];
        this.f = new o(null, 0);
        this.g = -1;
        this.h = -1;
        this.j = new Handler();
        c();
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Matrix();
        this.e = new Matrix();
        this.a = new Matrix();
        this.b = new float[9];
        this.f = new o(null, 0);
        this.g = -1;
        this.h = -1;
        this.j = new Handler();
        c();
    }

    public void setRecycler(Recycler recycler) {
        this.k = recycler;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.g = i3 - i;
        this.h = i4 - i2;
        Runnable runnable = this.c;
        if (runnable != null) {
            this.c = null;
            runnable.run();
        }
        if (this.f.getBitmap() != null) {
            a(this.f, this.d, true);
            setImageMatrix(getImageViewMatrix());
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || !keyEvent.isTracking() || keyEvent.isCanceled() || getScale() <= 1.0f) {
            return super.onKeyUp(i, keyEvent);
        }
        a(1.0f);
        return true;
    }

    public void setImageBitmap(Bitmap bitmap) {
        a(bitmap, 0);
    }

    private void a(Bitmap bitmap, int i) {
        super.setImageBitmap(bitmap);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setDither(true);
        }
        Bitmap bitmap2 = this.f.getBitmap();
        this.f.setBitmap(bitmap);
        this.f.setRotation(i);
        if (bitmap2 != null && bitmap2 != bitmap && this.k != null) {
            this.k.recycle(bitmap2);
        }
    }

    public void clear() {
        setImageBitmapResetBase(null, true);
    }

    public void setImageBitmapResetBase(Bitmap bitmap, boolean z) {
        setImageRotateBitmapResetBase(new o(bitmap, 0), z);
    }

    public void setImageRotateBitmapResetBase(o oVar, boolean z) {
        if (getWidth() <= 0) {
            this.c = new l(this, oVar, z);
            return;
        }
        if (oVar.getBitmap() != null) {
            a(oVar, this.d, true);
            a(oVar.getBitmap(), oVar.getRotation());
        } else {
            this.d.reset();
            setImageBitmap(null);
        }
        if (z) {
            this.e.reset();
        }
        setImageMatrix(getImageViewMatrix());
        this.i = b();
    }

    protected void a() {
        Bitmap bitmap = this.f.getBitmap();
        if (bitmap != null) {
            Matrix imageViewMatrix = getImageViewMatrix();
            RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
            imageViewMatrix.mapRect(rectF);
            float height = rectF.height();
            float width = rectF.width();
            a(b(rectF, width, 0.0f), a(rectF, height, 0.0f));
            setImageMatrix(getImageViewMatrix());
        }
    }

    private float a(RectF rectF, float f, float f2) {
        int height = getHeight();
        if (f < ((float) height)) {
            return ((((float) height) - f) / 2.0f) - rectF.top;
        }
        if (rectF.top > 0.0f) {
            return -rectF.top;
        }
        if (rectF.bottom < ((float) height)) {
            return ((float) getHeight()) - rectF.bottom;
        }
        return f2;
    }

    private float b(RectF rectF, float f, float f2) {
        int width = getWidth();
        if (f < ((float) width)) {
            return ((((float) width) - f) / 2.0f) - rectF.left;
        }
        if (rectF.left > 0.0f) {
            return -rectF.left;
        }
        if (rectF.right < ((float) width)) {
            return ((float) width) - rectF.right;
        }
        return f2;
    }

    private void c() {
        setScaleType(ScaleType.MATRIX);
    }

    protected float a(Matrix matrix, int i) {
        matrix.getValues(this.b);
        return this.b[i];
    }

    protected float a(Matrix matrix) {
        return a(matrix, 0);
    }

    protected float getScale() {
        return a(this.e);
    }

    private void a(o oVar, Matrix matrix, boolean z) {
        float width = (float) getWidth();
        float height = (float) getHeight();
        float width2 = (float) oVar.getWidth();
        float height2 = (float) oVar.getHeight();
        matrix.reset();
        float min = Math.min(Math.min(width / width2, 3.0f), Math.min(height / height2, 3.0f));
        if (z) {
            matrix.postConcat(oVar.getRotateMatrix());
        }
        matrix.postScale(min, min);
        matrix.postTranslate((width - (width2 * min)) / 2.0f, (height - (height2 * min)) / 2.0f);
    }

    protected Matrix getImageViewMatrix() {
        this.a.set(this.d);
        this.a.postConcat(this.e);
        return this.a;
    }

    public Matrix getUnrotatedMatrix() {
        Matrix matrix = new Matrix();
        a(this.f, matrix, false);
        matrix.postConcat(this.e);
        return matrix;
    }

    protected float b() {
        if (this.f.getBitmap() == null) {
            return 1.0f;
        }
        return Math.max(((float) this.f.getWidth()) / ((float) this.g), ((float) this.f.getHeight()) / ((float) this.h)) * 4.0f;
    }

    protected void a(float f, float f2, float f3) {
        if (f > this.i) {
            f = this.i;
        }
        float scale = f / getScale();
        this.e.postScale(scale, scale, f2, f3);
        setImageMatrix(getImageViewMatrix());
        a();
    }

    protected void a(float f, float f2, float f3, float f4) {
        float scale = (f - getScale()) / f4;
        float scale2 = getScale();
        this.j.post(new m(this, f4, System.currentTimeMillis(), scale2, scale, f2, f3));
    }

    protected void a(float f) {
        a(f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
    }

    protected void a(float f, float f2) {
        this.e.postTranslate(f, f2);
    }

    protected void b(float f, float f2) {
        a(f, f2);
        setImageMatrix(getImageViewMatrix());
    }
}
