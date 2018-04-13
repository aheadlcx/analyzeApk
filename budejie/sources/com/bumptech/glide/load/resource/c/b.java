package com.bumptech.glide.load.resource.c;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.view.Gravity;
import com.bumptech.glide.b.c;
import com.bumptech.glide.load.f;

public class b extends com.bumptech.glide.load.resource.a.b implements com.bumptech.glide.load.resource.c.f.b {
    private final Paint a;
    private final Rect b;
    private final a c;
    private final com.bumptech.glide.b.a d;
    private final f e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private boolean l;

    static class a extends ConstantState {
        c a;
        byte[] b;
        Context c;
        f<Bitmap> d;
        int e;
        int f;
        com.bumptech.glide.b.a.a g;
        com.bumptech.glide.load.engine.a.c h;
        Bitmap i;

        public a(c cVar, byte[] bArr, Context context, f<Bitmap> fVar, int i, int i2, com.bumptech.glide.b.a.a aVar, com.bumptech.glide.load.engine.a.c cVar2, Bitmap bitmap) {
            if (bitmap == null) {
                throw new NullPointerException("The first frame of the GIF must not be null");
            }
            this.a = cVar;
            this.b = bArr;
            this.h = cVar2;
            this.i = bitmap;
            this.c = context.getApplicationContext();
            this.d = fVar;
            this.e = i;
            this.f = i2;
            this.g = aVar;
        }

        public Drawable newDrawable(Resources resources) {
            return newDrawable();
        }

        public Drawable newDrawable() {
            return new b(this);
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }

    public b(Context context, com.bumptech.glide.b.a.a aVar, com.bumptech.glide.load.engine.a.c cVar, f<Bitmap> fVar, int i, int i2, c cVar2, byte[] bArr, Bitmap bitmap) {
        this(new a(cVar2, bArr, context, fVar, i, i2, aVar, cVar, bitmap));
    }

    public b(b bVar, Bitmap bitmap, f<Bitmap> fVar) {
        this(new a(bVar.c.a, bVar.c.b, bVar.c.c, fVar, bVar.c.e, bVar.c.f, bVar.c.g, bVar.c.h, bitmap));
    }

    b(a aVar) {
        this.b = new Rect();
        this.i = true;
        this.k = -1;
        if (aVar == null) {
            throw new NullPointerException("GifState must not be null");
        }
        this.c = aVar;
        this.d = new com.bumptech.glide.b.a(aVar.g);
        this.a = new Paint();
        this.d.a(aVar.a, aVar.b);
        this.e = new f(aVar.c, this, this.d, aVar.e, aVar.f);
        this.e.a(aVar.d);
    }

    public Bitmap b() {
        return this.c.i;
    }

    public com.bumptech.glide.b.a c() {
        return this.d;
    }

    public f<Bitmap> d() {
        return this.c.d;
    }

    public byte[] e() {
        return this.c.b;
    }

    public int f() {
        return this.d.e();
    }

    private void h() {
        this.j = 0;
    }

    public void start() {
        this.g = true;
        h();
        if (this.i) {
            j();
        }
    }

    public void stop() {
        this.g = false;
        k();
        if (VERSION.SDK_INT < 11) {
            i();
        }
    }

    private void i() {
        this.e.c();
        invalidateSelf();
    }

    private void j() {
        if (this.d.e() == 1) {
            invalidateSelf();
        } else if (!this.f) {
            this.f = true;
            this.e.a();
            invalidateSelf();
        }
    }

    private void k() {
        this.f = false;
        this.e.b();
    }

    public boolean setVisible(boolean z, boolean z2) {
        this.i = z;
        if (!z) {
            k();
        } else if (this.g) {
            j();
        }
        return super.setVisible(z, z2);
    }

    public int getIntrinsicWidth() {
        return this.c.i.getWidth();
    }

    public int getIntrinsicHeight() {
        return this.c.i.getHeight();
    }

    public boolean isRunning() {
        return this.f;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.l = true;
    }

    public void draw(Canvas canvas) {
        if (!this.h) {
            if (this.l) {
                Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), this.b);
                this.l = false;
            }
            Bitmap d = this.e.d();
            if (d == null) {
                d = this.c.i;
            }
            canvas.drawBitmap(d, null, this.b, this.a);
        }
    }

    public void setAlpha(int i) {
        this.a.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -2;
    }

    @TargetApi(11)
    public void b(int i) {
        if (VERSION.SDK_INT < 11 || getCallback() != null) {
            invalidateSelf();
            if (i == this.d.e() - 1) {
                this.j++;
            }
            if (this.k != -1 && this.j >= this.k) {
                stop();
                return;
            }
            return;
        }
        stop();
        i();
    }

    public ConstantState getConstantState() {
        return this.c;
    }

    public void g() {
        this.h = true;
        this.c.h.a(this.c.i);
        this.e.c();
        this.e.b();
    }

    public boolean a() {
        return true;
    }

    public void a(int i) {
        if (i <= 0 && i != -1 && i != 0) {
            throw new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
        } else if (i == 0) {
            this.k = this.d.g();
        } else {
            this.k = i;
        }
    }
}
