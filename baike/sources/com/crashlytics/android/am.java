package com.crashlytics.android;

import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class am implements Flushable {
    private final byte[] a;
    private final int b;
    private int c = 0;
    private final OutputStream d;

    private am(OutputStream outputStream, byte[] bArr) {
        this.d = outputStream;
        this.a = bArr;
        this.b = bArr.length;
    }

    public static am a(OutputStream outputStream) {
        return new am(outputStream, new byte[4096]);
    }

    public final void a(int i, float f) throws IOException {
        g(1, 5);
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        d(floatToRawIntBits & 255);
        d((floatToRawIntBits >> 8) & 255);
        d((floatToRawIntBits >> 16) & 255);
        d(floatToRawIntBits >>> 24);
    }

    public final void a(int i, long j) throws IOException {
        g(i, 0);
        a(j);
    }

    public final void a(int i, boolean z) throws IOException {
        int i2 = 0;
        g(i, 0);
        if (z) {
            i2 = 1;
        }
        d(i2);
    }

    public final void a(int i, String str) throws IOException {
        g(1, 2);
        byte[] bytes = str.getBytes("UTF-8");
        b(bytes.length);
        a(bytes);
    }

    public final void a(int i, ai aiVar) throws IOException {
        g(i, 2);
        b(aiVar.a());
        int a = aiVar.a();
        if (this.b - this.c >= a) {
            aiVar.a(this.a, 0, this.c, a);
            this.c = a + this.c;
            return;
        }
        int i2 = this.b - this.c;
        aiVar.a(this.a, 0, this.c, i2);
        int i3 = i2 + 0;
        a -= i2;
        this.c = this.b;
        a();
        if (a <= this.b) {
            aiVar.a(this.a, i3, 0, a);
            this.c = a;
            return;
        }
        InputStream b = aiVar.b();
        if (((long) i3) != b.skip((long) i3)) {
            throw new IllegalStateException("Skip failed.");
        }
        while (a > 0) {
            i3 = Math.min(a, this.b);
            int read = b.read(this.a, 0, i3);
            if (read != i3) {
                throw new IllegalStateException("Read failed.");
            }
            this.d.write(this.a, 0, read);
            a -= read;
        }
    }

    public final void a(int i, int i2) throws IOException {
        g(i, 0);
        b(i2);
    }

    public final void b(int i, int i2) throws IOException {
        g(i, 0);
        if (i2 >= 0) {
            b(i2);
        } else {
            a((long) i2);
        }
    }

    public final void c(int i, int i2) throws IOException {
        g(2, 0);
        b(e(i2));
    }

    public static int b(int i, float f) {
        return a(1) + 4;
    }

    public static int b(int i, long j) {
        int a = a(i);
        int i2 = (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10;
        return i2 + a;
    }

    public static int b(int i, boolean z) {
        return a(i) + 1;
    }

    public static int b(int i, ai aiVar) {
        return a(i) + (c(aiVar.a()) + aiVar.a());
    }

    public static int d(int i, int i2) {
        return a(i) + c(i2);
    }

    public static int e(int i, int i2) {
        return (i2 >= 0 ? c(i2) : 10) + a(i);
    }

    public static int f(int i, int i2) {
        return a(2) + c(e(i2));
    }

    private void a() throws IOException {
        if (this.d == null) {
            throw new an();
        }
        this.d.write(this.a, 0, this.c);
        this.c = 0;
    }

    public final void flush() throws IOException {
        if (this.d != null) {
            a();
        }
    }

    private void d(int i) throws IOException {
        byte b = (byte) i;
        if (this.c == this.b) {
            a();
        }
        byte[] bArr = this.a;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = b;
    }

    public final void a(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.b - this.c >= length) {
            System.arraycopy(bArr, 0, this.a, this.c, length);
            this.c = length + this.c;
            return;
        }
        int i = this.b - this.c;
        System.arraycopy(bArr, 0, this.a, this.c, i);
        int i2 = i + 0;
        length -= i;
        this.c = this.b;
        a();
        if (length <= this.b) {
            System.arraycopy(bArr, i2, this.a, 0, length);
            this.c = length;
            return;
        }
        this.d.write(bArr, i2, length);
    }

    public final void g(int i, int i2) throws IOException {
        b(af.a(i, i2));
    }

    public static int a(int i) {
        return c(af.a(i, 0));
    }

    public final void b(int i) throws IOException {
        while ((i & -128) != 0) {
            d((i & 127) | 128);
            i >>>= 7;
        }
        d(i);
    }

    public static int c(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    private void a(long j) throws IOException {
        while ((-128 & j) != 0) {
            d((((int) j) & 127) | 128);
            j >>>= 7;
        }
        d((int) j);
    }

    private static int e(int i) {
        return (i << 1) ^ (i >> 31);
    }
}
