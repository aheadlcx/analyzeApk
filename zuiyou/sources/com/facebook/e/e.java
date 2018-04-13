package com.facebook.e;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class e {
    private static int a = 0;
    private f b;
    private boolean c;
    private final String d;
    private final a e = new a();
    private final a f = new a();
    private final a g = new a();
    private double h;
    private double i;
    private boolean j = true;
    private double k = 0.005d;
    private double l = 0.005d;
    private CopyOnWriteArraySet<g> m = new CopyOnWriteArraySet();
    private double n = 0.0d;
    private final b o;

    private static class a {
        double a;
        double b;

        private a() {
        }
    }

    e(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Spring cannot be created outside of a BaseSpringSystem");
        }
        this.o = bVar;
        StringBuilder append = new StringBuilder().append("spring:");
        int i = a;
        a = i + 1;
        this.d = append.append(i).toString();
        a(f.c);
    }

    public String a() {
        return this.d;
    }

    public e a(f fVar) {
        if (fVar == null) {
            throw new IllegalArgumentException("springConfig is required");
        }
        this.b = fVar;
        return this;
    }

    public double b() {
        return this.e.a;
    }

    private double a(a aVar) {
        return Math.abs(this.i - aVar.a);
    }

    public e a(double d) {
        if (!(this.i == d && f())) {
            this.h = b();
            this.i = d;
            this.o.a(a());
            Iterator it = this.m.iterator();
            while (it.hasNext()) {
                ((g) it.next()).d(this);
            }
        }
        return this;
    }

    public e b(double d) {
        if (d != this.e.b) {
            this.e.b = d;
            this.o.a(a());
        }
        return this;
    }

    public boolean c() {
        return this.b.b > 0.0d && ((this.h < this.i && b() > this.i) || (this.h > this.i && b() < this.i));
    }

    void c(double d) {
        boolean f = f();
        if (!f || !this.j) {
            boolean z;
            Object obj;
            if (d > 0.064d) {
                d = 0.064d;
            }
            this.n += d;
            double d2 = this.b.b;
            double d3 = this.b.a;
            double d4 = this.e.a;
            double d5 = this.e.b;
            double d6 = this.g.a;
            double d7 = this.g.b;
            while (this.n >= 0.001d) {
                this.n -= 0.001d;
                if (this.n < 0.001d) {
                    this.f.a = d4;
                    this.f.b = d5;
                }
                double d8 = ((this.i - d6) * d2) - (d3 * d5);
                double d9 = d5 + ((0.001d * d8) * 0.5d);
                double d10 = ((this.i - (((0.001d * d5) * 0.5d) + d4)) * d2) - (d3 * d9);
                double d11 = d5 + ((0.001d * d10) * 0.5d);
                double d12 = ((this.i - (((0.001d * d9) * 0.5d) + d4)) * d2) - (d3 * d11);
                d6 = d4 + (0.001d * d11);
                d7 = (0.001d * d12) + d5;
                d4 += (((((d9 + d11) * 2.0d) + d5) + d7) * 0.16666666666666666d) * 0.001d;
                d5 += (((d8 + ((d10 + d12) * 2.0d)) + (((this.i - d6) * d2) - (d3 * d7))) * 0.16666666666666666d) * 0.001d;
            }
            this.g.a = d6;
            this.g.b = d7;
            this.e.a = d4;
            this.e.b = d5;
            if (this.n > 0.0d) {
                d(this.n / 0.001d);
            }
            if (f() || (this.c && c())) {
                if (d2 > 0.0d) {
                    this.h = this.i;
                    this.e.a = this.i;
                } else {
                    this.i = this.e.a;
                    this.h = this.i;
                }
                b(0.0d);
                z = true;
            } else {
                z = f;
            }
            if (this.j) {
                this.j = false;
                obj = 1;
            } else {
                obj = null;
            }
            Object obj2 = null;
            if (z) {
                this.j = true;
                obj2 = 1;
            }
            Iterator it = this.m.iterator();
            while (it.hasNext()) {
                g gVar = (g) it.next();
                if (obj != null) {
                    gVar.c(this);
                }
                gVar.a(this);
                if (obj2 != null) {
                    gVar.b(this);
                }
            }
        }
    }

    public boolean d() {
        return (f() && e()) ? false : true;
    }

    public boolean e() {
        return this.j;
    }

    public boolean f() {
        return Math.abs(this.e.b) <= this.k && (a(this.e) <= this.l || this.b.b == 0.0d);
    }

    private void d(double d) {
        this.e.a = (this.e.a * d) + (this.f.a * (1.0d - d));
        this.e.b = (this.e.b * d) + (this.f.b * (1.0d - d));
    }

    public e a(g gVar) {
        if (gVar == null) {
            throw new IllegalArgumentException("newListener is required");
        }
        this.m.add(gVar);
        return this;
    }
}
