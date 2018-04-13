package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.g;

public class h extends g {
    private Matrix a;
    private Matrix c;
    private int d = 0;
    private int e = 0;

    public h(Drawable drawable, Matrix matrix) {
        super((Drawable) g.a((Object) drawable));
        this.a = matrix;
    }

    public Drawable b(Drawable drawable) {
        Drawable b = super.b(drawable);
        c();
        return b;
    }

    public void draw(Canvas canvas) {
        b();
        if (this.c != null) {
            int save = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.c);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        c();
    }

    private void b() {
        if (this.d != getCurrent().getIntrinsicWidth() || this.e != getCurrent().getIntrinsicHeight()) {
            c();
        }
    }

    private void c() {
        Drawable current = getCurrent();
        Rect bounds = getBounds();
        int intrinsicWidth = current.getIntrinsicWidth();
        this.d = intrinsicWidth;
        int intrinsicHeight = current.getIntrinsicHeight();
        this.e = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            current.setBounds(bounds);
            this.c = null;
            return;
        }
        current.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        this.c = this.a;
    }

    public void a(Matrix matrix) {
        super.a(matrix);
        if (this.c != null) {
            matrix.preConcat(this.c);
        }
    }
}
