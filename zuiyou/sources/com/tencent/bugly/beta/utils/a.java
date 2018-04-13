package com.tencent.bugly.beta.utils;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class a {
    public static long a = -1;
    public static long b = 0;
    public static long c = 1;
    private byte[] d;
    private byte[] e;
    private byte[] f;
    private long g;
    private String h;
    private BufferedInputStream i;
    private long j;
    private long k;

    public synchronized void a(long j) {
        this.g = j;
    }

    public a(String str) throws Exception {
        this.d = new byte[2];
        this.e = new byte[4];
        this.f = new byte[8];
        this.g = c;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.h = str;
        this.i = new BufferedInputStream(new FileInputStream(this.h));
        this.j = 0;
        this.k = 0;
    }

    public a(String str, long j) throws Exception {
        this.d = new byte[2];
        this.e = new byte[4];
        this.f = new byte[8];
        this.g = c;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.h = str;
        this.g = j;
        this.i = new BufferedInputStream(new FileInputStream(this.h));
        this.j = 0;
        this.k = 0;
    }

    public synchronized boolean a() {
        boolean z;
        try {
            if (this.i != null) {
                this.i.close();
            }
            this.i = null;
            this.h = null;
            this.j = 0;
            this.k = 0;
            z = true;
        } catch (IOException e) {
            Log.e("BinaryFileReader", e.getMessage());
            z = false;
        }
        return z;
    }

    public synchronized boolean b(long j) {
        boolean z = false;
        synchronized (this) {
            if (this.i == null) {
                Log.e("BinaryFileReader", "Please open file first！");
            } else if (j == 0) {
                z = true;
            } else {
                long j2 = j;
                while (j2 > 0) {
                    try {
                        j2 -= this.i.skip(j2);
                    } catch (IOException e) {
                        Log.e("BinaryFileReader", "Failed to skip file pointer！");
                    }
                }
                this.j += j;
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean a(byte[] bArr) {
        boolean z;
        try {
            this.i.read(bArr);
            this.j += (long) bArr.length;
            this.k += (long) bArr.length;
            z = true;
        } catch (IOException e) {
            Log.e("BinaryFileReader", e.getMessage());
            z = false;
        }
        return z;
    }

    public synchronized byte b() throws IOException {
        byte b;
        b = (byte) 0;
        if (this.i == null) {
            Log.e("BinaryFileReader", "Failed to skip file pointer！");
        } else {
            b = (byte) this.i.read();
            this.j++;
            this.k++;
        }
        return b;
    }

    public synchronized short c() throws IOException {
        short s;
        s = (short) 0;
        if (this.i == null) {
            Log.e("BinaryFileReader", "Failed to skip file pointer！");
        } else {
            this.i.read(this.d);
            s = a(this.d, this.g);
            this.j += 2;
            this.k += 2;
        }
        return s;
    }

    public synchronized int d() throws IOException {
        int i;
        i = 0;
        if (this.i == null) {
            Log.e("BinaryFileReader", "Failed to skip file pointer！");
        } else {
            this.i.read(this.e);
            i = b(this.e, this.g);
            this.j += 4;
            this.k += 4;
        }
        return i;
    }

    public synchronized long e() throws IOException {
        long j;
        j = 0;
        if (this.i == null) {
            Log.e("BinaryFileReader", "Failed to skip file pointer！");
        } else {
            this.i.read(this.f);
            j = c(this.f, this.g);
            this.j += 8;
            this.k += 8;
        }
        return j;
    }

    public synchronized long f() throws IOException {
        return ((long) b()) & 255;
    }

    public synchronized long g() throws IOException {
        return ((long) c()) & 65535;
    }

    public synchronized long h() throws IOException {
        return ((long) d()) & 4294967295L;
    }

    public synchronized long i() throws IOException {
        return e();
    }

    private static short b(byte[] bArr) {
        if (bArr == null || bArr.length > 2) {
            return (short) -1;
        }
        return (short) c(bArr);
    }

    private static int c(byte[] bArr) {
        if (bArr == null || bArr.length > 4) {
            return -1;
        }
        return (int) d(bArr);
    }

    private static long d(byte[] bArr) {
        if (bArr == null || bArr.length > 8) {
            return -1;
        }
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0) {
            long j2 = (((long) bArr[length]) & 255) | (j << 8);
            length--;
            j = j2;
        }
        return j;
    }

    private static short e(byte[] bArr) {
        if (bArr == null || bArr.length > 2) {
            return (short) -1;
        }
        return (short) f(bArr);
    }

    private static int f(byte[] bArr) {
        if (bArr == null || bArr.length > 4) {
            return -1;
        }
        return (int) g(bArr);
    }

    private static long g(byte[] bArr) {
        if (bArr == null || bArr.length > 8) {
            return -1;
        }
        long j = 0;
        int i = 0;
        while (i < bArr.length) {
            i++;
            j = (((long) bArr[i]) & 255) | (j << 8);
        }
        return j;
    }

    public static short a(byte[] bArr, long j) {
        if (j == c) {
            return b(bArr);
        }
        return e(bArr);
    }

    public static int b(byte[] bArr, long j) {
        if (j == c) {
            return c(bArr);
        }
        return f(bArr);
    }

    public static long c(byte[] bArr, long j) {
        if (j == c) {
            return d(bArr);
        }
        return g(bArr);
    }
}
