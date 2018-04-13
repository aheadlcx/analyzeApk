package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ad extends ak {
    private static final ap f = new ap();
    protected boolean a;
    protected boolean b;
    protected int c;
    protected boolean d;
    private byte[] g;
    private byte[] h;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;

    public static class a implements am {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.a = false;
            this.b = true;
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        public ak a(ay ayVar) {
            ak adVar = new ad(ayVar, this.a, this.b);
            if (this.c != 0) {
                adVar.c(this.c);
            }
            return adVar;
        }
    }

    public ad(ay ayVar) {
        this(ayVar, false, true);
    }

    public ad(ay ayVar, boolean z, boolean z2) {
        super(ayVar);
        this.a = false;
        this.b = true;
        this.d = false;
        this.g = new byte[1];
        this.h = new byte[2];
        this.i = new byte[4];
        this.j = new byte[8];
        this.k = new byte[1];
        this.l = new byte[2];
        this.m = new byte[4];
        this.n = new byte[8];
        this.a = z;
        this.b = z2;
    }

    public void a(ai aiVar) throws r {
        if (this.b) {
            a(-2147418112 | aiVar.b);
            a(aiVar.a);
            a(aiVar.c);
            return;
        }
        a(aiVar.a);
        a(aiVar.b);
        a(aiVar.c);
    }

    public void a() {
    }

    public void a(ap apVar) {
    }

    public void b() {
    }

    public void a(af afVar) throws r {
        a(afVar.b);
        a(afVar.c);
    }

    public void c() {
    }

    public void d() throws r {
        a((byte) 0);
    }

    public void a(ah ahVar) throws r {
        a(ahVar.a);
        a(ahVar.b);
        a(ahVar.c);
    }

    public void e() {
    }

    public void a(ag agVar) throws r {
        a(agVar.a);
        a(agVar.b);
    }

    public void f() {
    }

    public void a(ao aoVar) throws r {
        a(aoVar.a);
        a(aoVar.b);
    }

    public void g() {
    }

    public void a(boolean z) throws r {
        a(z ? (byte) 1 : (byte) 0);
    }

    public void a(byte b) throws r {
        this.g[0] = b;
        this.e.b(this.g, 0, 1);
    }

    public void a(short s) throws r {
        this.h[0] = (byte) ((s >> 8) & 255);
        this.h[1] = (byte) (s & 255);
        this.e.b(this.h, 0, 2);
    }

    public void a(int i) throws r {
        this.i[0] = (byte) ((i >> 24) & 255);
        this.i[1] = (byte) ((i >> 16) & 255);
        this.i[2] = (byte) ((i >> 8) & 255);
        this.i[3] = (byte) (i & 255);
        this.e.b(this.i, 0, 4);
    }

    public void a(long j) throws r {
        this.j[0] = (byte) ((int) ((j >> 56) & 255));
        this.j[1] = (byte) ((int) ((j >> 48) & 255));
        this.j[2] = (byte) ((int) ((j >> 40) & 255));
        this.j[3] = (byte) ((int) ((j >> 32) & 255));
        this.j[4] = (byte) ((int) ((j >> 24) & 255));
        this.j[5] = (byte) ((int) ((j >> 16) & 255));
        this.j[6] = (byte) ((int) ((j >> 8) & 255));
        this.j[7] = (byte) ((int) (255 & j));
        this.e.b(this.j, 0, 8);
    }

    public void a(double d) throws r {
        a(Double.doubleToLongBits(d));
    }

    public void a(String str) throws r {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.e.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new r("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void a(ByteBuffer byteBuffer) throws r {
        int limit = byteBuffer.limit() - byteBuffer.position();
        a(limit);
        this.e.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public ai h() throws r {
        int w = w();
        if (w < 0) {
            if ((-65536 & w) == -2147418112) {
                return new ai(z(), (byte) (w & 255), w());
            }
            throw new al(4, "Bad version in readMessageBegin");
        } else if (!this.a) {
            return new ai(b(w), u(), w());
        } else {
            throw new al(4, "Missing version in readMessageBegin, old client?");
        }
    }

    public void i() {
    }

    public ap j() {
        return f;
    }

    public void k() {
    }

    public af l() throws r {
        byte u = u();
        return new af("", u, u == (byte) 0 ? (short) 0 : v());
    }

    public void m() {
    }

    public ah n() throws r {
        return new ah(u(), u(), w());
    }

    public void o() {
    }

    public ag p() throws r {
        return new ag(u(), w());
    }

    public void q() {
    }

    public ao r() throws r {
        return new ao(u(), w());
    }

    public void s() {
    }

    public boolean t() throws r {
        return u() == (byte) 1;
    }

    public byte u() throws r {
        if (this.e.h() >= 1) {
            byte b = this.e.f()[this.e.g()];
            this.e.a(1);
            return b;
        }
        a(this.k, 0, 1);
        return this.k[0];
    }

    public short v() throws r {
        int i = 0;
        byte[] bArr = this.l;
        if (this.e.h() >= 2) {
            bArr = this.e.f();
            i = this.e.g();
            this.e.a(2);
        } else {
            a(this.l, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int w() throws r {
        int i = 0;
        byte[] bArr = this.m;
        if (this.e.h() >= 4) {
            bArr = this.e.f();
            i = this.e.g();
            this.e.a(4);
        } else {
            a(this.m, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8));
    }

    public long x() throws r {
        int i = 0;
        byte[] bArr = this.n;
        if (this.e.h() >= 8) {
            bArr = this.e.f();
            i = this.e.g();
            this.e.a(8);
        } else {
            a(this.n, 0, 8);
        }
        return ((long) (bArr[i + 7] & 255)) | (((((((((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48)) | (((long) (bArr[i + 2] & 255)) << 40)) | (((long) (bArr[i + 3] & 255)) << 32)) | (((long) (bArr[i + 4] & 255)) << 24)) | (((long) (bArr[i + 5] & 255)) << 16)) | (((long) (bArr[i + 6] & 255)) << 8));
    }

    public double y() throws r {
        return Double.longBitsToDouble(x());
    }

    public String z() throws r {
        int w = w();
        if (this.e.h() < w) {
            return b(w);
        }
        try {
            String str = new String(this.e.f(), this.e.g(), w, "UTF-8");
            this.e.a(w);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new r("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public String b(int i) throws r {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.e.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new r("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer A() throws r {
        int w = w();
        d(w);
        if (this.e.h() >= w) {
            ByteBuffer wrap = ByteBuffer.wrap(this.e.f(), this.e.g(), w);
            this.e.a(w);
            return wrap;
        }
        byte[] bArr = new byte[w];
        this.e.d(bArr, 0, w);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i, int i2) throws r {
        d(i2);
        return this.e.d(bArr, i, i2);
    }

    public void c(int i) {
        this.c = i;
        this.d = true;
    }

    protected void d(int i) throws r {
        if (i < 0) {
            throw new al("Negative length: " + i);
        } else if (this.d) {
            this.c -= i;
            if (this.c < 0) {
                throw new al("Message length exceeded: " + i);
            }
        }
    }
}
