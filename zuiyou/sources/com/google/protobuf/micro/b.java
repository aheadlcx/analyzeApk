package com.google.protobuf.micro;

import java.io.InputStream;
import java.util.Vector;

public final class b {
    private final byte[] a;
    private int b;
    private int c;
    private int d;
    private final InputStream e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    private b(InputStream inputStream) {
        this.h = Integer.MAX_VALUE;
        this.j = 64;
        this.k = 67108864;
        this.a = new byte[4096];
        this.b = 0;
        this.d = 0;
        this.e = inputStream;
    }

    private b(byte[] bArr, int i, int i2) {
        this.h = Integer.MAX_VALUE;
        this.j = 64;
        this.k = 67108864;
        this.a = bArr;
        this.b = i + i2;
        this.d = i;
        this.e = null;
    }

    public static b a(InputStream inputStream) {
        return new b(inputStream);
    }

    public static b a(byte[] bArr, int i, int i2) {
        return new b(bArr, i, i2);
    }

    private boolean a(boolean z) {
        if (this.d < this.b) {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        } else if (this.g + this.b != this.h) {
            this.g += this.b;
            this.d = 0;
            this.b = this.e == null ? -1 : this.e.read(this.a);
            if (this.b == 0 || this.b < -1) {
                throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.b + "\nThe InputStream implementation is buggy.");
            } else if (this.b == -1) {
                this.b = 0;
                if (!z) {
                    return false;
                }
                throw d.a();
            } else {
                p();
                int i = (this.g + this.b) + this.c;
                if (i <= this.k && i >= 0) {
                    return true;
                }
                throw d.h();
            }
        } else if (!z) {
            return false;
        } else {
            throw d.a();
        }
    }

    private void p() {
        this.b += this.c;
        int i = this.g + this.b;
        if (i > this.h) {
            this.c = i - this.h;
            this.b -= this.c;
            return;
        }
        this.c = 0;
    }

    public int a() {
        if (n()) {
            this.f = 0;
            return 0;
        }
        this.f = j();
        if (this.f != 0) {
            return this.f;
        }
        throw d.d();
    }

    public void a(int i) {
        if (this.f != i) {
            throw d.e();
        }
    }

    public void a(e eVar) {
        int j = j();
        if (this.i >= this.j) {
            throw d.g();
        }
        j = c(j);
        this.i++;
        eVar.a(this);
        a(0);
        this.i--;
        d(j);
    }

    public void b() {
        int a;
        do {
            a = a();
            if (a == 0) {
                return;
            }
        } while (b(a));
    }

    public boolean b(int i) {
        switch (f.a(i)) {
            case 0:
                e();
                return true;
            case 1:
                m();
                return true;
            case 2:
                f(j());
                return true;
            case 3:
                b();
                a(f.a(f.b(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                l();
                return true;
            default:
                throw d.f();
        }
    }

    public int c(int i) {
        if (i < 0) {
            throw d.b();
        }
        int i2 = (this.g + this.d) + i;
        int i3 = this.h;
        if (i2 > i3) {
            throw d.a();
        }
        this.h = i2;
        p();
        return i3;
    }

    public long c() {
        return k();
    }

    public long d() {
        return k();
    }

    public void d(int i) {
        this.h = i;
        p();
    }

    public int e() {
        return j();
    }

    public byte[] e(int i) {
        if (i < 0) {
            throw d.b();
        } else if ((this.g + this.d) + i > this.h) {
            f((this.h - this.g) - this.d);
            throw d.a();
        } else if (i <= this.b - this.d) {
            Object obj = new byte[i];
            System.arraycopy(this.a, this.d, obj, 0, i);
            this.d += i;
            return obj;
        } else if (i < 4096) {
            Object obj2 = new byte[i];
            r0 = this.b - this.d;
            System.arraycopy(this.a, this.d, obj2, 0, r0);
            this.d = this.b;
            a(true);
            while (i - r0 > this.b) {
                System.arraycopy(this.a, 0, obj2, r0, this.b);
                r0 += this.b;
                this.d = this.b;
                a(true);
            }
            System.arraycopy(this.a, 0, obj2, r0, i - r0);
            this.d = i - r0;
            return obj2;
        } else {
            int read;
            int i2 = this.d;
            int i3 = this.b;
            this.g += this.b;
            this.d = 0;
            this.b = 0;
            r0 = i - (i3 - i2);
            Vector vector = new Vector();
            int i4 = r0;
            while (i4 > 0) {
                Object obj3 = new byte[Math.min(i4, 4096)];
                r0 = 0;
                while (r0 < obj3.length) {
                    read = this.e == null ? -1 : this.e.read(obj3, r0, obj3.length - r0);
                    if (read == -1) {
                        throw d.a();
                    }
                    this.g += read;
                    r0 += read;
                }
                r0 = i4 - obj3.length;
                vector.addElement(obj3);
                i4 = r0;
            }
            Object obj4 = new byte[i];
            r0 = i3 - i2;
            System.arraycopy(this.a, i2, obj4, 0, r0);
            int i5 = r0;
            for (read = 0; read < vector.size(); read++) {
                byte[] bArr = (byte[]) vector.elementAt(read);
                System.arraycopy(bArr, 0, obj4, i5, bArr.length);
                i5 += bArr.length;
            }
            return obj4;
        }
    }

    public void f(int i) {
        if (i < 0) {
            throw d.b();
        } else if ((this.g + this.d) + i > this.h) {
            f((this.h - this.g) - this.d);
            throw d.a();
        } else if (i <= this.b - this.d) {
            this.d += i;
        } else {
            int i2 = this.b - this.d;
            this.g += this.b;
            this.d = 0;
            this.b = 0;
            int i3 = i2;
            while (i3 < i) {
                i2 = this.e == null ? -1 : (int) this.e.skip((long) (i - i3));
                if (i2 <= 0) {
                    throw d.a();
                }
                i3 += i2;
                this.g = i2 + this.g;
            }
        }
    }

    public boolean f() {
        return j() != 0;
    }

    public String g() {
        int j = j();
        if (j > this.b - this.d || j <= 0) {
            return new String(e(j), "UTF-8");
        }
        String str = new String(this.a, this.d, j, "UTF-8");
        this.d = j + this.d;
        return str;
    }

    public a h() {
        int j = j();
        if (j > this.b - this.d || j <= 0) {
            return a.a(e(j));
        }
        a a = a.a(this.a, this.d, j);
        this.d = j + this.d;
        return a;
    }

    public int i() {
        return j();
    }

    public int j() {
        byte o = o();
        if (o >= (byte) 0) {
            return o;
        }
        int i = o & 127;
        byte o2 = o();
        if (o2 >= (byte) 0) {
            return i | (o2 << 7);
        }
        i |= (o2 & 127) << 7;
        o2 = o();
        if (o2 >= (byte) 0) {
            return i | (o2 << 14);
        }
        i |= (o2 & 127) << 14;
        o2 = o();
        if (o2 >= (byte) 0) {
            return i | (o2 << 21);
        }
        i |= (o2 & 127) << 21;
        o2 = o();
        i |= o2 << 28;
        if (o2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            if (o() >= (byte) 0) {
                return i;
            }
        }
        throw d.c();
    }

    public long k() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte o = o();
            j |= ((long) (o & 127)) << i;
            if ((o & 128) == 0) {
                return j;
            }
        }
        throw d.c();
    }

    public int l() {
        return (((o() & 255) | ((o() & 255) << 8)) | ((o() & 255) << 16)) | ((o() & 255) << 24);
    }

    public long m() {
        byte o = o();
        byte o2 = o();
        return ((((((((((long) o2) & 255) << 8) | (((long) o) & 255)) | ((((long) o()) & 255) << 16)) | ((((long) o()) & 255) << 24)) | ((((long) o()) & 255) << 32)) | ((((long) o()) & 255) << 40)) | ((((long) o()) & 255) << 48)) | ((((long) o()) & 255) << 56);
    }

    public boolean n() {
        return this.d == this.b && !a(false);
    }

    public byte o() {
        if (this.d == this.b) {
            a(true);
        }
        byte[] bArr = this.a;
        int i = this.d;
        this.d = i + 1;
        return bArr[i];
    }
}
