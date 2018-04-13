package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.g;
import java.util.Arrays;

public class l extends Drawable implements j {
    final float[] a = new float[8];
    final Paint b = new Paint(1);
    final Path c = new Path();
    final Path d = new Path();
    private final float[] e = new float[8];
    private boolean f = false;
    private float g = 0.0f;
    private float h = 0.0f;
    private int i = 0;
    private int j = 0;
    private final RectF k = new RectF();
    private int l = 255;

    public l(int i) {
        a(i);
    }

    public static l a(ColorDrawable colorDrawable) {
        return new l(colorDrawable.getColor());
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a();
    }

    public void draw(Canvas canvas) {
        this.b.setColor(e.a(this.j, this.l));
        this.b.setStyle(Style.FILL);
        canvas.drawPath(this.c, this.b);
        if (this.g != 0.0f) {
            this.b.setColor(e.a(this.i, this.l));
            this.b.setStyle(Style.STROKE);
            this.b.setStrokeWidth(this.g);
            canvas.drawPath(this.d, this.b);
        }
    }

    public void a(boolean z) {
        this.f = z;
        a();
        invalidateSelf();
    }

    public void a(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.e, 0.0f);
        } else {
            boolean z;
            if (fArr.length == 8) {
                z = true;
            } else {
                z = false;
            }
            g.a(z, (Object) "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.e, 0, 8);
        }
        a();
        invalidateSelf();
    }

    public void a(int i) {
        if (this.j != i) {
            this.j = i;
            invalidateSelf();
        }
    }

    public void a(int i, float f) {
        if (this.i != i) {
            this.i = i;
            invalidateSelf();
        }
        if (this.g != f) {
            this.g = f;
            a();
            invalidateSelf();
        }
    }

    public void a(float f) {
        if (this.h != f) {
            this.h = f;
            a();
            invalidateSelf();
        }
    }

    public void setAlpha(int i) {
        if (i != this.l) {
            this.l = i;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.l;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return e.a(e.a(this.j, this.l));
    }

    private void a() {
        this.c.reset();
        this.d.reset();
        this.k.set(getBounds());
        this.k.inset(this.g / 2.0f, this.g / 2.0f);
        if (this.f) {
            this.d.addCircle(this.k.centerX(), this.k.centerY(), Math.min(this.k.width(), this.k.height()) / 2.0f, Direction.CW);
        } else {
            for (int i = 0; i < this.a.length; i++) {
                this.a[i] = (this.e[i] + this.h) - (this.g / 2.0f);
            }
            this.d.addRoundRect(this.k, this.a, Direction.CW);
        }
        this.k.inset((-this.g) / 2.0f, (-this.g) / 2.0f);
        this.k.inset(this.h, this.h);
        if (this.f) {
            this.c.addCircle(this.k.centerX(), this.k.centerY(), Math.min(this.k.width(), this.k.height()) / 2.0f, Direction.CW);
        } else {
            this.c.addRoundRect(this.k, this.e, Direction.CW);
        }
        this.k.inset(-this.h, -this.h);
    }
}
