package com.loc;

import java.util.Locale;

public final class ce {
    private static ce p = null;
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = -113;
    public int k = 0;
    public short l = (short) 0;
    public long m = 0;
    public boolean n = false;
    public boolean o = true;

    public ce(int i, boolean z) {
        this.k = i;
        this.n = z;
    }

    public final int a() {
        return this.c;
    }

    public final boolean a(ce ceVar) {
        if (ceVar == null) {
            return false;
        }
        switch (ceVar.k) {
            case 1:
                return this.k == 1 && ceVar.c == this.c && ceVar.d == this.d && ceVar.b == this.b;
            case 2:
                return this.k == 2 && ceVar.i == this.i && ceVar.h == this.h && ceVar.g == this.g;
            case 3:
                return this.k == 3 && ceVar.c == this.c && ceVar.d == this.d && ceVar.b == this.b;
            case 4:
                return this.k == 4 && ceVar.c == this.c && ceVar.d == this.d && ceVar.b == this.b;
            default:
                return false;
        }
    }

    public final int b() {
        return this.d;
    }

    public final int c() {
        return this.h;
    }

    public final int d() {
        return this.i;
    }

    public final int e() {
        return this.j;
    }

    public final String toString() {
        String str = "unknown";
        switch (this.k) {
            case 1:
                return String.format(Locale.CHINA, "GSM lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)});
            case 2:
                return String.format(Locale.CHINA, "CDMA bid=%d, nid=%d, sid=%d, valid=%b, sig=%d, age=%d, reg=%b", new Object[]{Integer.valueOf(this.i), Integer.valueOf(this.h), Integer.valueOf(this.g), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)});
            case 3:
                return String.format(Locale.CHINA, "LTE lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)});
            case 4:
                return String.format(Locale.CHINA, "WCDMA lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)});
            default:
                return str;
        }
    }
}
