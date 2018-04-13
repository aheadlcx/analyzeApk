package com.facebook.drawee.drawable;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import com.facebook.common.internal.g;
import javax.annotation.Nullable;

public class a extends Drawable implements Callback, o, p {
    private p a;
    private final d b = new d();
    private final Drawable[] c;
    private final c[] d;
    private final Rect e = new Rect();
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;

    public a(Drawable[] drawableArr) {
        int i = 0;
        g.a(drawableArr);
        this.c = drawableArr;
        while (i < this.c.length) {
            e.a(this.c[i], this, this);
            i++;
        }
        this.d = new c[this.c.length];
    }

    public int a() {
        return this.c.length;
    }

    @Nullable
    public Drawable a(int i) {
        boolean z;
        boolean z2 = true;
        if (i >= 0) {
            z = true;
        } else {
            z = false;
        }
        g.a(z);
        if (i >= this.c.length) {
            z2 = false;
        }
        g.a(z2);
        return this.c[i];
    }

    @Nullable
    public Drawable a(int i, @Nullable Drawable drawable) {
        boolean z = true;
        g.a(i >= 0);
        if (i >= this.c.length) {
            z = false;
        }
        g.a(z);
        Drawable drawable2 = this.c[i];
        if (drawable != drawable2) {
            if (drawable != null && this.h) {
                drawable.mutate();
            }
            e.a(this.c[i], null, null);
            e.a(drawable, null, null);
            e.a(drawable, this.b);
            e.a(drawable, this);
            e.a(drawable, this, this);
            this.g = false;
            this.c[i] = drawable;
            invalidateSelf();
        }
        return drawable2;
    }

    public int getIntrinsicWidth() {
        int i = -1;
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                i = Math.max(i, drawable.getIntrinsicWidth());
            }
        }
        return i > 0 ? i : -1;
    }

    public int getIntrinsicHeight() {
        int i = -1;
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                i = Math.max(i, drawable.getIntrinsicHeight());
            }
        }
        return i > 0 ? i : -1;
    }

    protected void onBoundsChange(Rect rect) {
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setBounds(rect);
            }
        }
    }

    public boolean isStateful() {
        if (!this.g) {
            this.f = false;
            for (Drawable drawable : this.c) {
                int i;
                boolean z = this.f;
                if (drawable == null || !drawable.isStateful()) {
                    i = 0;
                } else {
                    i = 1;
                }
                this.f = i | z;
            }
            this.g = true;
        }
        return this.f;
    }

    protected boolean onStateChange(int[] iArr) {
        int i = 0;
        boolean z = false;
        while (i < this.c.length) {
            Drawable drawable = this.c[i];
            if (drawable != null && drawable.setState(iArr)) {
                z = true;
            }
            i++;
        }
        return z;
    }

    protected boolean onLevelChange(int i) {
        int i2 = 0;
        boolean z = false;
        while (i2 < this.c.length) {
            Drawable drawable = this.c[i2];
            if (drawable != null && drawable.setLevel(i)) {
                z = true;
            }
            i2++;
        }
        return z;
    }

    public void draw(Canvas canvas) {
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }

    public boolean getPadding(Rect rect) {
        int i = 0;
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        Rect rect2 = this.e;
        while (i < this.c.length) {
            Drawable drawable = this.c[i];
            if (drawable != null) {
                drawable.getPadding(rect2);
                rect.left = Math.max(rect.left, rect2.left);
                rect.top = Math.max(rect.top, rect2.top);
                rect.right = Math.max(rect.right, rect2.right);
                rect.bottom = Math.max(rect.bottom, rect2.bottom);
            }
            i++;
        }
        return true;
    }

    public Drawable mutate() {
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.mutate();
            }
        }
        this.h = true;
        return this;
    }

    public int getOpacity() {
        if (this.c.length == 0) {
            return -2;
        }
        int i = -1;
        for (int i2 = 1; i2 < this.c.length; i2++) {
            Drawable drawable = this.c[i2];
            if (drawable != null) {
                i = Drawable.resolveOpacity(i, drawable.getOpacity());
            }
        }
        return i;
    }

    public void setAlpha(int i) {
        this.b.a(i);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setAlpha(i);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.b.a(colorFilter);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setDither(boolean z) {
        this.b.a(z);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    public void setFilterBitmap(boolean z) {
        this.b.b(z);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setFilterBitmap(z);
            }
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setVisible(z, z2);
            }
        }
        return visible;
    }

    public c b(int i) {
        boolean z = true;
        g.a(i >= 0);
        if (i >= this.d.length) {
            z = false;
        }
        g.a(z);
        if (this.d[i] == null) {
            this.d[i] = c(i);
        }
        return this.d[i];
    }

    private c c(int i) {
        return new a$1(this, i);
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public void a(p pVar) {
        this.a = pVar;
    }

    public void a(Matrix matrix) {
        if (this.a != null) {
            this.a.a(matrix);
        } else {
            matrix.reset();
        }
    }

    public void a(RectF rectF) {
        if (this.a != null) {
            this.a.a(rectF);
        } else {
            rectF.set(getBounds());
        }
    }

    @TargetApi(21)
    public void setHotspot(float f, float f2) {
        for (Drawable drawable : this.c) {
            if (drawable != null) {
                drawable.setHotspot(f, f2);
            }
        }
    }
}
