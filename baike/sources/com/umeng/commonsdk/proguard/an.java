package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.ae.a;

public class an {
    private static int a = Integer.MAX_VALUE;

    public static void a(int i) {
        a = i;
    }

    public static void a(ak akVar, byte b) throws r {
        a(akVar, b, a);
    }

    public static void a(ak akVar, byte b, int i) throws r {
        int i2 = 0;
        if (i <= 0) {
            throw new r("Maximum skip depth exceeded");
        }
        switch (b) {
            case (byte) 2:
                akVar.t();
                return;
            case (byte) 3:
                akVar.u();
                return;
            case (byte) 4:
                akVar.y();
                return;
            case (byte) 6:
                akVar.v();
                return;
            case (byte) 8:
                akVar.w();
                return;
            case (byte) 10:
                akVar.x();
                return;
            case (byte) 11:
                akVar.A();
                return;
            case (byte) 12:
                akVar.j();
                while (true) {
                    af l = akVar.l();
                    if (l.b == (byte) 0) {
                        akVar.k();
                        return;
                    } else {
                        a(akVar, l.b, i - 1);
                        akVar.m();
                    }
                }
            case (byte) 13:
                ah n = akVar.n();
                while (i2 < n.c) {
                    a(akVar, n.a, i - 1);
                    a(akVar, n.b, i - 1);
                    i2++;
                }
                akVar.o();
                return;
            case (byte) 14:
                ao r = akVar.r();
                while (i2 < r.b) {
                    a(akVar, r.a, i - 1);
                    i2++;
                }
                akVar.s();
                return;
            case (byte) 15:
                ag p = akVar.p();
                while (i2 < p.b) {
                    a(akVar, p.a, i - 1);
                    i2++;
                }
                akVar.q();
                return;
            default:
                return;
        }
    }

    public static am a(byte[] bArr, am amVar) {
        if (bArr[0] > ar.n) {
            return new a();
        }
        if (bArr.length <= 1 || (bArr[1] & 128) == 0) {
            return amVar;
        }
        return new a();
    }
}
