package com.umeng.analytics.pro;

public class du extends cm {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    private static final long g = 1;
    protected int f = 0;

    public du(int i) {
        this.f = i;
    }

    public du(int i, String str) {
        super(str);
        this.f = i;
    }

    public du(String str) {
        super(str);
    }

    public du(int i, Throwable th) {
        super(th);
        this.f = i;
    }

    public du(Throwable th) {
        super(th);
    }

    public du(String str, Throwable th) {
        super(str, th);
    }

    public du(int i, String str, Throwable th) {
        super(str, th);
        this.f = i;
    }

    public int a() {
        return this.f;
    }
}
