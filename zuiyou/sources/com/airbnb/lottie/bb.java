package com.airbnb.lottie;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

class bb extends d {
    @Nullable
    private n<?, Float> A;
    @Nullable
    private n<?, Float> B;
    private n<?, Integer> C;
    private n<?, Integer> D;
    private List<n<?, Float>> E;
    private n<?, Float> F;
    private boolean G = true;
    private boolean H;
    private final a<Path> c = new a<Path>(this) {
        final /* synthetic */ bb a;

        {
            this.a = r1;
        }

        public void a(Path path) {
            this.a.e();
        }
    };
    private final a<Integer> d = new a<Integer>(this) {
        final /* synthetic */ bb a;

        {
            this.a = r1;
        }

        public void a(Integer num) {
            this.a.invalidateSelf();
        }
    };
    private final a<Integer> e = new a<Integer>(this) {
        final /* synthetic */ bb a;

        {
            this.a = r1;
        }

        public void a(Integer num) {
            this.a.f();
        }
    };
    private final a<Float> f = new a<Float>(this) {
        final /* synthetic */ bb a;

        {
            this.a = r1;
        }

        public void a(Float f) {
            this.a.j();
        }
    };
    private final a<Float> g = new a<Float>(this) {
        final /* synthetic */ bb a;

        {
            this.a = r1;
        }

        public void a(Float f) {
            this.a.k();
        }
    };
    private final a<Float> h = new a<Float>(this) {
        final /* synthetic */ bb a;

        {
            this.a = r1;
        }

        public void a(Float f) {
            this.a.g();
        }
    };
    private final a<aw> i = new a<aw>(this) {
        final /* synthetic */ bb a;

        {
            this.a = r1;
        }

        public void a(aw awVar) {
            this.a.g();
        }
    };
    private final Paint j = new Paint(this, 1) {
        final /* synthetic */ bb a;
    };
    private final Path k = new Path();
    private final Path l = new Path();
    private final Path m = new Path();
    private final PathMeasure n = new PathMeasure();
    private float o;
    private float p;
    private float q;
    private float r = 100.0f;
    private float s = 0.0f;
    @Nullable
    private ag<aw> t;
    private final RectF u = new RectF();
    private final Matrix v = new Matrix();
    private n<?, Path> w;
    private n<?, Integer> x;
    private n<?, Float> y;
    @Nullable
    private n<?, Float> z;

    bb(Callback callback) {
        super(callback);
    }

    void d() {
        this.j.setStyle(Style.STROKE);
        invalidateSelf();
    }

    public void c(ag<Integer> agVar) {
        if (this.x != null) {
            b(this.x);
            this.x.b(this.e);
        }
        this.x = agVar;
        a((n) agVar);
        agVar.a(this.e);
        f();
    }

    private void f() {
        this.j.setColor(((Integer) this.x.b()).intValue());
        invalidateSelf();
    }

    public void d(n<?, Path> nVar) {
        if (this.w != null) {
            b(this.w);
            this.w.b(this.c);
        }
        this.w = nVar;
        a((n) nVar);
        nVar.a(this.c);
        e();
    }

    void e() {
        this.l.reset();
        this.l.set((Path) this.w.b());
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.o = Float.NaN;
        this.p = Float.NaN;
        g();
        invalidateSelf();
    }

    private void g() {
        this.G = true;
        invalidateSelf();
    }

    @SuppressLint({"NewApi"})
    public void draw(@NonNull Canvas canvas) {
        if (this.G) {
            h();
        }
        if (this.H) {
            i();
        }
        if (this.j.getStyle() != Style.STROKE || this.j.getStrokeWidth() != 0.0f) {
            this.j.setAlpha(getAlpha());
            canvas.drawPath(this.l, this.j);
            if (!this.m.isEmpty()) {
                canvas.drawPath(this.m, this.j);
            }
        }
    }

    private void h() {
        boolean z;
        this.G = false;
        boolean z2 = (this.z == null || ((Float) this.z.b()).floatValue() == this.q) ? false : true;
        boolean z3;
        if (this.A == null || ((Float) this.A.b()).floatValue() == this.r) {
            z3 = false;
        } else {
            z3 = true;
        }
        boolean z4;
        if (this.B == null || ((Float) this.B.b()).floatValue() == this.s) {
            z4 = false;
        } else {
            z4 = true;
        }
        if (this.t == null || ((aw) this.t.b()).a() == this.o) {
            z = false;
        } else {
            z = true;
        }
        boolean z5;
        if (this.t == null || ((aw) this.t.b()).b() == this.p) {
            z5 = false;
        } else {
            z5 = true;
        }
        if (z2 || r4 || z || r7 || r5) {
            this.l.set((Path) this.w.b());
            if (z || r7) {
                this.l.computeBounds(this.u, false);
                this.o = ((aw) this.t.b()).a();
                this.p = ((aw) this.t.b()).b();
                this.v.setScale(this.o, this.p, this.u.centerX(), this.u.centerY());
                this.l.transform(this.v, this.l);
            }
            if (z2 || r4 || r5) {
                this.k.set(this.l);
                this.n.setPath(this.k, false);
                this.q = ((Float) this.z.b()).floatValue();
                this.r = ((Float) this.A.b()).floatValue();
                float length = this.n.getLength();
                float f = (this.q * length) / 100.0f;
                float f2 = (this.r * length) / 100.0f;
                float min = Math.min(f, f2);
                float max = Math.max(f, f2);
                this.l.reset();
                this.s = (((Float) this.B.b()).floatValue() / 360.0f) * length;
                f2 = min + this.s;
                f = this.s + max;
                float f3;
                if (f2 <= length || f <= length) {
                    f3 = f;
                    f = f2;
                    f2 = f3;
                } else {
                    f3 = f % length;
                    f = f2 % length;
                    f2 = f3;
                }
                if (f > f2) {
                    f -= length;
                }
                this.n.getSegment(f, f2, this.l, true);
                this.m.reset();
                if (f2 > length) {
                    this.n.getSegment(0.0f, f2 % length, this.m, true);
                } else if (f < 0.0f) {
                    this.n.getSegment(f + length, length, this.m, true);
                }
            }
        }
    }

