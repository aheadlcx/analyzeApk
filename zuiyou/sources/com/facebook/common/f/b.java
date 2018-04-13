package com.facebook.common.f;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class b extends FilterInputStream {
    private final byte[] a;
    private int b;
    private int c;

    public b(InputStream inputStream, byte[] bArr) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (bArr == null) {
            throw new NullPointerException();
        } else {
            this.a = bArr;
        }
    }

    public int read() throws IOException {
        int read = this.in.read();
        return read != -1 ? read : a();
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            return read;
        }
        if (i2 == 0) {
            return 0;
        }
        read = 0;
        while (read < i2) {
            int a = a();
            if (a == -1) {
                break;
            }
            bArr[i + read] = (byte) a;
            read++;
        }
        return read <= 0 ? -1 : read;
    }

    public void reset() throws IOException {
        if (this.in.markSupported()) {
            this.in.reset();
            this.b = this.c;
            return;
        }
        throw new IOException("mark is not supported");
    }

    public void mark(int i) {
        if (this.in.markSupported()) {
            super.mark(i);
            this.c = this.b;
        }
    }

    private int a() {
        if (this.b >= this.a.length) {
            return -1;
        }
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & 255;
    }
}
