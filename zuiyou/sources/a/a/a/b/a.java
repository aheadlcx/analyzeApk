package a.a.a.b;

import a.a.a.d.c;
import a.a.a.j;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class a extends f {
    private static final k f = new k();
    protected boolean a = false;
    protected boolean b = true;
    protected int c;
    protected boolean d = false;
    private byte[] g = new byte[1];
    private byte[] h = new byte[2];
    private byte[] i = new byte[4];
    private byte[] j = new byte[8];
    private byte[] k = new byte[1];
    private byte[] l = new byte[2];
    private byte[] m = new byte[4];
    private byte[] n = new byte[8];

    public static class a implements j {
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

        public f a(c cVar) {
            f aVar = new a(cVar, this.a, this.b);
            if (this.c != 0) {
                aVar.c(this.c);
            }
            return aVar;
        }
    }

    public a(c cVar, boolean z, boolean z2) {
        super(cVar);
        this.a = z;
        this.b = z2;
    }

    public void a(k kVar) {
    }

    public void a() {
    }

    public void a(c cVar) throws j {
        a(cVar.b);
        a(cVar.c);
    }

    public void b() {
    }

    public void c() throws j {
        a((byte) 0);
    }

    public void a(e eVar) throws j {
        a(eVar.a);
        a(eVar.b);
        a(eVar.c);
    }

    public void d() {
    }

    public void a(d dVar) throws j {
        a(dVar.a);
        a(dVar.b);
    }

    public void e() {
    }

    public void a(byte b) throws j {
        this.g[0] = b;
        this.e.b(this.g, 0, 1);
    }

    public void a(short s) throws j {
        this.h[0] = (byte) ((s >> 8) & 255);
        this.h[1] = (byte) (s & 255);
        this.e.b(this.h, 0, 2);
    }

    public void a(int i) throws j {
        this.i[0] = (byte) ((i >> 24) & 255);
        this.i[1] = (byte) ((i >> 16) & 255);
        this.i[2] = (byte) ((i >> 8) & 255);
        this.i[3] = (byte) (i & 255);
        this.e.b(this.i, 0, 4);
    }

    public void a(long j) throws j {
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

    public void a(String str) throws j {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.e.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new j("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void a(ByteBuffer byteBuffer) throws j {
        int limit = byteBuffer.limit() - byteBuffer.position();
        a(limit);
        this.e.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public k f() {
        return f;
    }

    public void g() {
    }

    public c h() throws j {
        byte q = q();
        return new c("", q, q == (byte) 0 ? (short) 0 : r());
    }

    public void i() {
    }

    public e j() throws j {
        return new e(q(), q(), s());
    }

    public void k() {
    }

    public d l() throws j {
        return new d(q(), s());
    }

    public void m() {
    }

    public h n() throws j {
        return new h(q(), s());
    }

    public void o() {
    }

    public boolean p() throws j {
        return q() == (byte) 1;
    }

    public byte q() throws j {
        if (this.e.d() >= 1) {
            byte b = this.e.b()[this.e.c()];
            this.e.a(1);
            return b;
        }
        a(this.k, 0, 1);
        return this.k[0];
    }

    public short r() throws j {
        int i = 0;
        byte[] bArr = this.l;
        if (this.e.d() >= 2) {
            bArr = this.e.b();
            i = this.e.c();
            this.e.a(2);
        } else {
            a(this.l, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int s() throws j {
        int i = 0;
        byte[] bArr = this.m;
        if (this.e.d() >= 4) {
            bArr = this.e.b();
            i = this.e.c();
            this.e.a(4);
        } else {
            a(this.m, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8));
    }

    public long t() throws j {
        int i = 0;
        byte[] bArr = this.n;
        if (this.e.d() >= 8) {
            bArr = this.e.b();
            i = this.e.c();
            this.e.a(8);
        } else {
            a(this.n, 0, 8);
        }
        return ((long) (bArr[i + 7] & 255)) | (((((((((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48)) | (((long) (bArr[i + 2] & 255)) << 40)) | (((long) (bArr[i + 3] & 255)) << 32)) | (((long) (bArr[i + 4] & 255)) << 24)) | (((long) (bArr[i + 5] & 255)) << 16)) | (((long) (bArr[i + 6] & 255)) << 8));
    }

    public double u() throws j {
        return Double.longBitsToDouble(t());
    }

    public String v() throws j {
        int s = s();
        if (this.e.d() < s) {
            return b(s);
        }
        try {
            String str = new String(this.e.b(), this.e.c(), s, "UTF-8");
            this.e.a(s);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new j("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public String b(int i) throws j {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.e.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new j("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer w() throws j {
        int s = s();
        d(s);
        if (this.e.d() >= s) {
            ByteBuffer wrap = ByteBuffer.wrap(this.e.b(), this.e.c(), s);
            this.e.a(s);
            return wrap;
        }
        byte[] bArr = new byte[s];
        this.e.d(bArr, 0, s);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i, int i2) throws j {
        d(i2);
        return this.e.d(bArr, i, i2);
    }

    public void c(int i) {
        this.c = i;
        this.d = true;
    }

    protected void d(int i) throws j {
        if (i < 0) {
            throw new i("Negative length: " + i);
        } else if (this.d) {
            this.c -= i;
            if (this.c < 0) {
                throw new i("Message length exceeded: " + i);
            }
        }
    }
}
