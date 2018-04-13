package com.alibaba.sdk.android.httpdns;

public class e extends Throwable {
    private int b;

    public e(int i, String str) {
        super(str);
        this.b = i;
    }

    public int getErrorCode() {
        return this.b;
    }
}
