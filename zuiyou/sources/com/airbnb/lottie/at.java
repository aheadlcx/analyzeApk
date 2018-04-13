package com.airbnb.lottie;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

class at extends d {
    @Nullable
    private a c;
    @Nullable
    private a d;

    private static class a extends bb {
        private final a<PointF> c = new a<PointF>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(PointF pointF) {
                this.a.f();
            }
        };
        private final a<Float> d = new a<Float>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(Float f) {
                this.a.f();
            }
        };
        private final a<PointF> e = new a<PointF>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(PointF pointF) {
                this.a.f();
            }
        };
        private final Path f = new Path();
        private final RectF g = new RectF();
        private n<?, Float> h;
        private n<?, PointF> i;
        private n<?, PointF> j;
        private boolean k;

        a(Callback callback) {
            super(callback);
            d(new bg(this.f));
        }

        void a(ag<Float> agVar) {
            if (this.h != null) {
                b(agVar);
                this.h.b(this.d);
            }
            this.h = agVar;
            a((n) agVar);
            agVar.a(this.d);
            f();
        }

        void b(ag<PointF> agVar) {
            if (this.j != null) {
                b(this.j);
                this.j.b(this.c);
            }
            this.j = agVar;
            a((n) agVar);
            agVar.a(this.c);
            f();
        }

        void c(n<?, PointF> nVar) {
            if (this.i != null) {
                b(this.i);
                this.i.b(this.e);
            }
            this.i = nVar;
            a((n) nVar);
            nVar.a(this.e);
            f();
        }

        private void f() {
            this.k = true;
            invalidateSelf();
        }

        private void g() {
            this.f.reset();
            if (this.j != null) {
                PointF pointF = (PointF) this.j.b();
                float f = pointF.x / 2.0f;
                float f2 = pointF.y / 2.0f;
                float floatValue = this.h == null ? 0.0f : ((Float) this.h.b()).floatValue();
                float min = Math.min(f, f2);
                if (floatValue <= min) {
                    min = floatValue;
                }
                pointF = this.i == null ? bi.a() : (PointF) this.i.b();
                this.f.moveTo(pointF.x + f, (pointF.y - f2) + min);
                this.f.lineTo(pointF.x + f, (pointF.y + f2) - min);
                if (min > 0.0f) {
                    this.g.set((pointF.x + f) - (2.0f * min), (pointF.y + f2) - (2.0f * min), pointF.x + f, pointF.y + f2);
                    this.f.arcTo(this.g, 0.0f, 90.0f, false);
                }
                this.f.lineTo((pointF.x - f) + min, pointF.y + f2);
                if (min > 0.0f) {
                    this.g.set(pointF.x - f, (pointF.y + f2) - (2.0f * min), (pointF.x - f) + (2.0f * min), pointF.y + f2);
                    this.f.arcTo(this.g, 90.0f, 90.0f, false);
                }
                this.f.lineTo(pointF.x - f, (pointF.y - f2) + (2.0f * min));
                if (min > 0.0f) {
                    this.g.set(pointF.x - f, pointF.y - f2, (pointF.x - f) + (2.0f * min), (pointF.y - f2) + (2.0f * min));
                    this.f.arcTo(this.g, 180.0f, 90.0f, false);
                }
                this.f.lineTo((pointF.x + f) - (2.0f * min), pointF.y - f2);
                if (min > 0.0f) {
                    this.g.set((pointF.x + f) - (2.0f * min), pointF.y - f2, f + pointF.x, (pointF.y - f2) + (min * 2.0f));
                    this.f.arcTo(this.g, 270.0f, 90.0f, false);
                }
                this.f.close();
                e();
            }
        }

        public void draw(@NonNull Canvas canvas) {
            if (this.k) {
                this.k = false;
                g();
            }
            super.draw(canvas);
        }
    }

    at(au auVar, @Nullable ay ayVar, @Nullable ShapeStroke shapeStroke, @Nullable be beVar, j jVar, Callback callback) {
        super(callback);
        a(jVar.f());
        if (ayVar != null) {
            this.c = new a(getCallback());
            this.c.c(ayVar.a().a());
            this.c.d(ayVar.b().a());
            this.c.e(jVar.e().a());
            this.c.a(auVar.a().a());
            this.c.b(auVar.b().a());
            this.c.c(auVar.c().b());
            if (beVar != null) {
                this.c.a(beVar.b().a(), beVar.a().a(), beVar.c().a());
            }
            a(this.c);
        }
        if (shapeStroke != null) {
            this.d = new a(getCallback());
            this.d.d();
            this.d.c(shapeStroke.a().a());
            this.d.d(shapeStroke.b().a());
            this.d.e(jVar.e().a());
            this.d.f(shapeStroke.c().a());
            if (!shapeStroke.d().isEmpty()) {
                List arrayList = new ArrayList(shapeStroke.d().size());
                for (b a : shapeStroke.d()) {
                    arrayList.add(a.a());
                }
                this.d.a(arrayList, shapeStroke.e().a());
            }
            this.d.a(shapeStroke.f());
            this.d.a(auVar.a().a());
            this.d.b(auVar.b().a());
            this.d.c(auVar.c().b());
            this.d.a(shapeStroke.g());
            if (beVar != null) {
                this.d.a(beVar.b().a(), beVar.a().a(), beVar.c().a());
            }
            a(this.d);
        }
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        if (this.c != null) {
            this.c.setAlpha(i);
        }
        if (this.d != null) {
            this.d.setAlpha(i);
        }
    }
}
