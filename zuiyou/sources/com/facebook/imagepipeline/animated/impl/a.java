package com.facebook.imagepipeline.animated.impl;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.d;
import com.facebook.imagepipeline.animated.base.i;
import com.facebook.imagepipeline.animated.base.j;
import com.facebook.imagepipeline.animated.base.k;
import javax.annotation.concurrent.GuardedBy;

public class a implements d {
    private final com.facebook.imagepipeline.animated.a.a a;
    private final k b;
    private final i c;
    private final Rect d;
    private final int[] e = this.c.d();
    private final int[] f;
    private final int g;
    private final AnimatedDrawableFrameInfo[] h;
    @GuardedBy
    private Bitmap i;

    public a(com.facebook.imagepipeline.animated.a.a aVar, k kVar, Rect rect) {
        this.a = aVar;
        this.b = kVar;
        this.c = kVar.a();
        this.a.a(this.e);
        this.g = this.a.b(this.e);
        this.f = this.a.c(this.e);
        this.d = a(this.c, rect);
        this.h = new AnimatedDrawableFrameInfo[this.c.c()];
        for (int i = 0; i < this.c.c(); i++) {
            this.h[i] = this.c.b(i);
        }
    }

    private static Rect a(i iVar, Rect rect) {
        if (rect == null) {
            return new Rect(0, 0, iVar.a(), iVar.b());
        }
        return new Rect(0, 0, Math.min(rect.width(), iVar.a()), Math.min(rect.height(), iVar.b()));
    }

    public k a() {
        return this.b;
    }

    public int b() {
        return this.g;
    }

    public int c() {
        return this.c.c();
    }

    public int d() {
        return this.c.e();
    }

    public int e() {
        return this.c.a();
    }

    public int f() {
        return this.c.b();
    }

    public int g() {
        return this.d.width();
    }

    public int h() {
        return this.d.height();
    }

    public AnimatedDrawableFrameInfo a(int i) {
        return this.h[i];
    }

    public int b(int i) {
        return this.a.a(this.f, i);
    }

    public int c(int i) {
        g.a(i, this.f.length);
        return this.f[i];
    }

    public int d(int i) {
        return this.e[i];
    }

    public int i() {
        return this.b.b();
    }

    public d a(Rect rect) {
        return a(this.c, rect).equals(this.d) ? this : new a(this.a, this.b, rect);
    }

    public synchronized int j() {
        int i;
        i = 0;
        if (this.i != null) {
            i = 0 + this.a.a(this.i);
        }
        return i + this.c.g();
    }

    public com.facebook.common.references.a<Bitmap> e(int i) {
        return this.b.a(i);
    }

    public boolean f(int i) {
        return this.b.b(i);
    }

    public void a(int i, Canvas canvas) {
        j c = this.c.c(i);
        try {
            if (this.c.f()) {
                b(canvas, c);
            } else {
                a(canvas, c);
            }
            c.a();
        } catch (Throwable th) {
            c.a();
        }
    }

    private void b(Canvas canvas, j jVar) {
        double width = ((double) this.d.width()) / ((double) this.c.a());
        double height = ((double) this.d.height()) / ((double) this.c.b());
        int round = (int) Math.round(((double) jVar.c()) * width);
        int round2 = (int) Math.round(((double) jVar.d()) * height);
        int e = (int) (width * ((double) jVar.e()));
        int f = (int) (height * ((double) jVar.f()));
        synchronized (this) {
            if (this.i == null) {
                this.i = Bitmap.createBitmap(this.d.width(), this.d.height(), Config.ARGB_8888);
            }
            this.i.eraseColor(0);
            jVar.a(round, round2, this.i);
            canvas.drawBitmap(this.i, (float) e, (float) f, null);
        }
    }

    public void a(Canvas canvas, j jVar) {
        int c = jVar.c();
        int d = jVar.d();
        int e = jVar.e();
        int f = jVar.f();
        synchronized (this) {
            if (this.i == null) {
                this.i = Bitmap.createBitmap(this.c.a(), this.c.b(), Config.ARGB_8888);
            }
            this.i.eraseColor(0);
            jVar.a(c, d, this.i);
            float width = ((float) this.d.width()) / ((float) this.c.a());
            float height = ((float) this.d.height()) / ((float) this.c.b());
            canvas.save();
            canvas.scale(width, height);
            canvas.translate((float) e, (float) f);
            canvas.drawBitmap(this.i, 0.0f, 0.0f, null);
            canvas.restore();
        }
    }

    public synchronized void k() {
        if (this.i != null) {
            this.i.recycle();
            this.i = null;
        }
    }
}
