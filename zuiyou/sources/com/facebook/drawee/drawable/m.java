package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.f;
import com.facebook.common.internal.g;

public class m extends g {
    n$b a;
    Object c;
    PointF d = null;
    int e = 0;
    int f = 0;
    Matrix g;
    private Matrix h = new Matrix();

    public m(Drawable drawable, n$b n_b) {
        super((Drawable) g.a((Object) drawable));
        this.a = n_b;
    }

    public Drawable b(Drawable drawable) {
        Drawable b = super.b(drawable);
        c();
        return b;
    }

    public n$b b() {
        return this.a;
    }

    public void a(n$b n_b) {
        if (!f.a(this.a, n_b)) {
            this.a = n_b;
            this.c = null;
            c();
            invalidateSelf();
        }
    }

    public void a(PointF pointF) {
        if (!f.a(this.d, pointF)) {
            if (this.d == null) {
                this.d = new PointF();
            }
            this.d.set(pointF);
            c();
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        d();
        if (this.g != null) {
            int save = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.g);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    protected void onBoundsChange(Rect rect) {
        c();
    }

    private void d() {
        Object obj = null;
        Object obj2;
        if (this.a instanceof n$k) {
            Object a = ((n$k) this.a).a();
            obj2 = (a == null || !a.equals(this.c)) ? 1 : null;
            this.c = a;
        } else {
            obj2 = null;
        }
        if (!(this.e == getCurrent().getIntrinsicWidth() && this.f == getCurrent().getIntrinsicHeight())) {
            obj = 1;
        }
        if (obj != null || r0 != null) {
            c();
        }
    }

    void c() {
        float f = 0.5f;
        Drawable current = getCurrent();
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        int intrinsicWidth = current.getIntrinsicWidth();
        this.e = intrinsicWidth;
        int intrinsicHeight = current.getIntrinsicHeight();
        this.f = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            current.setBounds(bounds);
            this.g = null;
        } else if (intrinsicWidth == width && intrinsicHeight == height) {
            current.setBounds(bounds);
            this.g = null;
        } else if (this.a == n$b.a) {
            current.setBounds(bounds);
            this.g = null;
        } else {
            current.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            n$b n_b = this.a;
            Matrix matrix = this.h;
            float f2 = this.d != null ? this.d.x : 0.5f;
            if (this.d != null) {
                f = this.d.y;
            }
            n_b.a(matrix, bounds, intrinsicWidth, intrinsicHeight, f2, f);
            this.g = this.h;
        }
    }

    public void a(Matrix matrix) {
        b(matrix);
        d();
        if (this.g != null) {
            matrix.preConcat(this.g);
        }
    }
}
