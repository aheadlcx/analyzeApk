package com.facebook.common.memory;

import com.facebook.common.c.a;
import com.facebook.common.internal.g;
import com.facebook.common.references.c;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class f extends InputStream {
    private final InputStream a;
    private final byte[] b;
    private final c<byte[]> c;
    private int d = 0;
    private int e = 0;
    private boolean f = false;

    public f(InputStream inputStream, byte[] bArr, c<byte[]> cVar) {
        this.a = (InputStream) g.a((Object) inputStream);
        this.b = (byte[]) g.a((Object) bArr);
        this.c = (c) g.a((Object) cVar);
    }

    public int read() throws IOException {
        g.b(this.e <= this.d);
        b();
        if (!a()) {
            return -1;
        }
        byte[] bArr = this.b;
        int i = this.e;
        this.e = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        g.b(this.e <= this.d);
        b();
        if (!a()) {
            return -1;
        }
        int min = Math.min(this.d - this.e, i2);
        System.arraycopy(this.b, this.e, bArr, i, min);
        this.e += min;
        return min;
    }

    public int available() throws IOException {
        g.b(this.e <= this.d);
        b();
        return (this.d - this.e) + this.a.available();
    }

    public void close() throws IOException {
        if (!this.f) {
            this.f = true;
            this.c.release(this.b);
            super.close();
        }
    }

    public long skip(long j) throws IOException {
        g.b(this.e <= this.d);
        b();
        int i = this.d - this.e;
        if (((long) i) >= j) {
            this.e = (int) (((long) this.e) + j);
            return j;
        }
        this.e = this.d;
        return ((long) i) + this.a.skip(j - ((long) i));
    }

    private boolean a() throws IOException {
        if (this.e < this.d) {
            return true;
        }
        int read = this.a.read(this.b);
        if (read <= 0) {
            return false;
        }
        this.d = read;
        this.e = 0;
        return true;
    }

    private void b() throws IOException {
        if (this.f) {
            throw new IOException("stream already closed");
        }
    }

    protected void finalize() throws Throwable {
        if (!this.f) {
            a.a("PooledByteInputStream", "Finalized without closing");
            close();
        }
        super.finalize();
    }
}
