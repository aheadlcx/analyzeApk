package com.facebook.common.memory;

import com.facebook.common.internal.g;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class h extends InputStream {
    final PooledByteBuffer a;
    int b;
    int c;

    public h(PooledByteBuffer pooledByteBuffer) {
        boolean z;
        if (pooledByteBuffer.isClosed()) {
            z = false;
        } else {
            z = true;
        }
        g.a(z);
        this.a = (PooledByteBuffer) g.a((Object) pooledByteBuffer);
        this.b = 0;
        this.c = 0;
    }

    public int available() {
        return this.a.size() - this.b;
    }

    public void mark(int i) {
        this.c = this.b;
    }

    public boolean markSupported() {
        return true;
    }

    public int read() {
        if (available() <= 0) {
            return -1;
        }
        PooledByteBuffer pooledByteBuffer = this.a;
        int i = this.b;
        this.b = i + 1;
        return pooledByteBuffer.read(i) & 255;
    }

    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + bArr.length + "; regionStart=" + i + "; regionLength=" + i2);
        }
        int available = available();
        if (available <= 0) {
            return -1;
        }
        if (i2 <= 0) {
            return 0;
        }
        available = Math.min(available, i2);
        this.a.read(this.b, bArr, i, available);
        this.b += available;
        return available;
    }

    public void reset() {
        this.b = this.c;
    }

    public long skip(long j) {
        g.a(j >= 0);
        int min = Math.min((int) j, available());
        this.b += min;
        return (long) min;
    }
}
