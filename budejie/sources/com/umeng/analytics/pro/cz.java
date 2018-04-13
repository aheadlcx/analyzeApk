package com.umeng.analytics.pro;

import com.budejie.www.R$styleable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class cz extends df {
    private static final dk d = new dk("");
    private static final da e = new da("", (byte) 0, (short) 0);
    private static final byte[] f = new byte[16];
    private static final byte h = (byte) -126;
    private static final byte i = (byte) 1;
    private static final byte j = (byte) 31;
    private static final byte k = (byte) -32;
    private static final int l = 5;
    byte[] a;
    byte[] b;
    byte[] c;
    private ce m;
    private short n;
    private da o;
    private Boolean p;
    private final long q;
    private byte[] r;

    public static class a implements dh {
        private final long a;

        public a() {
            this.a = -1;
        }

        public a(int i) {
            this.a = (long) i;
        }

        public df a(dt dtVar) {
            return new cz(dtVar, this.a);
        }
    }

    private static class b {
        public static final byte a = (byte) 1;
        public static final byte b = (byte) 2;
        public static final byte c = (byte) 3;
        public static final byte d = (byte) 4;
        public static final byte e = (byte) 5;
        public static final byte f = (byte) 6;
        public static final byte g = (byte) 7;
        public static final byte h = (byte) 8;
        public static final byte i = (byte) 9;
        public static final byte j = (byte) 10;
        public static final byte k = (byte) 11;
        public static final byte l = (byte) 12;

        private b() {
        }
    }

    static {
        f[0] = (byte) 0;
        f[2] = (byte) 1;
        f[3] = (byte) 3;
        f[6] = (byte) 4;
        f[8] = (byte) 5;
        f[10] = (byte) 6;
        f[4] = (byte) 7;
        f[11] = (byte) 8;
        f[15] = (byte) 9;
        f[14] = (byte) 10;
        f[13] = (byte) 11;
        f[12] = (byte) 12;
    }

    public cz(dt dtVar, long j) {
        super(dtVar);
        this.m = new ce(15);
        this.n = (short) 0;
        this.o = null;
        this.p = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.r = new byte[1];
        this.c = new byte[1];
        this.q = j;
    }

    public cz(dt dtVar) {
        this(dtVar, -1);
    }

    public void B() {
        this.m.c();
        this.n = (short) 0;
    }

    public void a(dd ddVar) throws cm {
        b((byte) h);
        d(((ddVar.b << 5) & -32) | 1);
        b(ddVar.c);
        a(ddVar.a);
    }

    public void a(dk dkVar) throws cm {
        this.m.a(this.n);
        this.n = (short) 0;
    }

    public void b() throws cm {
        this.n = this.m.a();
    }

    public void a(da daVar) throws cm {
        if (daVar.b == (byte) 2) {
            this.o = daVar;
        } else {
            a(daVar, (byte) -1);
        }
    }

    private void a(da daVar, byte b) throws cm {
        if (b == (byte) -1) {
            b = e(daVar.b);
        }
        if (daVar.c <= this.n || daVar.c - this.n > 15) {
            b(b);
            a(daVar.c);
        } else {
            d(((daVar.c - this.n) << 4) | b);
        }
        this.n = daVar.c;
    }

    public void d() throws cm {
        b((byte) 0);
    }

    public void a(dc dcVar) throws cm {
        if (dcVar.c == 0) {
            d(0);
            return;
        }
        b(dcVar.c);
        d((e(dcVar.a) << 4) | e(dcVar.b));
    }

    public void a(db dbVar) throws cm {
        a(dbVar.a, dbVar.b);
    }

    public void a(dj djVar) throws cm {
        a(djVar.a, djVar.b);
    }

    public void a(boolean z) throws cm {
        byte b = (byte) 1;
        if (this.o != null) {
            da daVar = this.o;
            if (!z) {
                b = (byte) 2;
            }
            a(daVar, b);
            this.o = null;
            return;
        }
        if (!z) {
            b = (byte) 2;
        }
        b(b);
    }

    public void a(byte b) throws cm {
        b(b);
    }

    public void a(short s) throws cm {
        b(c((int) s));
    }

    public void a(int i) throws cm {
        b(c(i));
    }

    public void a(long j) throws cm {
        b(c(j));
    }

    public void a(double d) throws cm {
        byte[] bArr = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        a(Double.doubleToLongBits(d), bArr, 0);
        this.g.b(bArr);
    }

    public void a(String str) throws cm {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new cm("UTF-8 not supported!");
        }
    }

    public void a(ByteBuffer byteBuffer) throws cm {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i, int i2) throws cm {
        b(i2);
        this.g.b(bArr, i, i2);
    }

    public void a() throws cm {
    }

    public void e() throws cm {
    }

    public void f() throws cm {
    }

    public void g() throws cm {
    }

    public void c() throws cm {
    }

    protected void a(byte b, int i) throws cm {
        if (i <= 14) {
            d((i << 4) | e(b));
            return;
        }
        d(e(b) | R$styleable.Theme_Custom_shape_cmt_like4_bg);
        b(i);
    }

    private void b(int i) throws cm {
        int i2 = 0;
        while ((i & -128) != 0) {
            int i3 = i2 + 1;
            this.a[i2] = (byte) ((i & 127) | 128);
            i >>>= 7;
            i2 = i3;
        }
        int i4 = i2 + 1;
        this.a[i2] = (byte) i;
        this.g.b(this.a, 0, i4);
    }

    private void b(long j) throws cm {
        int i = 0;
        while ((-128 & j) != 0) {
            int i2 = i + 1;
            this.b[i] = (byte) ((int) ((127 & j) | 128));
            j >>>= 7;
            i = i2;
        }
        int i3 = i + 1;
        this.b[i] = (byte) ((int) j);
        this.g.b(this.b, 0, i3);
    }

    private long c(long j) {
        return (j << 1) ^ (j >> 63);
    }

    private int c(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private void a(long j, byte[] bArr, int i) {
        bArr[i + 0] = (byte) ((int) (j & 255));
        bArr[i + 1] = (byte) ((int) ((j >> 8) & 255));
        bArr[i + 2] = (byte) ((int) ((j >> 16) & 255));
        bArr[i + 3] = (byte) ((int) ((j >> 24) & 255));
        bArr[i + 4] = (byte) ((int) ((j >> 32) & 255));
        bArr[i + 5] = (byte) ((int) ((j >> 40) & 255));
        bArr[i + 6] = (byte) ((int) ((j >> 48) & 255));
        bArr[i + 7] = (byte) ((int) ((j >> 56) & 255));
    }

    private void b(byte b) throws cm {
        this.r[0] = b;
        this.g.b(this.r);
    }

    private void d(int i) throws cm {
        b((byte) i);
    }

    public dd h() throws cm {
        byte u = u();
        if (u != h) {
            throw new dg("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(u));
        }
        u = u();
        byte b = (byte) (u & 31);
        if (b != (byte) 1) {
            throw new dg("Expected version 1 but got " + b);
        }
        return new dd(z(), (byte) ((u >> 5) & 3), E());
    }

    public dk j() throws cm {
        this.m.a(this.n);
        this.n = (short) 0;
        return d;
    }

    public void k() throws cm {
        this.n = this.m.a();
    }

    public da l() throws cm {
        byte u = u();
        if (u == (byte) 0) {
            return e;
        }
        short s = (short) ((u & R$styleable.Theme_Custom_shape_cmt_like4_bg) >> 4);
        if (s == (short) 0) {
            s = v();
        } else {
            s = (short) (s + this.n);
        }
        da daVar = new da("", d((byte) (u & 15)), s);
        if (c(u)) {
            this.p = ((byte) (u & 15)) == (byte) 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.n = daVar.c;
        return daVar;
    }

    public dc n() throws cm {
        int E = E();
        int u = E == 0 ? 0 : u();
        return new dc(d((byte) (u >> 4)), d((byte) (u & 15)), E);
    }

    public db p() throws cm {
        byte u = u();
        int i = (u >> 4) & 15;
        if (i == 15) {
            i = E();
        }
        return new db(d(u), i);
    }

    public dj r() throws cm {
        return new dj(p());
    }

    public boolean t() throws cm {
        if (this.p != null) {
            boolean booleanValue = this.p.booleanValue();
            this.p = null;
            return booleanValue;
        } else if (u() != (byte) 1) {
            return false;
        } else {
            return true;
        }
    }

    public byte u() throws cm {
        if (this.g.h() > 0) {
            byte b = this.g.f()[this.g.g()];
            this.g.a(1);
            return b;
        }
        this.g.d(this.c, 0, 1);
        return this.c[0];
    }

    public short v() throws cm {
        return (short) g(E());
    }

    public int w() throws cm {
        return g(E());
    }

    public long x() throws cm {
        return d(F());
    }

    public double y() throws cm {
        byte[] bArr = new byte[8];
        this.g.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    public String z() throws cm {
        int E = E();
        f(E);
        if (E == 0) {
            return "";
        }
        try {
            if (this.g.h() < E) {
                return new String(e(E), "UTF-8");
            }
            String str = new String(this.g.f(), this.g.g(), E, "UTF-8");
            this.g.a(E);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new cm("UTF-8 not supported!");
        }
    }

    public ByteBuffer A() throws cm {
        int E = E();
        f(E);
        if (E == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[E];
        this.g.d(bArr, 0, E);
        return ByteBuffer.wrap(bArr);
    }

    private byte[] e(int i) throws cm {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.g.d(bArr, 0, i);
        return bArr;
    }

    private void f(int i) throws dg {
        if (i < 0) {
            throw new dg("Negative length: " + i);
        } else if (this.q != -1 && ((long) i) > this.q) {
            throw new dg("Length exceeded max allowed: " + i);
        }
    }

    public void i() throws cm {
    }

    public void m() throws cm {
    }

    public void o() throws cm {
    }

    public void q() throws cm {
    }

    public void s() throws cm {
    }

    private int E() throws cm {
        int i = 0;
        int i2;
        if (this.g.h() >= 5) {
            byte[] f = this.g.f();
            int g = this.g.g();
            i2 = 0;
            int i3 = 0;
            while (true) {
                byte b = f[g + i];
                i3 |= (b & 127) << i2;
                if ((b & 128) != 128) {
                    this.g.a(i + 1);
                    return i3;
                }
                i2 += 7;
                i++;
            }
        } else {
            i2 = 0;
            while (true) {
                byte u = u();
                i2 |= (u & 127) << i;
                if ((u & 128) != 128) {
                    return i2;
                }
                i += 7;
            }
        }
    }

    private long F() throws cm {
        long j = null;
        long j2 = 0;
        if (this.g.h() >= 10) {
            int i;
            byte[] f = this.g.f();
            int g = this.g.g();
            long j3 = 0;
            while (true) {
                byte b = f[g + i];
                j2 |= ((long) (b & 127)) << j3;
                if ((b & 128) != 128) {
                    break;
                }
                j3 += 7;
                i++;
            }
            this.g.a(i + 1);
        } else {
            while (true) {
                byte u = u();
                j2 |= ((long) (u & 127)) << j;
                if ((u & 128) != 128) {
                    break;
                }
                j += 7;
            }
        }
        return j2;
    }

    private int g(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    private long d(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private long a(byte[] bArr) {
        return ((((((((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48)) | ((((long) bArr[5]) & 255) << 40)) | ((((long) bArr[4]) & 255) << 32)) | ((((long) bArr[3]) & 255) << 24)) | ((((long) bArr[2]) & 255) << 16)) | ((((long) bArr[1]) & 255) << 8)) | (((long) bArr[0]) & 255);
    }

    private boolean c(byte b) {
        int i = b & 15;
        if (i == 1 || i == 2) {
            return true;
        }
        return false;
    }

    private byte d(byte b) throws dg {
        switch ((byte) (b & 15)) {
            case (byte) 0:
                return (byte) 0;
            case (byte) 1:
            case (byte) 2:
                return (byte) 2;
            case (byte) 3:
                return (byte) 3;
            case (byte) 4:
                return (byte) 6;
            case (byte) 5:
                return (byte) 8;
            case (byte) 6:
                return (byte) 10;
            case (byte) 7:
                return (byte) 4;
            case (byte) 8:
                return (byte) 11;
            case (byte) 9:
                return dm.m;
            case (byte) 10:
                return dm.l;
            case (byte) 11:
                return dm.k;
            case (byte) 12:
                return (byte) 12;
            default:
                throw new dg("don't know what type: " + ((byte) (b & 15)));
        }
    }

    private byte e(byte b) {
        return f[b];
    }
}
