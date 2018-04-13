package com.umeng.commonsdk.proguard;

import java.io.ByteArrayOutputStream;

public class n extends ByteArrayOutputStream {
    public n(int i) {
        super(i);
    }

    public byte[] a() {
        return this.buf;
    }

    public int b() {
        return this.count;
    }
}
