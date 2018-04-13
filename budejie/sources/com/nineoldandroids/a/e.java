package com.nineoldandroids.a;

import android.view.animation.Interpolator;

public abstract class e implements Cloneable {
    float a;
    Class b;
    boolean c = false;
    private Interpolator d = null;

    static class a extends e {
        float d;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return g();
        }

        public /* synthetic */ e e() {
            return g();
        }

        a(float f, float f2) {
            this.a = f;
            this.d = f2;
            this.b = Float.TYPE;
            this.c = true;
        }

        a(float f) {
            this.a = f;
            this.b = Float.TYPE;
        }

        public float f() {
            return this.d;
        }

        public Object b() {
            return Float.valueOf(this.d);
        }

        public void a(Object obj) {
            if (obj != null && obj.getClass() == Float.class) {
                this.d = ((Float) obj).floatValue();
                this.c = true;
            }
        }

        public a g() {
            a aVar = new a(c(), this.d);
            aVar.a(d());
            return aVar;
        }
    }

    public abstract void a(Object obj);

    public abstract Object b();

    public abstract e e();

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return e();
    }

    public static e a(float f, float f2) {
        return new a(f, f2);
    }

    public static e a(float f) {
        return new a(f);
    }

    public boolean a() {
        return this.c;
    }

    public float c() {
        return this.a;
    }

    public Interpolator d() {
        return this.d;
    }

    public void a(Interpolator interpolator) {
        this.d = interpolator;
    }
}
