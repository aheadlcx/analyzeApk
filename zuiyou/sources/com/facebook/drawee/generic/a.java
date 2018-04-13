package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.d.c;
import com.facebook.drawee.drawable.f;
import com.facebook.drawee.drawable.g;
import com.facebook.drawee.drawable.h;
import com.facebook.drawee.drawable.m;
import com.facebook.drawee.drawable.n$b;
import javax.annotation.Nullable;

public class a implements c {
    private final Drawable a = new ColorDrawable(0);
    private final Resources b;
    @Nullable
    private RoundingParams c;
    private final d d;
    private final f e;
    private final g f;

    a(b bVar) {
        int i;
        int i2 = 0;
        this.b = bVar.a();
        this.c = bVar.s();
        this.f = new g(this.a);
        int size = bVar.q() != null ? bVar.q().size() : 1;
        if (bVar.r() != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i3 = size + i;
        Drawable[] drawableArr = new Drawable[(i3 + 6)];
        drawableArr[0] = a(bVar.p(), null);
        drawableArr[1] = a(bVar.d(), bVar.e());
        drawableArr[2] = a(this.f, bVar.l(), bVar.n(), bVar.m(), bVar.o());
        drawableArr[3] = a(bVar.j(), bVar.k());
        drawableArr[4] = a(bVar.f(), bVar.g());
        drawableArr[5] = a(bVar.h(), bVar.i());
        if (i3 > 0) {
            if (bVar.q() != null) {
                for (Drawable a : bVar.q()) {
                    i = i2 + 1;
                    drawableArr[i2 + 6] = a(a, null);
                    i2 = i;
                }
            } else {
                i2 = 1;
            }
            if (bVar.r() != null) {
                drawableArr[i2 + 6] = a(bVar.r(), null);
            }
        }
        this.e = new f(drawableArr);
        this.e.c(bVar.b());
        this.d = new d(e.a(this.e, this.c));
        this.d.mutate();
        d();
    }

    @Nullable
    private Drawable a(Drawable drawable, @Nullable n$b n_b, @Nullable PointF pointF, @Nullable Matrix matrix, @Nullable ColorFilter colorFilter) {
        drawable.setColorFilter(colorFilter);
        return e.a(e.a(drawable, n_b, pointF), matrix);
    }

    @Nullable
    private Drawable a(@Nullable Drawable drawable, @Nullable n$b n_b) {
        return e.a(e.a(drawable, this.c, this.b), n_b);
    }

    private void c() {
        this.f.a(this.a);
    }

    private void d() {
        if (this.e != null) {
            this.e.b();
            this.e.d();
            e();
            b(1);
            this.e.e();
            this.e.c();
        }
    }

    private void e() {
        c(1);
        c(2);
        c(3);
        c(4);
        c(5);
    }

    private void b(int i) {
        if (i >= 0) {
            this.e.d(i);
        }
    }

    private void c(int i) {
        if (i >= 0) {
            this.e.e(i);
        }
    }

    private void a(float f) {
        Drawable a = this.e.a(3);
        if (a != null) {
            if (f >= 0.999f) {
                if (a instanceof Animatable) {
                    ((Animatable) a).stop();
                }
                c(3);
            } else {
                if (a instanceof Animatable) {
                    ((Animatable) a).start();
                }
                b(3);
            }
            a.setLevel(Math.round(10000.0f * f));
        }
    }

    public Drawable a() {
        return this.d;
    }

    public void b() {
        c();
        d();
    }

    public void a(Drawable drawable, float f, boolean z) {
        Drawable a = e.a(drawable, this.c, this.b);
        a.mutate();
        this.f.a(a);
        this.e.b();
        e();
        b(2);
        a(f);
        if (z) {
            this.e.e();
        }
        this.e.c();
    }

    public void a(float f, boolean z) {
        if (this.e.a(3) != null) {
            this.e.b();
            a(f);
            if (z) {
                this.e.e();
            }
            this.e.c();
        }
    }

    public void a(Throwable th) {
        this.e.b();
        e();
        if (this.e.a(5) != null) {
            b(5);
        } else {
            b(1);
        }
        this.e.c();
    }

    public void b(Throwable th) {
        this.e.b();
        e();
        if (this.e.a(4) != null) {
            b(4);
        } else {
            b(1);
        }
        this.e.c();
    }

    public void a(@Nullable Drawable drawable) {
        this.d.d(drawable);
    }

    private com.facebook.drawee.drawable.c d(int i) {
        com.facebook.drawee.drawable.c b = this.e.b(i);
        if (b.a() instanceof h) {
            b = (h) b.a();
        }
        if (b.a() instanceof m) {
            return (m) b.a();
        }
        return b;
    }

    private void b(int i, @Nullable Drawable drawable) {
        if (drawable == null) {
            this.e.a(i, null);
            return;
        }
        d(i).a(e.a(drawable, this.c, this.b));
    }

    private m e(int i) {
        com.facebook.drawee.drawable.c d = d(i);
        if (d instanceof m) {
            return (m) d;
        }
        return e.a(d, n$b.a);
    }

    public void a(n$b n_b) {
        com.facebook.common.internal.g.a(n_b);
        e(2).a(n_b);
    }

    public void b(@Nullable Drawable drawable) {
        b(1, drawable);
    }

    public void a(int i) {
        b(this.b.getDrawable(i));
    }

    public void c(@Nullable Drawable drawable) {
        b(5, drawable);
    }

    public void a(int i, @Nullable Drawable drawable) {
        boolean z = i >= 0 && i + 6 < this.e.a();
        com.facebook.common.internal.g.a(z, "The given index does not correspond to an overlay image.");
        b(i + 6, drawable);
    }

    public void d(@Nullable Drawable drawable) {
        a(0, drawable);
    }
}
