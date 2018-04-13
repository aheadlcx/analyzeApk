package com.umeng.commonsdk.proguard;

public class az extends r {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    protected int f = 0;

    public az(int i) {
        this.f = i;
    }

    public az(int i, String str) {
        super(str);
        this.f = i;
    }

    public az(String str) {
        super(str);
    }

    public az(int i, Throwable th) {
        super(th);
        this.f = i;
    }

    public az(Throwable th) {
        super(th);
    }

    public az(String str, Throwable th) {
        super(str, th);
    }

    public az(int i, String str, Throwable th) {
        super(str, th);
        this.f = i;
    }

    public int a() {
        return this.f;
    }
}
