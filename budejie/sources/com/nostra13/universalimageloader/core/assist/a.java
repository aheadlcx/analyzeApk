package com.nostra13.universalimageloader.core.assist;

import java.io.IOException;
import java.io.InputStream;

public class a extends InputStream {
    private final InputStream a;
    private final int b;

    public a(InputStream inputStream, int i) {
        this.a = inputStream;
        this.b = i;
    }

    public int available() {
        return this.b;
    }

    public void close() throws IOException {
        this.a.close();
    }

    public void mark(int i) {
        this.a.mark(i);
    }

    public int read() throws IOException {
        return this.a.read();
    }

    public int read(byte[] bArr) throws IOException {
        return this.a.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.a.read(bArr, i, i2);
    }

    public void reset() throws IOException {
        this.a.reset();
    }

    public long skip(long j) throws IOException {
        return this.a.skip(j);
    }

    public boolean markSupported() {
        return this.a.markSupported();
    }
}
