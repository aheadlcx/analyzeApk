package it.sephiroth.android.library.imagezoom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public abstract class ImageViewTouchBase extends ImageView {
    private float a;
    private float b;
    private boolean c;
    private boolean d;
    private int e;
    private int f;
    private PointF g;
    private boolean h;
    private boolean i;
    private a j;
    protected it.sephiroth.android.library.a.b k;
    protected Matrix l;
    protected Matrix m;
    protected Matrix n;
    protected Handler o;
    protected Runnable p;
    protected boolean q;
    protected final Matrix r;
    protected final float[] s;
    protected DisplayType t;
    protected final int u;
    protected RectF v;
    protected RectF w;
    protected RectF x;
    private b y;

    public enum DisplayType {
        NONE,
        FIT_TO_SCREEN,
        FIT_IF_BIGGER
    }

    public interface a {
        void a(Drawable drawable);
    }

    public interface b {
        void a(boolean z, int i, int i2, int i3, int i4);
    }

    public ImageViewTouchBase(Context context) {
        this(context, null);
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new it.sephiroth.android.library.a.a();
        this.l = new Matrix();
        this.m = new Matrix();
        this.o = new Handler();
        this.p = null;
        this.q = false;
        this.a = -1.0f;
        this.b = -1.0f;
        this.r = new Matrix();
        this.s = new float[9];
        this.e = -1;
        this.f = -1;
        this.g = new PointF();
        this.t = DisplayType.NONE;
        this.u = 200;
        this.v = new RectF();
        this.w = new RectF();
        this.x = new RectF();
        a(context, attributeSet, i);
    }

    public void setOnDrawableChangedListener(a aVar) {
        this.j = aVar;
    }

    public void setOnLayoutChangeListener(b bVar) {
        this.y = bVar;
    }

    protected void a(Context context, AttributeSet attributeSet, int i) {
        setScaleType(ScaleType.MATRIX);
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(scaleType);
        } else {
            Log.w("ImageViewTouchBase", "Unsupported scaletype. Only MATRIX can be used");
        }
    }

    public void setDisplayType(DisplayType displayType) {
        if (displayType != this.t) {
            this.q = false;
            this.t = displayType;
            this.h = true;
            requestLayout();
        }
    }

    public DisplayType getDisplayType() {
        return this.t;
    }

    protected void setMinScale(float f) {
        this.b = f;
    }

    protected void setMaxScale(float f) {
        this.a = f;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        int i6 = 0;
        if (z) {
            i6 = this.e;
            int i7 = this.f;
            this.e = i3 - i;
            this.f = i4 - i2;
            i5 = this.e - i6;
            i6 = this.f - i7;
            this.g.x = ((float) this.e) / 2.0f;
            this.g.y = ((float) this.f) / 2.0f;
        }
        Runnable runnable = this.p;
        if (runnable != null) {
            this.p = null;
            runnable.run();
        }
        Drawable drawable = getDrawable();
        if (drawable == null) {
            if (this.i) {
                a(drawable);
            }
            if (z || this.i || this.h) {
                b(i, i2, i3, i4);
            }
            if (this.i) {
                this.i = false;
            }
            if (this.h) {
                this.h = false;
            }
        } else if (z || this.h || this.i) {
            float scale;
            a(this.t);
            float c = c(this.l);
            float scale2 = getScale();
            float min = Math.min(1.0f, 1.0f / c);
            a(drawable, this.l);
            float c2 = c(this.l);
            if (this.i || this.h) {
                if (this.n != null) {
                    this.m.set(this.n);
                    this.n = null;
                    scale = getScale();
                } else {
                    this.m.reset();
                    scale = a(this.t);
                }
                setImageMatrix(getImageViewMatrix());
                if (scale != getScale()) {
                    b(scale);
                }
            } else if (z) {
                if (!this.d) {
                    this.b = -1.0f;
                }
                if (!this.c) {
                    this.a = -1.0f;
                }
                setImageMatrix(getImageViewMatrix());
                b((float) (-i5), (float) (-i6));
                if (this.q) {
                    if (((double) Math.abs(scale2 - min)) > 0.001d) {
                        scale = (c / c2) * scale2;
                    } else {
                        scale = 1.0f;
                    }
                    b(scale);
                } else {
                    scale = a(this.t);
                    b(scale);
                }
            } else {
                scale = 1.0f;
            }
            this.q = false;
            if (scale > getMaxScale() || scale < getMinScale()) {
                b(scale);
            }
            a(true, true);
            if (this.i) {
                a(drawable);
            }
            if (z || this.i || this.h) {
                b(i, i2, i3, i4);
            }
            if (this.h) {
                this.h = false;
            }
            if (this.i) {
                this.i = false;
            }
        }
    }

    protected float a(DisplayType displayType) {
        if (displayType == DisplayType.FIT_TO_SCREEN) {
            return 1.0f;
        }
        if (displayType == DisplayType.FIT_IF_BIGGER) {
            return Math.min(1.0f, 1.0f / c(this.l));
        }
        return 1.0f / c(this.l);
    }

    public void setImageResource(int i) {
        setImageDrawable(getContext().getResources().getDrawable(i));
    }

    public void setImageBitmap(Bitmap bitmap) {
        a(bitmap, null, -1.0f, -1.0f);
    }

    public void a(Bitmap bitmap, Matrix matrix, float f, float f2) {
        if (bitmap != null) {
            b(new it.sephiroth.android.library.imagezoom.a.a(bitmap), matrix, f, f2);
        } else {
            b(null, matrix, f, f2);
        }
    }

    public void setImageDrawable(Drawable drawable) {
        b(drawable, null, -1.0f, -1.0f);
    }

    public void b(Drawable drawable, Matrix matrix, float f, float f2) {
        if (getWidth() <= 0) {
            final Drawable drawable2 = drawable;
            final Matrix matrix2 = matrix;
            final float f3 = f;
            final float f4 = f2;
            this.p = new Runnable(this) {
                final /* synthetic */ ImageViewTouchBase e;

                public void run() {
                    this.e.b(drawable2, matrix2, f3, f4);
                }
            };
            return;
        }
        a(drawable, matrix, f, f2);
    }

    protected void a(Drawable drawable, Matrix matrix, float f, float f2) {
        if (drawable != null) {
            super.setImageDrawable(drawable);
        } else {
            this.l.reset();
            super.setImageDrawable(null);
        }
        if (f == -1.0f || f2 == -1.0f) {
            this.b = -1.0f;
            this.a = -1.0f;
            this.d = false;
            this.c = false;
        } else {
            float min = Math.min(f, f2);
            float max = Math.max(min, f2);
            this.b = min;
            this.a = max;
            this.d = true;
            this.c = true;
            if (this.t == DisplayType.FIT_TO_SCREEN || this.t == DisplayType.FIT_IF_BIGGER) {
                if (this.b >= 1.0f) {
                    this.d = false;
                    this.b = -1.0f;
                }
                if (this.a <= 1.0f) {
                    this.c = true;
                    this.a = -1.0f;
                }
            }
        }
        if (matrix != null) {
            this.n = new Matrix(matrix);
        }
        this.i = true;
        requestLayout();
    }

    protected void a(Drawable drawable) {
        b(drawable);
    }

    protected void a(int i, int i2, int i3, int i4) {
        if (this.y != null) {
            this.y.a(true, i, i2, i3, i4);
        }
    }

    protected void b(Drawable drawable) {
        if (this.j != null) {
            this.j.a(drawable);
        }
    }

    protected void b(int i, int i2, int i3, int i4) {
        a(i, i2, i3, i4);
    }

    protected float a() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return 1.0f;
        }
        return Math.max(((float) drawable.getIntrinsicWidth()) / ((float) this.e), ((float) drawable.getIntrinsicHeight()) / ((float) this.f)) * 8.0f;
    }

    protected float b() {
        if (getDrawable() == null) {
            return 1.0f;
        }
        return Math.min(1.0f, 1.0f / c(this.l));
    }

    public float getMaxScale() {
        if (this.a == -1.0f) {
            this.a = a();
        }
        return this.a;
    }

    public float getMinScale() {
        if (this.b == -1.0f) {
            this.b = b();
        }
        return this.b;
    }

    public Matrix getImageViewMatrix() {
        return a(this.m);
    }

    public Matrix a(Matrix matrix) {
        this.r.set(this.l);
        this.r.postConcat(matrix);
        return this.r;
    }

    public void setImageMatrix(Matrix matrix) {
        Matrix imageMatrix = getImageMatrix();
        Object obj = null;
        if ((matrix == null && !imageMatrix.isIdentity()) || !(matrix == null || imageMatrix.equals(matrix))) {
            obj = 1;
        }
        super.setImageMatrix(matrix);
        if (obj != null) {
            c();
        }
    }

    protected void c() {
    }

    public Matrix getDisplayMatrix() {
        return new Matrix(this.m);
    }

    protected void a(Drawable drawable, Matrix matrix) {
        float f = (float) this.e;
        float f2 = (float) this.f;
        float intrinsicWidth = (float) drawable.getIntrinsicWidth();
        float intrinsicHeight = (float) drawable.getIntrinsicHeight();
        matrix.reset();
        if (intrinsicWidth > f || intrinsicHeight > f2) {
            float min = Math.min(f / intrinsicWidth, f2 / intrinsicHeight);
            matrix.postScale(min, min);
            matrix.postTranslate((f - (intrinsicWidth * min)) / 2.0f, (f2 - (intrinsicHeight * min)) / 2.0f);
            return;
        }
        min = Math.min(f / intrinsicWidth, f2 / intrinsicHeight);
        matrix.postScale(min, min);
        matrix.postTranslate((f - (intrinsicWidth * min)) / 2.0f, (f2 - (intrinsicHeight * min)) / 2.0f);
    }

    protected float a(Matrix matrix, int i) {
        matrix.getValues(this.s);
        return this.s[i];
    }

    public RectF getBitmapRect() {
        return b(this.m);
    }

    protected RectF b(Matrix matrix) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        Matrix a = a(matrix);
        this.v.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        a.mapRect(this.v);
        return this.v;
    }

    protected float c(Matrix matrix) {
        return a(matrix, 0);
    }

    @SuppressLint({"Override"})
    public float getRotation() {
        return 0.0f;
    }

    public float getScale() {
        return c(this.m);
    }

    public float getBaseScale() {
        return c(this.l);
    }

    protected void a(boolean z, boolean z2) {
        if (getDrawable() != null) {
            RectF a = a(this.m, z, z2);
            if (a.left != 0.0f || a.top != 0.0f) {
                b(a.left, a.top);
            }
        }
    }

    protected RectF a(Matrix matrix, boolean z, boolean z2) {
        if (getDrawable() == null) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        float f;
        int i;
        this.w.set(0.0f, 0.0f, 0.0f, 0.0f);
        RectF b = b(matrix);
        float height = b.height();
        float width = b.width();
        if (z2) {
            int i2 = this.f;
            if (height < ((float) i2)) {
                f = ((((float) i2) - height) / 2.0f) - b.top;
            } else if (b.top > 0.0f) {
                f = -b.top;
            } else if (b.bottom < ((float) i2)) {
                f = ((float) this.f) - b.bottom;
            }
            if (z) {
                i = this.e;
                if (width < ((float) i)) {
                    height = ((((float) i) - width) / 2.0f) - b.left;
                } else if (b.left > 0.0f) {
                    height = -b.left;
                } else if (b.right < ((float) i)) {
                    height = ((float) i) - b.right;
                }
                this.w.set(height, f, 0.0f, 0.0f);
                return this.w;
            }
            height = 0.0f;
            this.w.set(height, f, 0.0f, 0.0f);
            return this.w;
        }
        f = 0.0f;
        if (z) {
            i = this.e;
            if (width < ((float) i)) {
                height = ((((float) i) - width) / 2.0f) - b.left;
            } else if (b.left > 0.0f) {
                height = -b.left;
            } else if (b.right < ((float) i)) {
                height = ((float) i) - b.right;
            }
            this.w.set(height, f, 0.0f, 0.0f);
            return this.w;
        }
        height = 0.0f;
        this.w.set(height, f, 0.0f, 0.0f);
        return this.w;
    }

    protected void b(float f, float f2) {
        if (f != 0.0f || f2 != 0.0f) {
            this.m.postTranslate(f, f2);
            setImageMatrix(getImageViewMatrix());
        }
    }

    protected void a(float f, float f2, float f3) {
        this.m.postScale(f, f, f2, f3);
        setImageMatrix(getImageViewMatrix());
    }

    protected PointF getCenter() {
        return this.g;
    }

    protected void b(float f) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        if (f < getMinScale()) {
            f = getMinScale();
        }
        PointF center = getCenter();
        b(f, center.x, center.y);
    }

    public void c(float f, float f2) {
        PointF center = getCenter();
        a(f, center.x, center.y, f2);
    }

    protected void b(float f, float f2, float f3) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        a(f / getScale(), f2, f3);
        c(getScale());
        a(true, true);
    }

    protected void c(float f) {
    }

    protected void a(float f) {
    }

    public void d(float f, float f2) {
        a((double) f, (double) f2);
    }

    protected void a(double d, double d2) {
        RectF bitmapRect = getBitmapRect();
        this.x.set((float) d, (float) d2, 0.0f, 0.0f);
        a(bitmapRect, this.x);
        b(this.x.left, this.x.top);
        a(true, true);
    }

    protected void a(RectF rectF, RectF rectF2) {
        if (rectF != null) {
            if (rectF.top >= 0.0f && rectF.bottom <= ((float) this.f)) {
                rectF2.top = 0.0f;
            }
            if (rectF.left >= 0.0f && rectF.right <= ((float) this.e)) {
                rectF2.left = 0.0f;
            }
            if (rectF.top + rectF2.top >= 0.0f && rectF.bottom > ((float) this.f)) {
                rectF2.top = (float) ((int) (0.0f - rectF.top));
            }
            if (rectF.bottom + rectF2.top <= ((float) (this.f + 0)) && rectF.top < 0.0f) {
                rectF2.top = (float) ((int) (((float) (this.f + 0)) - rectF.bottom));
            }
            if (rectF.left + rectF2.left >= 0.0f) {
                rectF2.left = (float) ((int) (0.0f - rectF.left));
            }
            if (rectF.right + rectF2.left <= ((float) (this.e + 0))) {
                rectF2.left = (float) ((int) (((float) (this.e + 0)) - rectF.right));
            }
        }
    }

    protected void a(float f, float f2, double d) {
        final double d2 = (double) f;
        final double d3 = (double) f2;
        final long currentTimeMillis = System.currentTimeMillis();
        final double d4 = d;
        this.o.post(new Runnable(this) {
            double a = 0.0d;
            double b = 0.0d;
            final /* synthetic */ ImageViewTouchBase g;

            public void run() {
                double min = Math.min(d4, (double) (System.currentTimeMillis() - currentTimeMillis));
                double a = this.g.k.a(min, 0.0d, d2, d4);
                double a2 = this.g.k.a(min, 0.0d, d3, d4);
                this.g.a(a - this.a, a2 - this.b);
                this.a = a;
                this.b = a2;
                if (min < d4) {
                    this.g.o.post(this);
                    return;
                }
                RectF a3 = this.g.a(this.g.m, true, true);
                if (a3.left != 0.0f || a3.top != 0.0f) {
                    this.g.d(a3.left, a3.top);
                }
            }
        });
    }

    protected void a(float f, float f2, float f3, float f4) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final float scale = getScale();
        final float f5 = f - scale;
        Matrix matrix = new Matrix(this.m);
        matrix.postScale(f, f, f2, f3);
        RectF a = a(matrix, true, true);
        final float f6 = f2 + (a.left * f);
        final float f7 = f3 + (a.top * f);
        final float f8 = f4;
        this.o.post(new Runnable(this) {
            final /* synthetic */ ImageViewTouchBase g;

            public void run() {
                float min = Math.min(f8, (float) (System.currentTimeMillis() - currentTimeMillis));
                this.g.b(((float) this.g.k.b((double) min, 0.0d, (double) f5, (double) f8)) + scale, f6, f7);
                if (min < f8) {
                    this.g.o.post(this);
                    return;
                }
                this.g.a(this.g.getScale());
                this.g.a(true, true);
            }
        });
    }
}
