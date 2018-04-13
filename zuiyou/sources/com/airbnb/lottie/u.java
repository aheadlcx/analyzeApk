package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.Drawable.Callback;
import java.util.ArrayList;
import java.util.List;

class u extends d {

    private static final class a extends bb {
        private final a<PointF> c = new a<PointF>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(PointF pointF) {
                this.a.f();
            }
        };
        private final a<PointF> d = new a<PointF>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(PointF pointF) {
                this.a.invalidateSelf();
            }
        };
        private final Path e = new Path();
        private n<?, PointF> f;
        private n<?, PointF> g;

        a(Callback callback) {
            super(callback);
            d(new bg(this.e));
        }

        void a(n<?, PointF> nVar, n<?, PointF> nVar2) {
            if (this.f != null) {
                b(this.f);
                this.f.b(this.c);
            }
            if (this.g != null) {
                b(this.g);
                this.g.b(this.d);
            }
            this.f = nVar2;
            this.g = nVar;
            a((n) nVar2);
            nVar2.a(this.c);
            a((n) nVar);
            nVar.a(this.d);
            f();
        }

        private void f() {
            float f = ((PointF) this.f.b()).x / 2.0f;
            float f2 = ((PointF) this.f.b()).y / 2.0f;
            setBounds(0, 0, ((int) f) * 2, ((int) f2) * 2);
            float f3 = f * 0.55228f;
            float f4 = f2 * 0.55228f;
            this.e.reset();
            this.e.moveTo(0.0f, -f2);
            this.e.cubicTo(0.0f + f3, -f2, f, 0.0f - f4, f, 0.0f);
            this.e.cubicTo(f, 0.0f + f4, 0.0f + f3, f2, 0.0f, f2);
            this.e.cubicTo(0.0f - f3, f2, -f, 0.0f + f4, -f, 0.0f);
            this.e.cubicTo(-f, 0.0f - f4, 0.0f - f3, -f2, 0.0f, -f2);
            this.e.offset(((PointF) this.g.b()).x, ((PointF) this.g.b()).y);
            this.e.close();
            e();
        }
    }

    u(p pVar, ay ayVar, ShapeStroke shapeStroke, be beVar, j jVar, Callback callback) {
        super(callback);
        a(jVar.f());
        if (ayVar != null) {
            d aVar = new a(getCallback());
            aVar.c(ayVar.a().a());
            aVar.e(jVar.e().a());
            aVar.d(ayVar.b().a());
            aVar.a(pVar.a().b(), pVar.b().a());
            if (beVar != null) {
                aVar.a(beVar.b().a(), beVar.a().a(), beVar.c().a());
            }
            a(aVar);
        }
        if (shapeStroke != null) {
            d aVar2 = new a(getCallback());
            aVar2.d();
            aVar2.c(shapeStroke.a().a());
            aVar2.e(shapeStroke.b().a());
            aVar2.d(shapeStroke.b().a());
            aVar2.f(shapeStroke.c().a());
            if (!shapeStroke.d().isEmpty()) {
                List arrayList = new ArrayList(shapeStroke.d().size());
                for (b a : shapeStroke.d()) {
                    arrayList.add(a.a());
                }
                aVar2.a(arrayList, shapeStroke.e().a());
            }
            aVar2.a(shapeStroke.f());
            aVar2.a(pVar.a().b(), pVar.b().a());
            if (beVar != null) {
                aVar2.a(beVar.b().a(), beVar.a().a(), beVar.c().a());
            }
            a(aVar2);
        }
    }
}
