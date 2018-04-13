package com.budejie.www.activity.video.barrage.danmaku.c;

import android.text.TextUtils;
import com.budejie.www.activity.video.barrage.danmaku.model.a;
import com.budejie.www.activity.video.barrage.danmaku.model.android.d;
import com.budejie.www.activity.video.barrage.danmaku.model.android.e;
import com.budejie.www.activity.video.barrage.danmaku.model.c;
import com.budejie.www.activity.video.barrage.danmaku.model.l;

public class b {
    public static boolean a(l lVar, c cVar, c cVar2, long j, long j2) {
        int n = cVar.n();
        if (n != cVar2.n() || cVar.f()) {
            return false;
        }
        long j3 = cVar2.b - cVar.b;
        if (j3 < 0) {
            return true;
        }
        if (Math.abs(j3) >= j || cVar.e() || cVar2.e()) {
            return false;
        }
        if (n == 5 || n == 4) {
            return true;
        }
        if (a(lVar, cVar, cVar2, j2) || a(lVar, cVar, cVar2, cVar.b + cVar.a())) {
            return true;
        }
        return false;
    }

    private static boolean a(l lVar, c cVar, c cVar2, long j) {
        float[] a = cVar.a(lVar, j);
        float[] a2 = cVar2.a(lVar, j);
        if (a == null || a2 == null) {
            return false;
        }
        return a(cVar.n(), cVar2.n(), a, a2);
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

    public static d a(c cVar, l lVar, d dVar) {
        if (dVar == null) {
            dVar = new d();
        }
        dVar.a((int) Math.ceil((double) cVar.n), (int) Math.ceil((double) cVar.o), lVar.g(), false);
        e h = dVar.h();
        if (h != null) {
            ((a) lVar).a(cVar, h.a, 0.0f, 0.0f, false);
            if (lVar.b()) {
                h.a(lVar.d(), lVar.e(), lVar.j(), lVar.k());
            }
        }
        return dVar;
    }

    public static int a(int i, int i2) {
        return (i * i2) * 4;
    }

    public static final boolean a(c cVar, c cVar2) {
        if (cVar == cVar2) {
            return false;
        }
        if (cVar.c == cVar2.c) {
            return true;
        }
        if (cVar.c == null || !cVar.c.equals(cVar2.c)) {
            return false;
        }
        return true;
    }

    public static final int b(c cVar, c cVar2) {
        if (cVar == cVar2) {
            return 0;
        }
        if (cVar == null) {
            return -1;
        }
        if (cVar2 == null) {
            return 1;
        }
        long j = cVar.b - cVar2.b;
        if (j > 0) {
            return 1;
        }
        if (j < 0) {
            return -1;
        }
        int i = cVar.q - cVar2.q;
        if (i > 0) {
            return 1;
        }
        if (i < 0) {
            return -1;
        }
        i = cVar.n() - cVar2.n();
        if (i > 0) {
            return 1;
        }
        if (i < 0 || cVar.c == null) {
            return -1;
        }
        if (cVar2.c == null) {
            return 1;
        }
        i = String.valueOf(cVar.c).compareTo(String.valueOf(cVar2.c));
        if (i != 0) {
            return i;
        }
        i = cVar.e - cVar2.e;
        if (i == 0) {
            i = cVar.q - cVar2.q;
            if (i == 0) {
                return cVar.hashCode() - cVar.hashCode();
            }
            if (i >= 0) {
                return 1;
            }
            return -1;
        } else if (i >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void a(c cVar, String str) {
        cVar.c = str;
        if (!TextUtils.isEmpty(str) && str.contains("/n")) {
            String[] split = String.valueOf(cVar.c).split("/n", -1);
            if (split.length > 1) {
                cVar.d = split;
            }
        }
    }
}
