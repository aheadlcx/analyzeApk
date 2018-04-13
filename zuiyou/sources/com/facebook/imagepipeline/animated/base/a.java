package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import com.facebook.common.time.b;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class a extends Drawable implements Animatable, com.facebook.b.a.a {
    private static final Class<?> a = c.class;
    private boolean A;
    private boolean B;
    private long C = -1;
    private boolean D = false;
    private final Runnable E = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d();
        }
    };
    private final Runnable F = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            com.facebook.common.c.a.a(a.a, "(%s) Next Frame Task", this.a.k);
            this.a.e();
        }
    };
    private final Runnable G = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            com.facebook.common.c.a.a(a.a, "(%s) Invalidate Task", this.a.k);
            this.a.B = false;
            this.a.h();
        }
    };
    private final Runnable H = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            com.facebook.common.c.a.a(a.a, "(%s) Watchdog Task", this.a.k);
            this.a.g();
        }
    };
    private final ScheduledExecutorService b;
    private final f c;
    private final b d;
    private final int e;
    private final int f;
    private final int g;
    private final Paint h = new Paint(6);
    private final Rect i = new Rect();
    private final Paint j;
    private volatile String k;
    private e l;
    private long m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r = -1;
    private int s = -1;
    private com.facebook.common.references.a<Bitmap> t;
    private boolean u;
    private long v = -1;
    private boolean w;
    private boolean x;
    private float y = 1.0f;
    private float z = 1.0f;

    public a(ScheduledExecutorService scheduledExecutorService, e eVar, f fVar, b bVar) {
        this.b = scheduledExecutorService;
        this.l = eVar;
        this.c = fVar;
        this.d = bVar;
        this.e = this.l.b();
        this.f = this.l.c();
        this.c.a(this.l);
        this.g = this.l.d();
        this.j = new Paint();
        this.j.setColor(0);
        this.j.setStyle(Style.FILL);
        c();
    }

    private void c() {
        this.n = this.l.i();
        this.o = this.n;
        this.p = -1;
        this.q = -1;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.t != null) {
            this.t.close();
            this.t = null;
        }
    }

    public int getIntrinsicWidth() {
        return this.l.e();
    }

    public int getIntrinsicHeight() {
        return this.l.f();
    }

    public void setAlpha(int i) {
        this.h.setAlpha(i);
        h();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.h.setColorFilter(colorFilter);
        h();
    }

    public int getOpacity() {
        return -3;
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.A = true;
        if (this.t != null) {
            this.t.close();
            this.t = null;
        }
        this.r = -1;
        this.s = -1;
        this.l.k();
    }

    private void d() {
        if (this.w) {
            this.c.a();
            try {
                this.m = this.d.now();
                if (this.D) {
                    this.m -= (long) this.l.c(this.n);
                } else {
                    this.n = 0;
                    this.o = 0;
                }
                long d = this.m + ((long) this.l.d(0));
                scheduleSelf(this.F, d);
                this.C = d;
                h();
            } finally {
                this.c.b();
            }
        }
    }

    private void e() {
        this.C = -1;
        if (this.w && this.e != 0) {
            this.c.c();
            try {
                a(true);
            } finally {
                this.c.d();
            }
        }
    }

    private void a(boolean z) {
        if (this.e != 0) {
            long now = this.d.now();
            int i = (int) ((now - this.m) / ((long) this.e));
            if (this.g == 0 || i < this.g) {
                int i2 = (int) ((now - this.m) % ((long) this.e));
                int b = this.l.b(i2);
                Object obj = this.n != b ? 1 : null;
                this.n = b;
                this.o = (i * this.f) + b;
                if (!z) {
                    return;
                }
                if (obj != null) {
                    h();
                    return;
                }
                int c = (this.l.c(this.n) + this.l.d(this.n)) - i2;
                i = (this.n + 1) % this.f;
                now += (long) c;
                if (this.C == -1 || this.C > now) {
                    com.facebook.common.c.a.a(a, "(%s) Next frame (%d) in %d ms", this.k, Integer.valueOf(i), Integer.valueOf(c));
                    unscheduleSelf(this.F);
                    scheduleSelf(this.F, now);
                    this.C = now;
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        int i = 0;
        this.c.e();
        try {
            this.u = false;
            if (this.w && !this.x) {
                this.b.schedule(this.H, 2000, TimeUnit.MILLISECONDS);
                this.x = true;
            }
            if (this.A) {
                this.i.set(getBounds());
                if (!this.i.isEmpty()) {
                    e b = this.l.b(this.i);
                    if (b != this.l) {
                        this.l.k();
                        this.l = b;
                        this.c.a(b);
                    }
                    this.y = ((float) this.i.width()) / ((float) this.l.g());
                    this.z = ((float) this.i.height()) / ((float) this.l.h());
                    this.A = false;
                }
            }
            if (!this.i.isEmpty()) {
                boolean a;
                canvas.save();
                canvas.scale(this.y, this.z);
                if (this.p != -1) {
                    a = a(canvas, this.p, this.q);
                    i = 0 | a;
                    if (a) {
                        com.facebook.common.c.a.a(a, "(%s) Rendered pending frame %d", this.k, Integer.valueOf(this.p));
                        this.p = -1;
                        this.q = -1;
                    } else {
                        com.facebook.common.c.a.a(a, "(%s) Trying again later for pending %d", this.k, Integer.valueOf(this.p));
                        f();
                    }
                }
                if (this.p == -1) {
                    if (this.w) {
                        a(false);
                    }
                    a = a(canvas, this.n, this.o);
                    i |= a;
                    if (a) {
                        com.facebook.common.c.a.a(a, "(%s) Rendered current frame %d", this.k, Integer.valueOf(this.n));
                        if (this.w) {
                            a(true);
                        }
                    } else {
                        com.facebook.common.c.a.a(a, "(%s) Trying again later for current %d", this.k, Integer.valueOf(this.n));
                        this.p = this.n;
                        this.q = this.o;
                        f();
                    }
                }
                if (i == 0 && this.t != null) {
                    canvas.drawBitmap((Bitmap) this.t.a(), 0.0f, 0.0f, this.h);
                    com.facebook.common.c.a.a(a, "(%s) Rendered last known frame %d", this.k, Integer.valueOf(this.r));
                    i = 1;
                }
                if (i == 0) {
                    com.facebook.common.references.a l = this.l.l();
                    if (l != null) {
                        canvas.drawBitmap((Bitmap) l.a(), 0.0f, 0.0f, this.h);
                        l.close();
                        com.facebook.common.c.a.a(a, "(%s) Rendered preview frame", this.k);
                        i = 1;
                    }
                }
                if (i == 0) {
                    canvas.drawRect(0.0f, 0.0f, (float) this.i.width(), (float) this.i.height(), this.j);
                    com.facebook.common.c.a.a(a, "(%s) Failed to draw a frame", this.k);
                }
                canvas.restore();
                this.c.a(canvas, this.i);
                this.c.f();
            }
        } finally {
            this.c.f();
        }
    }

    private void f() {
        if (!this.B) {
            this.B = true;
            scheduleSelf(this.G, 5);
        }
    }

    private boolean a(Canvas canvas, int i, int i2) {
        com.facebook.common.references.a g = this.l.g(i);
        if (g == null) {
            return false;
        }
        canvas.drawBitmap((Bitmap) g.a(), 0.0f, 0.0f, this.h);
        if (this.t != null) {
            this.t.close();
        }
        if (this.w && i2 > this.s) {
            int i3 = (i2 - this.s) - 1;
            this.c.b(1);
            this.c.a(i3);
            if (i3 > 0) {
                com.facebook.common.c.a.a(a, "(%s) Dropped %d frames", this.k, Integer.valueOf(i3));
            }
        }
        this.t = g;
        this.r = i;
        this.s = i2;
        com.facebook.common.c.a.a(a, "(%s) Drew frame %d", this.k, Integer.valueOf(i));
        return true;
    }

    private void g() {
        boolean z = false;
        this.x = false;
        if (this.w) {
            long now = this.d.now();
            boolean z2 = this.u && now - this.v > 1000;
            if (this.C != -1 && now - this.C > 1000) {
                z = true;
            }
            if (z2 || r2) {
                a();
                h();
                return;
            }
            this.b.schedule(this.H, 2000, TimeUnit.MILLISECONDS);
            this.x = true;
        }
    }

    private void h() {
        this.u = true;
        this.v = this.d.now();
        invalidateSelf();
    }

    public void start() {
        if (this.e != 0 && this.f > 1) {
            this.w = true;
            scheduleSelf(this.E, this.d.now());
        }
    }

    public void stop() {
        this.D = false;
        this.w = false;
    }

    public boolean isRunning() {
        return this.w;
    }

    protected boolean onLevelChange(int i) {
        if (this.w) {
            return false;
        }
        int b = this.l.b(i);
        if (b == this.n) {
            return false;
        }
        try {
            this.n = b;
            this.o = b;
            h();
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public void a() {
        com.facebook.common.c.a.a(a, "(%s) Dropping caches", this.k);
        if (this.t != null) {
            this.t.close();
            this.t = null;
            this.r = -1;
            this.s = -1;
        }
        this.l.k();
    }
}
