package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.view.Gravity;
import com.bumptech.glide.load.resource.a.b;

public class j extends b {
    private final Rect a;
    private int b;
    private int c;
    private boolean d;
    private boolean e;
    private a f;

    static class a extends ConstantState {
        private static final Paint d = new Paint(6);
        final Bitmap a;
        int b;
        Paint c;

        public a(Bitmap bitmap) {
            this.c = d;
            this.a = bitmap;
        }

        a(a aVar) {
            this(aVar.a);
            this.b = aVar.b;
        }

        void a(ColorFilter colorFilter) {
            a();
            this.c.setColorFilter(colorFilter);
        }

        void a(int i) {
            a();
            this.c.setAlpha(i);
        }

        void a() {
            if (d == this.c) {
                this.c = new Paint(6);
            }
        }

        public Drawable newDrawable() {
            return new j(null, this);
        }

        public Drawable newDrawable(Resources resources) {
            return new j(resources, this);
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }

    public j(Resources resources, Bitmap bitmap) {
        this(resources, new a(bitmap));
    }

    j(Resources resources, a aVar) {
        this.a = new Rect();
        if (aVar == null) {
            throw new NullPointerException("BitmapState must not be null");
        }
        int i;
        this.f = aVar;
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
            if (i == 0) {
                i = 160;
            }
            aVar.b = i;
        } else {
            i = aVar.b;
        }
        this.b = aVar.a.getScaledWidth(i);
        this.c = aVar.a.getScaledHeight(i);
    }

    public int getIntrinsicWidth() {
        return this.b;
    }

    public int getIntrinsicHeight() {
        return this.c;
    }

    public boolean a() {
        return false;
    }

    public void a(int i) {
    }

    public void start() {
    }

    public void stop() {
    }

    public boolean isRunning() {
        return false;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.d = true;
    }

    public ConstantState getConstantState() {
        return this.f;
    }

    public void draw(Canvas canvas) {
        if (this.d) {
            Gravity.apply(119, this.b, this.c, getBounds(), this.a);
            this.d = false;
        }
        canvas.drawBitmap(this.f.a, null, this.a, this.f.c);
    }

    public void setAlpha(int i) {
        if (this.f.c.getAlpha() != i) {
            this.f.a(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f.a(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        Bitmap bitmap = this.f.a;
        return (bitmap == null || bitmap.hasAlpha() || this.f.c.getAlpha() < 255) ? -3 : -1;
    }

    public Drawable mutate() {
        if (!this.e && super.mutate() == this) {
            this.f = new a(this.f);
            this.e = true;
        }
        return this;
    }

    public Bitmap b() {
        return this.f.a;
    }
}
