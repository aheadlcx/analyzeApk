package a.a.a.b;

import a.a.a.j;

public class g {
    private static int a = Integer.MAX_VALUE;

    public static void a(f fVar, byte b) throws j {
        a(fVar, b, a);
    }

    public static void a(f fVar, byte b, int i) throws j {
        int i2 = 0;
        if (i <= 0) {
            throw new j("Maximum skip depth exceeded");
        }
        switch (b) {
            case (byte) 2:
                fVar.p();
                return;
            case (byte) 3:
                fVar.q();
                return;
            case (byte) 4:
                fVar.u();
                return;
            case (byte) 6:
                fVar.r();
                return;
            case (byte) 8:
                fVar.s();
                return;
            case (byte) 10:
                fVar.t();
                return;
            case (byte) 11:
                fVar.w();
                return;
            case (byte) 12:
                fVar.f();
                while (true) {
                    c h = fVar.h();
                    if (h.b == (byte) 0) {
                        fVar.g();
                        return;
                    } else {
                        a(fVar, h.b, i - 1);
                        fVar.i();
                    }
                }
            case (byte) 13:
                e j = fVar.j();
                while (i2 < j.c) {
                    a(fVar, j.a, i - 1);
                    a(fVar, j.b, i - 1);
                    i2++;
                }
                fVar.k();
                return;
            case (byte) 14:
                h n = fVar.n();
                while (i2 < n.b) {
                    a(fVar, n.a, i - 1);
                    i2++;
                }
                fVar.o();
                return;
            case (byte) 15:
                d l = fVar.l();
                while (i2 < l.b) {
                    a(fVar, l.a, i - 1);
                    i2++;
                }
                fVar.m();
                return;
            default:
                return;
        }
    }
}
