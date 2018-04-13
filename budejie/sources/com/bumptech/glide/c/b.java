package com.bumptech.glide.c;

import android.support.v4.internal.view.SupportMenu;
import cn.v6.sixrooms.constants.CommonInts;
import com.budejie.www.R$styleable;
import com.tencent.smtt.sdk.TbsReaderView.ReaderCallback;
import java.io.IOException;
import java.io.OutputStream;

class b {
    int a;
    int b = 12;
    int c;
    int d = 4096;
    int[] e = new int[ReaderCallback.COPY_SELECT_TEXT];
    int[] f = new int[ReaderCallback.COPY_SELECT_TEXT];
    int g = ReaderCallback.COPY_SELECT_TEXT;
    int h = 0;
    boolean i = false;
    int j;
    int k;
    int l;
    int m = 0;
    int n = 0;
    int[] o = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, CommonInts.PIC_UPLOADING_ERROR_CODE, 2047, 4095, 8191, 16383, 32767, SupportMenu.USER_MASK};
    int p;
    byte[] q = new byte[256];
    private int r;
    private int s;
    private byte[] t;
    private int u;
    private int v;
    private int w;

    b(int i, int i2, byte[] bArr, int i3) {
        this.r = i;
        this.s = i2;
        this.t = bArr;
        this.u = Math.max(2, i3);
    }

    void a(byte b, OutputStream outputStream) throws IOException {
        byte[] bArr = this.q;
        int i = this.p;
        this.p = i + 1;
        bArr[i] = b;
        if (this.p >= R$styleable.Theme_Custom_icon_post_copyright) {
            c(outputStream);
        }
    }

    void a(OutputStream outputStream) throws IOException {
        a(this.g);
        this.h = this.k + 2;
        this.i = true;
        b(this.k, outputStream);
    }

    void a(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.e[i2] = -1;
        }
    }

    void a(int i, OutputStream outputStream) throws IOException {
        int i2;
        int i3 = 0;
        this.j = i;
        this.i = false;
        this.a = this.j;
        this.c = b(this.a);
        this.k = 1 << (i - 1);
        this.l = this.k + 1;
        this.h = this.k + 2;
        this.p = 0;
        int a = a();
        for (i2 = this.g; i2 < 65536; i2 *= 2) {
            i3++;
        }
        int i4 = 8 - i3;
        int i5 = this.g;
        a(i5);
        b(this.k, outputStream);
        while (true) {
            i3 = a();
            if (i3 != -1) {
                int i6 = (i3 << this.b) + a;
                i2 = (i3 << i4) ^ a;
                if (this.e[i2] == i6) {
                    a = this.f[i2];
                } else if (this.e[i2] >= 0) {
                    r3 = i5 - i2;
                    if (i2 == 0) {
                        r3 = 1;
                    }
                    do {
                        i2 -= r3;
                        if (i2 < 0) {
                            i2 += i5;
                        }
                        if (this.e[i2] == i6) {
                            a = this.f[i2];
                            break;
                        }
                    } while (this.e[i2] >= 0);
                    b(a, outputStream);
                    if (this.h >= this.d) {
                        r0 = this.f;
                        r3 = this.h;
                        this.h = r3 + 1;
                        r0[i2] = r3;
                        this.e[i2] = i6;
                        a = i3;
                    } else {
                        a(outputStream);
                        a = i3;
                    }
                } else {
                    b(a, outputStream);
                    if (this.h >= this.d) {
                        a(outputStream);
                        a = i3;
                    } else {
                        r0 = this.f;
                        r3 = this.h;
                        this.h = r3 + 1;
                        r0[i2] = r3;
                        this.e[i2] = i6;
                        a = i3;
                    }
                }
            } else {
                b(a, outputStream);
                b(this.l, outputStream);
                return;
            }
        }
    }

    void b(OutputStream outputStream) throws IOException {
        outputStream.write(this.u);
        this.v = this.r * this.s;
        this.w = 0;
        a(this.u + 1, outputStream);
        outputStream.write(0);
    }

    void c(OutputStream outputStream) throws IOException {
        if (this.p > 0) {
            outputStream.write(this.p);
            outputStream.write(this.q, 0, this.p);
            this.p = 0;
        }
    }

    final int b(int i) {
        return (1 << i) - 1;
    }

    private int a() {
        if (this.v == 0) {
            return -1;
        }
        this.v--;
        byte[] bArr = this.t;
        int i = this.w;
        this.w = i + 1;
        return bArr[i] & 255;
    }

    void b(int i, OutputStream outputStream) throws IOException {
        this.m &= this.o[this.n];
        if (this.n > 0) {
            this.m |= i << this.n;
        } else {
            this.m = i;
        }
        this.n += this.a;
        while (this.n >= 8) {
            a((byte) (this.m & 255), outputStream);
            this.m >>= 8;
            this.n -= 8;
        }
        if (this.h > this.c || this.i) {
            if (this.i) {
                int i2 = this.j;
                this.a = i2;
                this.c = b(i2);
                this.i = false;
            } else {
                this.a++;
                if (this.a == this.b) {
                    this.c = this.d;
                } else {
                    this.c = b(this.a);
                }
            }
        }
        if (i == this.l) {
            while (this.n > 0) {
                a((byte) (this.m & 255), outputStream);
                this.m >>= 8;
                this.n -= 8;
            }
            c(outputStream);
        }
    }
}
