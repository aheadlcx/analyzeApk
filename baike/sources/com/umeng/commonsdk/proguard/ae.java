package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ae extends ak {
    private static final ap d = new ap("");
    private static final af f = new af("", (byte) 0, (short) 0);
    private static final byte[] g = new byte[16];
    byte[] a;
    byte[] b;
    byte[] c;
    private j h;
    private short i;
    private af j;
    private Boolean k;
    private final long l;
    private byte[] m;

    public static class a implements am {
        private final long a;

        public a() {
            this.a = -1;
        }

        public a(int i) {
            this.a = (long) i;
        }

        public ak a(ay ayVar) {
            return new ae(ayVar, this.a);
        }
    }

    static {
        g[0] = (byte) 0;
        g[2] = (byte) 1;
        g[3] = (byte) 3;
        g[6] = (byte) 4;
        g[8] = (byte) 5;
        g[10] = (byte) 6;
        g[4] = (byte) 7;
        g[11] = (byte) 8;
        g[15] = (byte) 9;
        g[14] = (byte) 10;
        g[13] = (byte) 11;
        g[12] = (byte) 12;
    }

    public ae(ay ayVar, long j) {
        super(ayVar);
        this.h = new j(15);
        this.i = (short) 0;
        this.j = null;
        this.k = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.m = new byte[1];
        this.c = new byte[1];
        this.l = j;
    }

    public ae(ay ayVar) {
        this(ayVar, -1);
    }

    public void B() {
        this.h.c();
        this.i = (short) 0;
    }

    public void a(ai aiVar) throws r {
        b((byte) -126);
        d(((aiVar.b << 5) & -32) | 1);
        b(aiVar.c);
        a(aiVar.a);
    }

    public void a(ap apVar) throws r {
        this.h.a(this.i);
        this.i = (short) 0;
    }

    public void b() throws r {
        this.i = this.h.a();
    }

    public void a(af afVar) throws r {
        if (afVar.b == (byte) 2) {
            this.j = afVar;
        } else {
            a(afVar, (byte) -1);
        }
    }

    private void a(af afVar, byte b) throws r {
        if (b == (byte) -1) {
            b = e(afVar.b);
        }
        if (afVar.c <= this.i || afVar.c - this.i > 15) {
            b(b);
            a(afVar.c);
        } else {
            d(((afVar.c - this.i) << 4) | b);
        }
        this.i = afVar.c;
    }

    public void d() throws r {
        b((byte) 0);
    }

    public void a(ah ahVar) throws r {
        if (ahVar.c == 0) {
            d(0);
            return;
        }
        b(ahVar.c);
        d((e(ahVar.a) << 4) | e(ahVar.b));
    }

    public void a(ag agVar) throws r {
        a(agVar.a, agVar.b);
    }

    public void a(ao aoVar) throws r {
        a(aoVar.a, aoVar.b);
    }

    public void a(boolean z) throws r {
        byte b = (byte) 1;
        if (this.j != null) {
            af afVar = this.j;
            if (!z) {
                b = (byte) 2;
            }
            a(afVar, b);
            this.j = null;
            return;
        }
        if (!z) {
            b = (byte) 2;
        }
        b(b);
    }

    public void a(byte b) throws r {
        b(b);
    }

    public void a(short s) throws r {
        b(c((int) s));
    }

    public void a(int i) throws r {
        b(c(i));
    }

    public void a(long j) throws r {
        b(c(j));
    }

    public void a(double d) throws r {
        byte[] bArr = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        a(Double.doubleToLongBits(d), bArr, 0);
        this.e.b(bArr);
    }

    public void a(String str) throws r {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new r("UTF-8 not supported!");
        }
    }

    public void a(ByteBuffer byteBuffer) throws r {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i, int i2) throws r {
        b(i2);
        this.e.b(bArr, i, i2);
    }

    public void a() throws r {
    }

    public void e() throws r {
    }

    public void f() throws r {
    }

    public void g() throws r {
    }

    public void c() throws r {
    }

    protected void a(byte b, int i) throws r {
        if (i <= 14) {
            d((i << 4) | e(b));
            return;
        }
        d(e(b) | 240);
        b(i);
    }

    private void b(int i) throws r {
        int i2 = 0;
        while ((i & -128) != 0) {
            int i3 = i2 + 1;
            this.a[i2] = (byte) ((i & 127) | 128);
            i >>>= 7;
            i2 = i3;
        }
        int i4 = i2 + 1;
        this.a[i2] = (byte) i;
        this.e.b(this.a, 0, i4);
    }

    private void b(long j) throws r {
        int i = 0;
        while ((-128 & j) != 0) {
            int i2 = i + 1;
            this.b[i] = (byte) ((int) ((127 & j) | 128));
            j >>>= 7;
            i = i2;
        }
        int i3 = i + 1;
        this.b[i] = (byte) ((int) j);
        this.e.b(this.b, 0, i3);
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

    private void b(byte b) throws r {
        this.m[0] = b;
        this.e.b(this.m);
    }

    private void d(int i) throws r {
        b((byte) i);
    }

    public ai h() throws r {
        byte u = u();
        if (u != (byte) -126) {
            throw new al("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(u));
        }
        u = u();
        byte b = (byte) (u & 31);
        if (b != (byte) 1) {
            throw new al("Expected version 1 but got " + b);
        }
        return new ai(z(), (byte) ((u >> 5) & 3), E());
    }

    public ap j() throws r {
        this.h.a(this.i);
        this.i = (short) 0;
        return d;
    }

    public void k() throws r {
        this.i = this.h.a();
    }

    public af l() throws r {
        byte u = u();
        if (u == (byte) 0) {
            return f;
        }
        short s = (short) ((u & 240) >> 4);
        if (s == (short) 0) {
            s = v();
        } else {
            s = (short) (s + this.i);
        }
        af afVar = new af("", d((byte) (u & 15)), s);
        if (c(u)) {
            this.k = ((byte) (u & 15)) == (byte) 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.i = afVar.c;
        return afVar;
    }

    public ah n() throws r {
        int E = E();
        int u = E == 0 ? 0 : u();
        return new ah(d((byte) (u >> 4)), d((byte) (u & 15)), E);
    }

    public ag p() throws r {
        byte u = u();
        int i = (u >> 4) & 15;
        if (i == 15) {
            i = E();
        }
        return new ag(d(u), i);
    }

    public ao r() throws r {
        return new ao(p());
    }

    public boolean t() throws r {
        if (this.k != null) {
            boolean booleanValue = this.k.booleanValue();
            this.k = null;
            return booleanValue;
        } else if (u() != (byte) 1) {
            return false;
        } else {
            return true;
        }
    }

    public byte u() throws r {
        if (this.e.h() > 0) {
            byte b = this.e.f()[this.e.g()];
            this.e.a(1);
            return b;
        }
        this.e.d(this.c, 0, 1);
        return this.c[0];
    }

    public short v() throws r {
        return (short) g(E());
    }

    public int w() throws r {
        return g(E());
    }

    public long x() throws r {
        return d(F());
    }

    public double y() throws r {
        byte[] bArr = new byte[8];
        this.e.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    public String z() throws r {
        int E = E();
        f(E);
        if (E == 0) {
            return "";
        }
        try {
            if (this.e.h() < E) {
                return new String(e(E), "UTF-8");
            }
            String str = new String(this.e.f(), this.e.g(), E, "UTF-8");
            this.e.a(E);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new r("UTF-8 not supported!");
        }
    }

    public ByteBuffer A() throws r {
        int E = E();
        f(E);
        if (E == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[E];
        this.e.d(bArr, 0, E);
        return ByteBuffer.wrap(bArr);
    }

    private byte[] e(int i) throws r {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.e.d(bArr, 0, i);
        return bArr;
    }

    private void f(int i) throws al {
        if (i < 0) {
            throw new al("Negative length: " + i);
        } else if (this.l != -1 && ((long) i) > this.l) {
            throw new al("Length exceeded max allowed: " + i);
        }
    }

    public void i() throws r {
    }

    public void m() throws r {
    }

    public void o() throws r {
    }

    public void q() throws r {
    }

    public void s() throws r {
    }

    private int E() throws r {
        int i = 0;
        int i2;
        if (this.e.h() >= 5) {
            byte[] f = this.e.f();
            int g = this.e.g();
            i2 = 0;
            int i3 = 0;
            while (true) {
                byte b = f[g + i];
                i3 |= (b & 127) << i2;
                if ((b & 128) != 128) {
                    this.e.a(i + 1);
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

    private long F() throws r {
        long j = null;
        long j2 = 0;
        if (this.e.h() >= 10) {
            int i;
            byte[] f = this.e.f();
            int g = this.e.g();
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
            this.e.a(i + 1);
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

    private byte d(byte b) throws al {
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
                return ar.m;
            case (byte) 10:
                return (byte) 14;
            case (byte) 11:
                return (byte) 13;
            case (byte) 12:
                return (byte) 12;
            default:
                throw new al("don't know what type: " + ((byte) (b & 15)));
        }
    }

    private byte e(byte b) {
        return g[b];
    }
}
