package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.Drawable.Callback;
import java.util.ArrayList;
import java.util.List;

class ar extends d {

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
        private final a<Float> d = new a<Float>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(Float f) {
                this.a.f();
            }
        };
        private final Path e = new Path();
        private Type f;
        private n<?, Float> g;
        private n<?, PointF> h;
        private n<?, Float> i;
        private n<?, Float> j;
        private n<?, Float> k;
        private n<?, Float> l;
        private n<?, Float> m;

        a(Callback callback) {
            super(callback);
            d(new bg(this.e));
        }

        void a(PolystarShape polystarShape) {
            this.f = polystarShape.a();
            if (this.g != null) {
                b(this.g);
            }
            if (this.h != null) {
                b(this.h);
            }
            if (this.i != null) {
                b(this.i);
            }
            if (this.j != null) {
                b(this.j);
            }
            if (this.k != null) {
                b(this.k);
            }
            if (this.l != null) {
                b(this.l);
            }
            if (this.m != null) {
                b(this.m);
            }
            this.g = polystarShape.b().a();
            this.h = polystarShape.c().b();
            this.i = polystarShape.d().a();
            this.j = polystarShape.f().a();
            this.k = polystarShape.h().a();
            if (polystarShape.e() != null) {
                this.l = polystarShape.e().a();
            }
            if (polystarShape.g() != null) {
                this.m = polystarShape.g().a();
            }
            this.g.a(this.d);
            this.h.a(this.c);
            this.i.a(this.d);
            this.j.a(this.d);
            this.k.a(this.d);
            if (this.l != null) {
                this.l.a(this.d);
            }
            if (this.m != null) {
                this.m.a(this.d);
            }
            a(this.g);
            a(this.h);
            a(this.i);
            a(this.j);
            a(this.k);
            if (this.l != null) {
                a(this.l);
            }
            if (this.m != null) {
                a(this.m);
            }
            f();
        }

        private void f() {
            switch (this.f) {
                case Star:
                    g();
                    break;
                case Polygon:
                    h();
                    break;
            }
            e();
        }

        private void g() {
            double d;
            double d2;
            float floatValue;
            float floatValue2;
            float f;
            float cos;
            float sin;
            float f2;
            float floatValue3 = ((Float) this.g.b()).floatValue();
            if (this.i == null) {
                d = 0.0d;
            } else {
                d = (double) ((Float) this.i.b()).floatValue();
            }
            d = Math.toRadians(d - 90.0d);
            float f3 = (float) (6.283185307179586d / ((double) floatValue3));
            float f4 = f3 / 2.0f;
            float f5 = floatValue3 - ((float) ((int) floatValue3));
            if (f5 != 0.0f) {
                d2 = d + ((double) ((1.0f - f5) * f4));
            } else {
                d2 = d;
            }
            float floatValue4 = ((Float) this.j.b()).floatValue();
            float floatValue5 = ((Float) this.l.b()).floatValue();
            if (this.m != null) {
                floatValue = ((Float) this.m.b()).floatValue() / 100.0f;
            } else {
                floatValue = 0.0f;
            }
            if (this.k != null) {
                floatValue2 = ((Float) this.k.b()).floatValue() / 100.0f;
            } else {
                floatValue2 = 0.0f;
            }
            this.e.reset();
            if (f5 != 0.0f) {
                f = ((floatValue4 - floatValue5) * f5) + floatValue5;
                cos = (float) (((double) f) * Math.cos(d2));
                sin = (float) (((double) f) * Math.sin(d2));
                this.e.moveTo(cos, sin);
                d2 += (double) ((f3 * f5) / 2.0f);
                f2 = f;
                f = sin;
                sin = cos;
            } else {
                cos = (float) (((double) floatValue4) * Math.cos(d2));
                sin = (float) (((double) floatValue4) * Math.sin(d2));
                this.e.moveTo(cos, sin);
                d2 += (double) f4;
                f2 = 0.0f;
                f = sin;
                sin = cos;
            }
            double ceil = Math.ceil((double) floatValue3) * 2.0d;
            int i = 0;
            Object obj = null;
            float f6 = f;
            float f7 = sin;
            double d3 = d2;
            while (((double) i) < ceil) {
                float f8;
                Object obj2;
                if (obj != null) {
                    f = floatValue4;
                } else {
                    f = floatValue5;
                }
                if (f2 == 0.0f || ((double) i) != ceil - 2.0d) {
                    f8 = f4;
                } else {
                    f8 = (f3 * f5) / 2.0f;
                }
                if (f2 != 0.0f && ((double) i) == ceil - 1.0d) {
                    f = f2;
                }
                float cos2 = (float) (((double) f) * Math.cos(d3));
                floatValue3 = (float) (((double) f) * Math.sin(d3));
                if (floatValue == 0.0f && floatValue2 == 0.0f) {
                    this.e.lineTo(cos2, floatValue3);
                } else {
                    float f9;
                    f = (float) (Math.atan2((double) f6, (double) f7) - 1.5707963267948966d);
                    float cos3 = (float) Math.cos((double) f);
                    float sin2 = (float) Math.sin((double) f);
                    f = (float) (Math.atan2((double) floatValue3, (double) cos2) - 1.5707963267948966d);
                    float cos4 = (float) Math.cos((double) f);
                    float sin3 = (float) Math.sin((double) f);
                    float f10 = obj != null ? floatValue : floatValue2;
                    if (obj != null) {
                        cos = floatValue2;
                    } else {
                        cos = floatValue;
                    }
                    if (obj != null) {
                        sin = floatValue5;
                    } else {
                        sin = floatValue4;
                    }
                    if (obj != null) {
                        f = floatValue4;
                    } else {
                        f = floatValue5;
                    }
                    cos3 *= (sin * f10) * 0.47829f;
                    f10 = ((sin * f10) * 0.47829f) * sin2;
                    sin = ((f * cos) * 0.47829f) * cos4;
                    f = ((f * cos) * 0.47829f) * sin3;
                    if (f5 != 0.0f) {
                        if (i == 0) {
                            f10 *= f5;
                            cos = f;
                            f9 = sin;
                            sin = cos3 * f5;
                            cos3 = f9;
                        } else if (((double) i) == ceil - 1.0d) {
                            cos = f * f5;
                            f9 = sin * f5;
                            sin = cos3;
                            cos3 = f9;
                        }
                        this.e.cubicTo(f7 - sin, f6 - f10, cos3 + cos2, cos + floatValue3, cos2, floatValue3);
                    }
                    cos = f;
                    f9 = sin;
                    sin = cos3;
                    cos3 = f9;
                    this.e.cubicTo(f7 - sin, f6 - f10, cos3 + cos2, cos + floatValue3, cos2, floatValue3);
                }
                d2 = d3 + ((double) f8);
                if (obj == null) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                i++;
                obj = obj2;
                f6 = floatValue3;
                f7 = cos2;
                d3 = d2;
            }
            PointF pointF = (PointF) this.h.b();
            this.e.offset(pointF.x, pointF.y);
            this.e.close();
        }

        private void h() {
            double d;
            int floor = (int) Math.floor((double) ((Float) this.g.b()).floatValue());
            if (this.i == null) {
                d = 0.0d;
            } else {
                d = (double) ((Float) this.i.b()).floatValue();
            }
            double toRadians = Math.toRadians(d - 90.0d);
            float f = (float) (6.283185307179586d / ((double) floor));
            this.e.reset();
            float floatValue = ((Float) this.k.b()).floatValue() / 100.0f;
            float floatValue2 = ((Float) this.j.b()).floatValue();
            float cos = (float) (((double) floatValue2) * Math.cos(toRadians));
            float sin = (float) (((double) floatValue2) * Math.sin(toRadians));
            this.e.moveTo(cos, sin);
            toRadians += (double) f;
            double ceil = Math.ceil((double) floor);
            int i = 0;
            float f2 = cos;
            double d2 = toRadians;
            float f3 = sin;
            while (((double) i) < ceil) {
                cos = (float) (((double) floatValue2) * Math.cos(d2));
                sin = (float) (((double) floatValue2) * Math.sin(d2));
                if (floatValue != 0.0f) {
                    float atan2 = (float) (Math.atan2((double) f3, (double) f2) - 1.5707963267948966d);
                    float cos2 = (float) Math.cos((double) atan2);
                    float atan22 = (float) (Math.atan2((double) sin, (double) cos) - 1.5707963267948966d);
                    this.e.cubicTo(f2 - (cos2 * ((floatValue2 * floatValue) * 0.25f)), f3 - (((floatValue2 * floatValue) * 0.25f) * ((float) Math.sin((double) atan2))), cos + (((float) Math.cos((double) atan22)) * ((floatValue2 * floatValue) * 0.25f)), (((float) Math.sin((double) atan22)) * ((floatValue2 * floatValue) * 0.25f)) + sin, cos, sin);
                } else {
                    this.e.lineTo(cos, sin);
                }
                i++;
                f2 = cos;
                d2 += (double) f;
                f3 = sin;
            }
            PointF pointF = (PointF) this.h.b();
            this.e.offset(pointF.x, pointF.y);
            this.e.close();
        }
    }

    ar(PolystarShape polystarShape, ay ayVar, ShapeStroke shapeStroke, be beVar, j jVar, Callback callback) {
        super(callback);
        a(jVar.f());
        if (ayVar != null) {
            d aVar = new a(getCallback());
            aVar.c(ayVar.a().a());
            aVar.e(jVar.e().a());
            aVar.d(ayVar.b().a());
            aVar.a(polystarShape);
            if (beVar != null) {
                aVar.a(beVar.b().a(), beVar.a().a(), beVar.c().a());
            }
            a(aVar);
        }
        if (shapeStroke != null) {
            d aVar2 = new a(getCallback());
            aVar2.d();
            aVar2.c(shapeStroke.a().a());
            aVar2.e(jVar.e().a());
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
            aVar2.a(polystarShape);
            if (beVar != null) {
                aVar2.a(beVar.b().a(), beVar.a().a(), beVar.c().a());
            }
            a(aVar2);
        }
    }
}
