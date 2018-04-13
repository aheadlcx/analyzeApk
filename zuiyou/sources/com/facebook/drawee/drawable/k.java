package com.facebook.drawee.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.common.internal.g;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import javax.annotation.Nullable;

public class k extends BitmapDrawable implements j, o {
    final float[] a = new float[8];
    final RectF b = new RectF();
    final RectF c = new RectF();
    final RectF d = new RectF();
    final RectF e = new RectF();
    final Matrix f = new Matrix();
    final Matrix g = new Matrix();
    final Matrix h = new Matrix();
    final Matrix i = new Matrix();
    final Matrix j = new Matrix();
    final Matrix k = new Matrix();
    private boolean l = false;
    private boolean m = false;
    private final float[] n = new float[8];
    private float o = 0.0f;
    private int p = 0;
    private float q = 0.0f;
    private final Path r = new Path();
    private final Path s = new Path();
    private boolean t = true;
    private final Paint u = new Paint();
    private final Paint v = new Paint(1);
    private boolean w = true;
    private WeakReference<Bitmap> x;
    @Nullable
    private p y;

    public k(Resources resources, Bitmap bitmap, @Nullable Paint paint) {
        super(resources, bitmap);
        if (paint != null) {
            this.u.set(paint);
        }
        this.u.setFlags(1);
        this.v.setStyle(Style.STROKE);
    }

    public void a(boolean z) {
        this.l = z;
        this.t = true;
        invalidateSelf();
    }

    public void a(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.n, 0.0f);
            this.m = false;
        } else {
            boolean z;
            if (fArr.length == 8) {
                z = true;
            } else {
                z = false;
            }
            g.a(z, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.n, 0, 8);
            this.m = false;
            for (int i = 0; i < 8; i++) {
                int i2;
                boolean z2 = this.m;
                if (fArr[i] > 0.0f) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                this.m = i2 | z2;
            }
        }
        this.t = true;
        invalidateSelf();
    }

    public void a(int i, float f) {
        if (this.p != i || this.o != f) {
            this.p = i;
            this.o = f;
            this.t = true;
            invalidateSelf();
        }
    }

    public void a(float f) {
        if (this.q != f) {
            this.q = f;
            this.t = true;
            invalidateSelf();
        }
    }

    public void a(@Nullable p pVar) {
        this.y = pVar;
    }

    public void setAlpha(int i) {
        if (i != this.u.getAlpha()) {
            this.u.setAlpha(i);
            super.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.u.setColorFilter(colorFilter);
        super.setColorFilter(colorFilter);
    }

    public void draw(Canvas canvas) {
        if (a()) {
            b();
            c();
            d();
            int save = canvas.save();
            canvas.concat(this.j);
            canvas.drawPath(this.r, this.u);
            if (this.o > 0.0f) {
                this.v.setStrokeWidth(this.o);
                this.v.setColor(e.a(this.p, this.u.getAlpha()));
                canvas.drawPath(this.s, this.v);
            }
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    boolean a() {
        return this.l || this.m || this.o > 0.0f;
    }

    private void b() {
        if (this.y != null) {
            this.y.a(this.h);
            this.y.a(this.b);
        } else {
            this.h.reset();
            this.b.set(getBounds());
        }
        this.d.set(0.0f, 0.0f, (float) getBitmap().getWidth(), (float) getBitmap().getHeight());
        this.e.set(getBounds());
        this.f.setRectToRect(this.d, this.e, ScaleToFit.FILL);
        if (!(this.h.equals(this.i) && this.f.equals(this.g))) {
            this.w = true;
            this.h.invert(this.j);
            this.k.set(this.h);
            this.k.preConcat(this.f);
            this.i.set(this.h);
            this.g.set(this.f);
        }
        if (!this.b.equals(this.c)) {
            this.t = true;
            this.c.set(this.b);
        }
    }

    private void c() {
        if (this.t) {
            this.s.reset();
            this.b.inset(this.o / 2.0f, this.o / 2.0f);
            if (this.l) {
                this.s.addCircle(this.b.centerX(), this.b.centerY(), Math.min(this.b.width(), this.b.height()) / 2.0f, Direction.CW);
            } else {
                for (int i = 0; i < this.a.length; i++) {
                    this.a[i] = (this.n[i] + this.q) - (this.o / 2.0f);
                }
                this.s.addRoundRect(this.b, this.a, Direction.CW);
            }
            this.b.inset((-this.o) / 2.0f, (-this.o) / 2.0f);
            this.r.reset();
            this.b.inset(this.q, this.q);
            if (this.l) {
                this.r.addCircle(this.b.centerX(), this.b.centerY(), Math.min(this.b.width(), this.b.height()) / 2.0f, Direction.CW);
            } else {
                this.r.addRoundRect(this.b, this.n, Direction.CW);
            }
            this.b.inset(-this.q, -this.q);
            this.r.setFillType(FillType.WINDING);
            this.t = false;
        }
    }

    private void d() {
        Bitmap bitmap = getBitmap();
        if (this.x == null || this.x.get() != bitmap) {
            this.x = new WeakReference(bitmap);
            this.u.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP));
            this.w = true;
        }
        if (this.w) {
            this.u.getShader().setLocalMatrix(this.k);
            this.w = false;
        }
    }
}
