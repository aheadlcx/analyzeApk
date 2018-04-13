package com.bumptech.glide.b;

import android.util.Log;
import com.budejie.www.R$styleable;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class d {
    private final byte[] a = new byte[256];
    private ByteBuffer b;
    private c c;
    private int d = 0;

    public d a(byte[] bArr) {
        c();
        if (bArr != null) {
            this.b = ByteBuffer.wrap(bArr);
            this.b.rewind();
            this.b.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.b = null;
            this.c.b = 2;
        }
        return this;
    }

    public void a() {
        this.b = null;
        this.c = null;
    }

    private void c() {
        this.b = null;
        Arrays.fill(this.a, (byte) 0);
        this.c = new c();
        this.d = 0;
    }

    public c b() {
        if (this.b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        } else if (o()) {
            return this.c;
        } else {
            h();
            if (!o()) {
                d();
                if (this.c.c < 0) {
                    this.c.b = 1;
                }
            }
            return this.c;
        }
    }

    private void d() {
        int i = 0;
        while (i == 0 && !o()) {
            switch (m()) {
                case 33:
                    switch (m()) {
                        case 1:
                            k();
                            break;
                        case R$styleable.Theme_Custom_gray_click_state /*249*/:
                            this.c.d = new b();
                            e();
                            break;
                        case R$styleable.Theme_Custom_icon_post_copyright /*254*/:
                            k();
                            break;
                        case 255:
                            l();
                            String str = "";
                            for (int i2 = 0; i2 < 11; i2++) {
                                str = str + ((char) this.a[i2]);
                            }
                            if (!str.equals("NETSCAPE2.0")) {
                                k();
                                break;
                            } else {
                                g();
                                break;
                            }
                        default:
                            k();
                            break;
                    }
                case 44:
                    if (this.c.d == null) {
                        this.c.d = new b();
                    }
                    f();
                    break;
                case 59:
                    i = 1;
                    break;
                default:
                    this.c.b = 1;
                    break;
            }
        }
    }

    private void e() {
        boolean z = true;
        m();
        int m = m();
        this.c.d.g = (m & 28) >> 2;
        if (this.c.d.g == 0) {
            this.c.d.g = 1;
        }
        b bVar = this.c.d;
        if ((m & 1) == 0) {
            z = false;
        }
        bVar.f = z;
        int n = n();
        if (n < 3) {
            n = 10;
        }
        this.c.d.i = n * 10;
        this.c.d.h = m();
        m();
    }

    private void f() {
        boolean z = true;
        this.c.d.a = n();
        this.c.d.b = n();
        this.c.d.c = n();
        this.c.d.d = n();
        int m = m();
        boolean z2 = (m & 128) != 0;
        int pow = (int) Math.pow(2.0d, (double) ((m & 7) + 1));
        b bVar = this.c.d;
        if ((m & 64) == 0) {
            z = false;
        }
        bVar.e = z;
        if (z2) {
            this.c.d.k = a(pow);
        } else {
            this.c.d.k = null;
        }
        this.c.d.j = this.b.position();
        j();
        if (!o()) {
            c cVar = this.c;
            cVar.c++;
            this.c.e.add(this.c.d);
        }
    }

    private void g() {
        do {
            l();
            if (this.a[0] == (byte) 1) {
                this.c.m = (this.a[1] & 255) | ((this.a[2] & 255) << 8);
            }
            if (this.d <= 0) {
                return;
            }
        } while (!o());
    }

    private void h() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) m());
        }
        if (str.startsWith("GIF")) {
            i();
            if (this.c.h && !o()) {
                this.c.a = a(this.c.i);
                this.c.l = this.c.a[this.c.j];
                return;
            }
            return;
        }
        this.c.b = 1;
    }

    private void i() {
        this.c.f = n();
        this.c.g = n();
        int m = m();
        this.c.h = (m & 128) != 0;
        this.c.i = 2 << (m & 7);
        this.c.j = m();
        this.c.k = m();
    }

    private int[] a(int i) {
        int[] iArr;
        Throwable e;
        int i2 = 0;
        byte[] bArr = new byte[(i * 3)];
        try {
            this.b.get(bArr);
            iArr = new int[256];
            int i3 = 0;
            while (i2 < i) {
                int i4 = i3 + 1;
                try {
                    int i5 = bArr[i3] & 255;
                    int i6 = i4 + 1;
                    int i7 = bArr[i4] & 255;
                    i3 = i6 + 1;
                    i4 = i2 + 1;
                    iArr[i2] = (((i5 << 16) | -16777216) | (i7 << 8)) | (bArr[i6] & 255);
                    i2 = i4;
                } catch (BufferUnderflowException e2) {
                    e = e2;
                }
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            iArr = null;
            e = th;
            if (Log.isLoggable("GifHeaderParser", 3)) {
                Log.d("GifHeaderParser", "Format Error Reading Color Table", e);
            }
            this.c.b = 1;
            return iArr;
        }
        return iArr;
    }

    private void j() {
        m();
        k();
    }

    private void k() {
        int m;
        do {
            m = m();
            this.b.position(this.b.position() + m);
        } while (m > 0);
    }

    private int l() {
        int i = 0;
        this.d = m();
        if (this.d > 0) {
            int i2 = 0;
            while (i < this.d) {
                try {
                    i2 = this.d - i;
                    this.b.get(this.a, i, i2);
                    i += i2;
                } catch (Throwable e) {
                    if (Log.isLoggable("GifHeaderParser", 3)) {
                        Log.d("GifHeaderParser", "Error Reading Block n: " + i + " count: " + i2 + " blockSize: " + this.d, e);
                    }
                    this.c.b = 1;
                }
            }
        }
        return i;
    }

    private int m() {
        int i = 0;
        try {
            return this.b.get() & 255;
        } catch (Exception e) {
            this.c.b = 1;
            return i;
        }
    }

    private int n() {
        return this.b.getShort();
    }

    private boolean o() {
        return this.c.b != 0;
    }
}
