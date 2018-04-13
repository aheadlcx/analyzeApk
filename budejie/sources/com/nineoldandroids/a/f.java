package com.nineoldandroids.a;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Arrays;

class f {
    int a;
    e b;
    e c;
    Interpolator d;
    ArrayList<e> e = new ArrayList();
    j f;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    public f(e... eVarArr) {
        this.a = eVarArr.length;
        this.e.addAll(Arrays.asList(eVarArr));
        this.b = (e) this.e.get(0);
        this.c = (e) this.e.get(this.a - 1);
        this.d = this.c.d();
    }

    public static f a(float... fArr) {
        int i = 1;
        int length = fArr.length;
        a[] aVarArr = new a[Math.max(length, 2)];
        if (length == 1) {
            aVarArr[0] = (a) e.a(0.0f);
            aVarArr[1] = (a) e.a(1.0f, fArr[0]);
        } else {
            aVarArr[0] = (a) e.a(0.0f, fArr[0]);
            while (i < length) {
                aVarArr[i] = (a) e.a(((float) i) / ((float) (length - 1)), fArr[i]);
                i++;
            }
        }
        return new c(aVarArr);
    }

    public void a(j jVar) {
        this.f = jVar;
    }

    public f b() {
        ArrayList arrayList = this.e;
        int size = this.e.size();
        e[] eVarArr = new e[size];
        for (int i = 0; i < size; i++) {
            eVarArr[i] = ((e) arrayList.get(i)).e();
        }
        return new f(eVarArr);
    }

    public Object a(float f) {
        if (this.a == 2) {
            if (this.d != null) {
                f = this.d.getInterpolation(f);
            }
            return this.f.a(f, this.b.b(), this.c.b());
        } else if (f <= 0.0f) {
            r0 = (e) this.e.get(1);
            r1 = r0.d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r1 = this.b.c();
            return this.f.a((f - r1) / (r0.c() - r1), this.b.b(), r0.b());
        } else if (f >= 1.0f) {
            r0 = (e) this.e.get(this.a - 2);
            r1 = this.c.d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r1 = r0.c();
            return this.f.a((f - r1) / (this.c.c() - r1), r0.b(), this.c.b());
        } else {
            e eVar = this.b;
            int i = 1;
            while (i < this.a) {
                r0 = (e) this.e.get(i);
                if (f < r0.c()) {
                    r1 = r0.d();
                    if (r1 != null) {
                        f = r1.getInterpolation(f);
                    }
                    r1 = eVar.c();
                    return this.f.a((f - r1) / (r0.c() - r1), eVar.b(), r0.b());
                }
                i++;
                eVar = r0;
            }
            return this.c.b();
        }
    }

    public String toString() {
        String str = " ";
        int i = 0;
        while (i < this.a) {
            String str2 = str + ((e) this.e.get(i)).b() + "  ";
            i++;
            str = str2;
        }
        return str;
    }
}
