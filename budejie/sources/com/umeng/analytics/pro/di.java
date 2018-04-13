package com.umeng.analytics.pro;

import com.umeng.analytics.pro.cz.a;

public class di {
    private static int a = Integer.MAX_VALUE;

    public static void a(int i) {
        a = i;
    }

    public static void a(df dfVar, byte b) throws cm {
        a(dfVar, b, a);
    }

    public static void a(df dfVar, byte b, int i) throws cm {
        int i2 = 0;
        if (i <= 0) {
            throw new cm("Maximum skip depth exceeded");
        }
        switch (b) {
            case (byte) 2:
                dfVar.t();
                return;
            case (byte) 3:
                dfVar.u();
                return;
            case (byte) 4:
                dfVar.y();
                return;
            case (byte) 6:
                dfVar.v();
                return;
            case (byte) 8:
                dfVar.w();
                return;
            case (byte) 10:
                dfVar.x();
                return;
            case (byte) 11:
                dfVar.A();
                return;
            case (byte) 12:
                dfVar.j();
                while (true) {
                    da l = dfVar.l();
                    if (l.b == (byte) 0) {
                        dfVar.k();
                        return;
                    } else {
                        a(dfVar, l.b, i - 1);
                        dfVar.m();
                    }
                }
            case (byte) 13:
                dc n = dfVar.n();
                while (i2 < n.c) {
                    a(dfVar, n.a, i - 1);
                    a(dfVar, n.b, i - 1);
                    i2++;
                }
                dfVar.o();
                return;
            case (byte) 14:
                dj r = dfVar.r();
                while (i2 < r.b) {
                    a(dfVar, r.a, i - 1);
                    i2++;
                }
                dfVar.s();
                return;
            case (byte) 15:
                db p = dfVar.p();
                while (i2 < p.b) {
                    a(dfVar, p.a, i - 1);
                    i2++;
                }
                dfVar.q();
                return;
            default:
                return;
        }
    }

    public static dh a(byte[] bArr, dh dhVar) {
        if (bArr[0] > dm.n) {
            return new a();
        }
        if (bArr.length <= 1 || (bArr[1] & 128) == 0) {
            return dhVar;
        }
        return new a();
    }
}
