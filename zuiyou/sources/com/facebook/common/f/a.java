package com.facebook.common.f;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class a extends FilterInputStream {
    private int a;
    private int b;

    public a(InputStream inputStream, int i) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (i < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        } else {
            this.a = i;
            this.b = -1;
        }
    }

    public int read() throws IOException {
        if (this.a == 0) {
            return -1;
        }
        int read = this.in.read();
        if (read != -1) {
            this.a--;
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.a == 0) {
            return -1;
        }
        int read = this.in.read(bArr, i, Math.min(i2, this.a));
        if (read <= 0) {
            return read;
        }
        this.a -= read;
        return read;
    }

    public long skip(long j) throws IOException {
        long skip = this.in.skip(Math.min(j, (long) this.a));
        this.a = (int) (((long) this.a) - skip);
        return skip;
    }

    public int available() throws IOException {
        return Math.min(this.in.available(), this.a);
    }

    public void mark(int i) {
        if (this.in.markSupported()) {
            this.in.mark(i);
            this.b = this.a;
        }
    }

    public void reset() throws IOException {
        if (!this.in.markSupported()) {
            throw new IOException("mark is not supported");
        } else if (this.b == -1) {
            throw new IOException("mark not set");
        } else {
            this.in.reset();
            this.a = this.b;
        }
    }
}
