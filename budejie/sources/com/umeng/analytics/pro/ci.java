package com.umeng.analytics.pro;

import java.io.ByteArrayOutputStream;

public class ci extends ByteArrayOutputStream {
    public ci(int i) {
        super(i);
    }

    public byte[] a() {
        return this.buf;
    }

    public int b() {
        return this.count;
    }
}
