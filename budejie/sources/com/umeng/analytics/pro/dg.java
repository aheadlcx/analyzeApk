package com.umeng.analytics.pro;

public class dg extends cm {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    private static final long h = 1;
    protected int g = 0;

    public dg(int i) {
        this.g = i;
    }

    public dg(int i, String str) {
        super(str);
        this.g = i;
    }

    public dg(String str) {
        super(str);
    }

    public dg(int i, Throwable th) {
        super(th);
        this.g = i;
    }

    public dg(Throwable th) {
        super(th);
    }

    public dg(String str, Throwable th) {
        super(str, th);
    }

    public dg(int i, String str, Throwable th) {
        super(str, th);
        this.g = i;
    }

    public int a() {
        return this.g;
    }
}
