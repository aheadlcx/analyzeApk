package com.crashlytics.android.internal;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public final class aF extends BufferedOutputStream {
    private final CharsetEncoder a;

    public aF(OutputStream outputStream, String str, int i) {
        super(outputStream, i);
        this.a = Charset.forName(C0013ay.c(str)).newEncoder();
    }

    public final aF a(String str) throws IOException {
        ByteBuffer encode = this.a.encode(CharBuffer.wrap(str));
        super.write(encode.array(), 0, encode.limit());
        return this;
    }
}
