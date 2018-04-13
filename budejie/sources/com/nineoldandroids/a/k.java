package com.nineoldandroids.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashMap;

public class k extends a {
    private static ThreadLocal<a> h = new ThreadLocal();
    private static final ThreadLocal<ArrayList<k>> i = new ThreadLocal<ArrayList<k>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<k> a() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<k>> j = new ThreadLocal<ArrayList<k>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<k> a() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<k>> k = new ThreadLocal<ArrayList<k>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<k> a() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<k>> l = new ThreadLocal<ArrayList<k>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<k> a() {
            return new ArrayList();
        }
    };
    private static final ThreadLocal<ArrayList<k>> m = new ThreadLocal<ArrayList<k>>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected ArrayList<k> a() {
            return new ArrayList();
        }
    };
    private static final Interpolator n = new AccelerateDecelerateInterpolator();
    private static final j o = new d();
    private static final j p = new b();
    private static long z = 10;
    private int A = 0;
    private int B = 1;
    private Interpolator C = n;
    private ArrayList<b> D = null;
    long b;
    long c = -1;
    int d = 0;
    boolean e = false;
    i[] f;
    HashMap<String, i> g;
    private boolean q = false;
    private int r = 0;
    private float s = 0.0f;
    private boolean t = false;
    private long u;
    private boolean v = false;
    private boolean w = false;
    private long x = 300;
    private long y = 0;

    private static class a extends Handler {
        private a() {
        }

        public void handleMessage(Message message) {
            ArrayList arrayList;
            Object obj;
            ArrayList arrayList2;
            int size;
            int i;
            k kVar;
            ArrayList arrayList3 = (ArrayList) k.i.get();
            ArrayList arrayList4 = (ArrayList) k.k.get();
            switch (message.what) {
                case 0:
                    arrayList = (ArrayList) k.j.get();
                    if (arrayList3.size() > 0 || arrayList4.size() > 0) {
                        obj = null;
                    } else {
                        int i2 = 1;
                    }
                    while (arrayList.size() > 0) {
                        arrayList2 = (ArrayList) arrayList.clone();
                        arrayList.clear();
                        size = arrayList2.size();
                        for (i = 0; i < size; i++) {
                            kVar = (k) arrayList2.get(i);
                            if (kVar.y == 0) {
                                kVar.m();
                            } else {
                                arrayList4.add(kVar);
                            }
                        }
                    }
                    break;
                case 1:
                    obj = 1;
                    break;
                default:
                    return;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            arrayList = (ArrayList) k.m.get();
            arrayList2 = (ArrayList) k.l.get();
            size = arrayList4.size();
            for (i = 0; i < size; i++) {
                kVar = (k) arrayList4.get(i);
                if (kVar.a(currentAnimationTimeMillis)) {
                    arrayList.add(kVar);
                }
            }
            size = arrayList.size();
            if (size > 0) {
                for (i = 0; i < size; i++) {
                    kVar = (k) arrayList.get(i);
                    kVar.m();
                    kVar.v = true;
                    arrayList4.remove(kVar);
                }
                arrayList.clear();
            }
            i = arrayList3.size();
            int i3 = 0;
            while (i3 < i) {
                int i4;
                k kVar2 = (k) arrayList3.get(i3);
                if (kVar2.d(currentAnimationTimeMillis)) {
                    arrayList2.add(kVar2);
                }
                if (arrayList3.size() == i) {
                    i4 = i3 + 1;
                    i3 = i;
                } else {
                    i--;
                    arrayList2.remove(kVar2);
                    i4 = i3;
                    i3 = i;
                }
                i = i3;
                i3 = i4;
            }
            if (arrayList2.size() > 0) {
                for (i3 = 0; i3 < arrayList2.size(); i3++) {
                    ((k) arrayList2.get(i3)).d();
                }
                arrayList2.clear();
            }
            if (obj == null) {
                return;
            }
            if (!arrayList3.isEmpty() || !arrayList4.isEmpty()) {
                sendEmptyMessageDelayed(1, Math.max(0, k.z - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
            }
        }
    }

    public interface b {
        void a(k kVar);
    }

    public /* synthetic */ a b() {
        return e();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return e();
    }

    public void a(float... fArr) {
        if (fArr != null && fArr.length != 0) {
            if (this.f == null || this.f.length == 0) {
                a(i.a("", fArr));
            } else {
                this.f[0].a(fArr);
            }
            this.e = false;
        }
    }

    public void a(i... iVarArr) {
        this.f = iVarArr;
        this.g = new HashMap(r2);
        for (i iVar : iVarArr) {
            this.g.put(iVar.c(), iVar);
        }
        this.e = false;
    }

    void c() {
        if (!this.e) {
            for (i b : this.f) {
                b.b();
            }
            this.e = true;
        }
    }

    public k b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.x = j;
        return this;
    }

    public void c(long j) {
        c();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.d != 1) {
            this.c = j;
            this.d = 2;
        }
        this.b = currentAnimationTimeMillis - j;
        d(currentAnimationTimeMillis);
    }

    public long f() {
        if (!this.e || this.d == 0) {
            return 0;
        }
        return AnimationUtils.currentAnimationTimeMillis() - this.b;
    }

    public void a(Interpolator interpolator) {
        if (interpolator != null) {
            this.C = interpolator;
        } else {
            this.C = new LinearInterpolator();
        }
    }

    private void a(boolean z) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.q = z;
        this.r = 0;
        this.d = 0;
        this.w = true;
        this.t = false;
        ((ArrayList) j.get()).add(this);
        if (this.y == 0) {
            c(f());
            this.d = 0;
            this.v = true;
            if (this.a != null) {
                ArrayList arrayList = (ArrayList) this.a.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((com.nineoldandroids.a.a.a) arrayList.get(i)).c(this);
                }
            }
        }
        a aVar = (a) h.get();
        if (aVar == null) {
            aVar = new a();
            h.set(aVar);
        }
        aVar.sendEmptyMessage(0);
    }

    public void a() {
        a(false);
    }

    private void d() {
        ((ArrayList) i.get()).remove(this);
        ((ArrayList) j.get()).remove(this);
        ((ArrayList) k.get()).remove(this);
        this.d = 0;
        if (this.v && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((com.nineoldandroids.a.a.a) arrayList.get(i)).a(this);
            }
        }
        this.v = false;
        this.w = false;
    }

    private void m() {
        c();
        ((ArrayList) i.get()).add(this);
        if (this.y > 0 && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((com.nineoldandroids.a.a.a) arrayList.get(i)).c(this);
            }
        }
    }

    private boolean a(long j) {
        if (this.t) {
            long j2 = j - this.u;
            if (j2 > this.y) {
                this.b = j - (j2 - this.y);
                this.d = 1;
                return true;
            }
        }
        this.t = true;
        this.u = j;
        return false;
    }

    boolean d(long j) {
        boolean z = false;
        if (this.d == 0) {
            this.d = 1;
            if (this.c < 0) {
                this.b = j;
            } else {
                this.b = j - this.c;
                this.c = -1;
            }
        }
        switch (this.d) {
            case 1:
            case 2:
                float f;
                float f2 = this.x > 0 ? ((float) (j - this.b)) / ((float) this.x) : 1.0f;
                if (f2 < 1.0f) {
                    f = f2;
                } else if (this.r < this.A || this.A == -1) {
                    if (this.a != null) {
                        int size = this.a.size();
                        for (int i = 0; i < size; i++) {
                            ((com.nineoldandroids.a.a.a) this.a.get(i)).b(this);
                        }
                    }
                    if (this.B == 2) {
                        this.q = !this.q;
                    }
                    this.r += (int) f2;
                    f = f2 % 1.0f;
                    this.b += this.x;
                } else {
                    f = Math.min(f2, 1.0f);
                    z = true;
                }
                if (this.q) {
                    f = 1.0f - f;
                }
                a(f);
                break;
        }
        return z;
    }

    void a(float f) {
        int i;
        float interpolation = this.C.getInterpolation(f);
        this.s = interpolation;
        for (i a : this.f) {
            a.a(interpolation);
        }
        if (this.D != null) {
            int size = this.D.size();
            for (i = 0; i < size; i++) {
                ((b) this.D.get(i)).a(this);
            }
        }
    }

    public k e() {
        int i = 0;
        k kVar = (k) super.b();
        if (this.D != null) {
            ArrayList arrayList = this.D;
            kVar.D = new ArrayList();
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                kVar.D.add(arrayList.get(i2));
            }
        }
        kVar.c = -1;
        kVar.q = false;
        kVar.r = 0;
        kVar.e = false;
        kVar.d = 0;
        kVar.t = false;
        i[] iVarArr = this.f;
        if (iVarArr != null) {
            int length = iVarArr.length;
            kVar.f = new i[length];
            kVar.g = new HashMap(length);
            while (i < length) {
                i a = iVarArr[i].a();
                kVar.f[i] = a;
                kVar.g.put(a.c(), a);
                i++;
            }
        }
        return kVar;
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.f != null) {
            for (i iVar : this.f) {
                str = str + "\n    " + iVar.toString();
            }
        }
        return str;
    }
}
