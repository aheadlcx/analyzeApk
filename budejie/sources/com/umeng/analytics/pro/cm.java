package com.umeng.analytics.pro;

public class cm extends Exception {
    private static final long a = 1;

    public cm(String str) {
        super(str);
    }

    public cm(Throwable th) {
        super(th);
    }

    public cm(String str, Throwable th) {
        super(str, th);
    }
}
