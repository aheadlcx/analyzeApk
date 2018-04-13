package com.facebook.drawee.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.n$b;
import javax.annotation.Nullable;

public class a extends Drawable {
    private String a;
    private int b;
    private int c;
    private int d;
    private String e;
    private n$b f;
    private int g;
    private int h;
    private int i = 80;
    private final Paint j = new Paint(1);
    private final Matrix k = new Matrix();
    private final Rect l = new Rect();
    private final RectF m = new RectF();
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;

    public a() {
        a();
    }

    public void a() {
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.g = -1;
        this.h = -1;
        this.e = null;
        a(null);
        invalidateSelf();
    }

    public void a(@Nullable String str) {
        if (str == null) {
            str = "none";
        }
        this.a = str;
        invalidateSelf();
    }

    public void a(int i, int i2) {
        this.b = i;
        this.c = i2;
        invalidateSelf();
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(n$b n_b) {
        this.f = n_b;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a(rect, 7, 7);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        this.j.setStyle(Style.STROKE);
        this.j.setStrokeWidth(2.0f);
        this.j.setColor(-26624);
        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.j);
        this.j.setStyle(Style.FILL);
        this.j.setColor(a(this.b, this.c, this.f));
        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.j);
        this.j.setStyle(Style.FILL);
        this.j.setStrokeWidth(0.0f);
        this.j.setColor(-1);
        this.q = this.n;
        this.r = this.o;
        a(canvas, "ID: %s", this.a);
        a(canvas, "D: %dx%d", Integer.valueOf(bounds.width()), Integer.valueOf(bounds.height()));
        a(canvas, "I: %dx%d", Integer.valueOf(this.b), Integer.valueOf(this.c));
        a(canvas, "I: %d KiB", Integer.valueOf(this.d / 1024));
        if (this.e != null) {
            a(canvas, "i format: %s", this.e);
        }
        if (this.g > 0) {
            a(canvas, "anim: f %d, l %d", Integer.valueOf(this.g), Integer.valueOf(this.h));
        }
        if (this.f != null) {
            a(canvas, "scale: %s", this.f);
        }
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return -3;
    }

    private void a(Rect rect, int i, int i2) {
        int min = Math.min(40, Math.max(12, Math.min(rect.width() / i2, rect.height() / i)));
        this.j.setTextSize((float) min);
        this.p = min + 8;
        if (this.i == 80) {
            this.p *= -1;
        }
        this.n = rect.left + 10;
        this.o = this.i == 80 ? rect.bottom - 10 : (rect.top + 10) + 12;
    }

    private void a(Canvas canvas, String str, @Nullable Object... objArr) {
        if (objArr == null) {
            canvas.drawText(str, (float) this.q, (float) this.r, this.j);
        } else {
            canvas.drawText(String.format(str, objArr), (float) this.q, (float) this.r, this.j);
        }
        this.r += this.p;
    }

    int a(int i, int i2, @Nullable n$b n_b) {
        int width = getBounds().width();
        int height = getBounds().height();
        if (width <= 0 || height <= 0 || i <= 0 || i2 <= 0) {
            return 1727284022;
        }
        int min;
        int min2;
        if (n_b != null) {
            Rect rect = this.l;
            this.l.top = 0;
            rect.left = 0;
            this.l.right = width;
            this.l.bottom = height;
            this.k.reset();
            n_b.a(this.k, this.l, i, i2, 0.0f, 0.0f);
            RectF rectF = this.m;
            this.m.top = 0.0f;
            rectF.left = 0.0f;
            this.m.right = (float) i;
            this.m.bottom = (float) i2;
            this.k.mapRect(this.m);
            int height2 = (int) this.m.height();
            min = Math.min(width, (int) this.m.width());
            min2 = Math.min(height, height2);
        } else {
            min2 = height;
            min = width;
        }
        float f = ((float) min) * 0.1f;
        float f2 = ((float) min) * 0.5f;
        float f3 = ((float) min2) * 0.1f;
        float f4 = ((float) min2) * 0.5f;
        min = Math.abs(i - min);
        min2 = Math.abs(i2 - min2);
        if (((float) min) >= f || ((float) min2) >= f3) {
            return (((float) min) >= f2 || ((float) min2) >= f4) ? 1727284022 : 1728026624;
        } else {
            return 1716301648;
        }
    }
}
