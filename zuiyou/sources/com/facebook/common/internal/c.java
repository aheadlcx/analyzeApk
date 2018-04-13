package com.facebook.common.internal;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class c extends FilterOutputStream {
    private long a = 0;

    public c(OutputStream outputStream) {
        super(outputStream);
    }

    public long a() {
        return this.a;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        this.a += (long) i2;
    }

    public void write(int i) throws IOException {
        this.out.write(i);
        this.a++;
    }

    public void close() throws IOException {
        this.out.close();
    }
}
