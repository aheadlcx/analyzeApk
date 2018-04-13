package com.umeng.analytics.pro;

public final class dj {
    public final byte a;
    public final int b;

    public dj() {
        this((byte) 0, 0);
    }

    public dj(byte b, int i) {
        this.a = b;
        this.b = i;
    }

    public dj(db dbVar) {
        this(dbVar.a, dbVar.b);
    }
}
