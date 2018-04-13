package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.internal.g;
import java.util.Arrays;

public class f extends a {
    int a;
    int b;
    long c;
    int[] d;
    int[] e;
    int f;
    boolean[] g;
    int h;
    private final Drawable[] i;

    public f(Drawable[] drawableArr) {
        boolean z = true;
        super(drawableArr);
        if (drawableArr.length < 1) {
            z = false;
        }
        g.b(z, "At least one layer required!");
        this.i = drawableArr;
        this.d = new int[drawableArr.length];
        this.e = new int[drawableArr.length];
        this.f = 255;
        this.g = new boolean[drawableArr.length];
        this.h = 0;
        g();
    }

    public void invalidateSelf() {
        if (this.h == 0) {
            super.invalidateSelf();
        }
    }

    public void b() {
        this.h++;
    }

    public void c() {
        this.h--;
        invalidateSelf();
    }

    public void c(int i) {
        this.b = i;
        if (this.a == 1) {
            this.a = 0;
        }
    }

    private void g() {
        this.a = 2;
        Arrays.fill(this.d, 0);
        this.d[0] = 255;
        Arrays.fill(this.e, 0);
        this.e[0] = 255;
        Arrays.fill(this.g, false);
        this.g[0] = true;
    }

    public void d(int i) {
        this.a = 0;
        this.g[i] = true;
        invalidateSelf();
    }

    public void e(int i) {
        this.a = 0;
        this.g[i] = false;
        invalidateSelf();
    }

    public void d() {
        this.a = 0;
        Arrays.fill(this.g, true);
        invalidateSelf();
    }

    public void e() {
        this.a = 2;
        for (int i = 0; i < this.i.length; i++) {
            int i2;
            int[] iArr = this.e;
            if (this.g[i]) {
                i2 = 255;
            } else {
                i2 = 0;
            }
            iArr[i] = i2;
        }
        invalidateSelf();
    }

    private boolean a(float f) {
        int i = 0;
        boolean z = true;
        while (i < this.i.length) {
            this.e[i] = (int) ((((float) ((this.g[i] ? 1 : -1) * 255)) * f) + ((float) this.d[i]));
            if (this.e[i] < 0) {
                this.e[i] = 0;
            }
            if (this.e[i] > 255) {
                this.e[i] = 255;
            }
            if (this.g[i] && this.e[i] < 255) {
                z = false;
            }
            if (!this.g[i] && this.e[i] > 0) {
                z = false;
            }
            i++;
        }
        return z;
    }

    public void draw(Canvas canvas) {
        int i = 2;
        int i2 = 0;
        boolean z = true;
        switch (this.a) {
            case 0:
                int i3;
                System.arraycopy(this.e, 0, this.d, 0, this.i.length);
                this.c = f();
                boolean a = a(this.b == 0 ? 1.0f : 0.0f);
                if (a) {
                    i3 = 2;
                } else {
                    i3 = 1;
                }
                this.a = i3;
                z = a;
                break;
            case 1:
                g.b(this.b > 0);
                boolean a2 = a(((float) (f() - this.c)) / ((float) this.b));
                if (!a2) {
                    i = 1;
                }
                this.a = i;
                z = a2;
                break;
        }
        while (i2 < this.i.length) {
            a(canvas, this.i[i2], (this.e[i2] * this.f) / 255);
            i2++;
        }
        if (!z) {
            invalidateSelf();
        }
    }

    private void a(Canvas canvas, Drawable drawable, int i) {
        if (drawable != null && i > 0) {
            this.h++;
            drawable.mutate().setAlpha(i);
            this.h--;
            drawable.draw(canvas);
        }
    }

    public void setAlpha(int i) {
        if (this.f != i) {
            this.f = i;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.f;
    }

    protected long f() {
        return SystemClock.uptimeMillis();
    }
}