    private void i() {
        float[] fArr = new float[this.E.size()];
        for (int i = 0; i < this.E.size(); i++) {
            fArr[i] = ((Float) ((n) this.E.get(i)).b()).floatValue();
            if (i % 2 == 0) {
                if (fArr[i] < 1.0f) {
                    fArr[i] = 1.0f;
                }
            } else if (fArr[i] < 0.1f) {
                fArr[i] = 0.1f;
            }
        }
        this.j.setPathEffect(new DashPathEffect(fArr, ((Float) this.F.b()).floatValue()));
    }

    public int getAlpha() {
        int i = 255;
        int intValue = this.C == null ? 255 : ((Integer) this.C.b()).intValue();
        if (this.D != null) {
            i = ((Integer) this.D.b()).intValue();
        }
        return (int) (((((float) super.getAlpha()) * (((((float) intValue) / 255.0f) * ((float) i)) / 255.0f)) / 255.0f) * 255.0f);
    }

    void d(ag<Integer> agVar) {
        if (this.C != null) {
            b(this.C);
            this.C.b(this.d);
        }
        this.C = agVar;
        a((n) agVar);
        agVar.a(this.d);
        invalidateSelf();
    }

    void e(ag<Integer> agVar) {
        this.D = agVar;
        a((n) agVar);
        agVar.a(this.d);
        invalidateSelf();
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i) {
        this.j.setAlpha(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return -3;
    }

    void f(ag<Float> agVar) {
        if (this.y != null) {
            b(this.y);
            this.y.b(this.f);
        }
        this.y = agVar;
        a((n) agVar);
        agVar.a(this.f);
        j();
    }

    private void j() {
        this.j.setStrokeWidth(((Float) this.y.b()).floatValue());
        invalidateSelf();
    }

    void a(List<n<?, Float>> list, n<?, Float> nVar) {
        int i = 0;
        if (this.E != null) {
            b((n) this.E.get(0));
            ((n) this.E.get(0)).b(this.g);
            b((n) this.E.get(1));
            ((n) this.E.get(1)).b(this.g);
        }
        if (this.F != null) {
            b(this.F);
            this.F.b(this.g);
        }
        if (!list.isEmpty()) {
            this.E = list;
            this.F = nVar;
            while (i < list.size()) {
                n nVar2 = (n) list.get(i);
                a(nVar2);
                nVar2.a(this.g);
                i++;
            }
            a((n) nVar);
            nVar.a(this.g);
            k();
        }
    }

    private void k() {
        this.H = true;
        invalidateSelf();
    }

    void a(LineCapType lineCapType) {
        switch (lineCapType) {
            case Round:
                this.j.setStrokeCap(Cap.ROUND);
                break;
            default:
                this.j.setStrokeCap(Cap.BUTT);
                break;
        }
        invalidateSelf();
    }

    void a(LineJoinType lineJoinType) {
        switch (lineJoinType) {
            case Bevel:
                this.j.setStrokeJoin(Join.BEVEL);
                return;
            case Miter:
                this.j.setStrokeJoin(Join.MITER);
                return;
            case Round:
                this.j.setStrokeJoin(Join.ROUND);
                return;
            default:
                return;
        }
    }

    void a(ag<Float> agVar, ag<Float> agVar2, ag<Float> agVar3) {
        if (this.z != null) {
            b(this.z);
            this.z.b(this.h);
        }
        if (this.A != null) {
            b(this.A);
            this.A.b(this.h);
        }
        if (this.B != null) {
            b(this.B);
            this.B.b(this.h);
        }
        this.z = agVar;
        this.A = agVar2;
        this.B = agVar3;
        a((n) agVar);
        agVar.a(this.h);
        a((n) agVar2);
        agVar2.a(this.h);
        a((n) agVar3);
        agVar3.a(this.h);
        g();
    }

    void g(ag<aw> agVar) {
        if (this.t != null) {
            b(this.t);
            this.t.b(this.i);
        }
        this.t = agVar;
        a((n) agVar);
        agVar.a(this.i);
        g();
    }
}
