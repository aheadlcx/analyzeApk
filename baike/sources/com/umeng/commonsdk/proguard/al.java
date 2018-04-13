package com.umeng.commonsdk.proguard;

public class al extends r {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    protected int g = 0;

    public al(int i) {
        this.g = i;
    }

    public al(int i, String str) {
        super(str);
        this.g = i;
    }

    public al(String str) {
        super(str);
    }

    public al(int i, Throwable th) {
        super(th);
        this.g = i;
    }

    public al(Throwable th) {
        super(th);
    }

    public al(String str, Throwable th) {
        super(str, th);
    }

    public al(int i, String str, Throwable th) {
        super(str, th);
        this.g = i;
    }

    public int a() {
        return this.g;
    }
}
