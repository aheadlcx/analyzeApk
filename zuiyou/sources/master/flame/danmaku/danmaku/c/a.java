package master.flame.danmaku.danmaku.c;

import android.text.TextUtils;
import master.flame.danmaku.danmaku.model.android.f;
import master.flame.danmaku.danmaku.model.android.g;
import master.flame.danmaku.danmaku.model.b;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.m;

public class a {
    public static boolean a(m mVar, d dVar, d dVar2, long j, long j2) {
        int o = dVar.o();
        if (o != dVar2.o() || dVar.g()) {
            return false;
        }
        long s = dVar2.s() - dVar.s();
        if (s <= 0) {
            return true;
        }
        if (Math.abs(s) >= j || dVar.f() || dVar2.f()) {
            return false;
        }
        if (o == 5 || o == 4) {
            return true;
        }
        if (a(mVar, dVar, dVar2, j2) || a(mVar, dVar, dVar2, dVar.s() + dVar.a())) {
            return true;
        }
        return false;
    }

    private static boolean a(m mVar, d dVar, d dVar2, long j) {
        float[] a = dVar.a(mVar, j);
        float[] a2 = dVar2.a(mVar, j);
        if (a == null || a2 == null) {
            return false;
        }
        return a(dVar.o(), dVar2.o(), a, a2);
    }

    private static boolean a(int i, int i2, float[] fArr, float[] fArr2) {
        boolean z = true;
        if (i != i2) {
            return false;
        }
        if (i == 1) {
            if (fArr2[0] >= fArr[2]) {
                z = false;
            }
            return z;
        } else if (i != 6) {
            return false;
        } else {
            if (fArr2[2] <= fArr[0]) {
                z = false;
            }
            return z;
        }
    }

    public static f a(d dVar, m mVar, f fVar, int i) {
        f fVar2;
        if (fVar == null) {
            fVar2 = new f();
        } else {
            fVar2 = fVar;
        }
        fVar2.a((int) Math.ceil((double) dVar.p), (int) Math.ceil((double) dVar.q), mVar.h(), false, i);
        g h = fVar2.h();
        if (h != null) {
            ((b) mVar).a(dVar, h.a, 0.0f, 0.0f, true);
            if (mVar.b()) {
                h.a(mVar.e(), mVar.f(), mVar.k(), mVar.l());
            }
        }
        return fVar2;
    }

    public static int a(int i, int i2, int i3) {
        return (i * i2) * i3;
    }

    public static final boolean a(d dVar, d dVar2) {
        if (dVar == dVar2) {
            return false;
        }
        if (dVar.c == dVar2.c) {
            return true;
        }
        if (dVar.c == null || !dVar.c.equals(dVar2.c)) {
            return false;
        }
        return true;
    }

    public static final int b(d dVar, d dVar2) {
        if (dVar == dVar2) {
            return 0;
        }
        if (dVar == null) {
            return -1;
        }
        if (dVar2 == null) {
            return 1;
        }
        long r = dVar.r() - dVar2.r();
        if (r > 0) {
            return 1;
        }
        if (r < 0) {
            return -1;
        }
        int o = dVar.o() - dVar2.o();
        if (o > 0) {
            return 1;
        }
        if (o < 0 || dVar.c == null) {
            return -1;
        }
        if (dVar2.c == null) {
            return 1;
        }
        o = dVar.c.toString().compareTo(dVar2.c.toString());
        if (o != 0) {
            return o;
        }
        o = dVar.g - dVar2.g;
        if (o == 0) {
            o = dVar.s - dVar2.s;
            if (o == 0) {
                return dVar.hashCode() - dVar.hashCode();
            }
            if (o >= 0) {
                return 1;
            }
            return -1;
        } else if (o >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void a(d dVar, CharSequence charSequence) {
        dVar.c = charSequence;
        if (!TextUtils.isEmpty(charSequence) && charSequence.toString().contains("/n")) {
            String[] split = String.valueOf(dVar.c).split("/n", -1);
            if (split.length > 1) {
                dVar.d = split;
            }
        }
    }
}
