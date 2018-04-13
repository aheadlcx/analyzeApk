package a.a.a.b;

import a.a.a.d.c;
import a.a.a.j;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.mozilla.classfile.ByteCode;

public class b extends f {
    private static final k d = new k("");
    private static final c f = new c("", (byte) 0, (short) 0);
    private static final byte[] g = new byte[16];
    byte[] a;
    byte[] b;
    byte[] c;
    private a.a.a.b h;
    private short i;
    private c j;
    private Boolean k;
    private final long l;
    private byte[] m;

    public static class a implements j {
        private final long a;

        public a() {
            this.a = -1;
        }

        public a(int i) {
            this.a = (long) i;
        }

        public f a(c cVar) {
            return new b(cVar, this.a);
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
        g[13] = ByteCode.T_LONG;
        g[12] = (byte) 12;
    }

    public b(c cVar, long j) {
        super(cVar);
        this.h = new a.a.a.b(15);
        this.i = (short) 0;
        this.j = null;
        this.k = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.m = new byte[1];
        this.c = new byte[1];
        this.l = j;
    }

    public b(c cVar) {
        this(cVar, -1);
    }

    public void x() {
        this.h.b();
        this.i = (short) 0;
    }

    public void a(k kVar) throws j {
        this.h.a(this.i);
        this.i = (short) 0;
    }

    public void a() throws j {
        this.i = this.h.a();
    }

    public void a(c cVar) throws j {
        if (cVar.b == (byte) 2) {
            this.j = cVar;
        } else {
            a(cVar, (byte) -1);
        }
    }

    private void a(c cVar, byte b) throws j {
        if (b == (byte) -1) {
            b = e(cVar.b);
        }
        if (cVar.c <= this.i || cVar.c - this.i > 15) {
            b(b);
            a(cVar.c);
        } else {
            d(((cVar.c - this.i) << 4) | b);
        }
        this.i = cVar.c;
    }

    public void c() throws j {
        b((byte) 0);
    }

    public void a(e eVar) throws j {
        if (eVar.c == 0) {
            d(0);
            return;
        }
        b(eVar.c);
        d((e(eVar.a) << 4) | e(eVar.b));
    }

    public void a(d dVar) throws j {
        a(dVar.a, dVar.b);
    }

    public void a(byte b) throws j {
        b(b);
    }

    public void a(short s) throws j {
        b(c((int) s));
    }

    public void a(int i) throws j {
        b(c(i));
    }

    public void a(long j) throws j {
        b(c(j));
    }

    public void a(String str) throws j {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new j("UTF-8 not supported!");
        }
    }

    public void a(ByteBuffer byteBuffer) throws j {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i, int i2) throws j {
        b(i2);
        this.e.b(bArr, i, i2);
    }

    public void d() throws j {
    }

    public void e() throws j {
    }

    public void b() throws j {
    }

    protected void a(byte b, int i) throws j {
        if (i <= 14) {
            d((i << 4) | e(b));
            return;
        }
        d(e(b) | 240);
        b(i);
    }

    private void b(int i) throws j {
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

    private void b(long j) throws j {
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

    private void b(byte b) throws j {
        this.m[0] = b;
        this.e.b(this.m);
    }

    private void d(int i) throws j {
        b((byte) i);
    }

    public k f() throws j {
        this.h.a(this.i);
        this.i = (short) 0;
        return d;
    }

    public void g() throws j {
        this.i = this.h.a();
    }

    public c h() throws j {
        byte q = q();
        if (q == (byte) 0) {
            return f;
        }
        short s = (short) ((q & 240) >> 4);
        if (s == (short) 0) {
            s = r();
        } else {
            s = (short) (s + this.i);
        }
        c cVar = new c("", d((byte) (q & 15)), s);
        if (c(q)) {
            this.k = ((byte) (q & 15)) == (byte) 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.i = cVar.c;
        return cVar;
    }

    public e j() throws j {
        int z = z();
        int q = z == 0 ? 0 : q();
        return new e(d((byte) (q >> 4)), d((byte) (q & 15)), z);
    }

    public d l() throws j {
        byte q = q();
        int i = (q >> 4) & 15;
        if (i == 15) {
            i = z();
        }
        return new d(d(q), i);
    }

    public h n() throws j {
        return new h(l());
    }

    public boolean p() throws j {
        if (this.k != null) {
            boolean booleanValue = this.k.booleanValue();
            this.k = null;
            return booleanValue;
        } else if (q() != (byte) 1) {
            return false;
        } else {
            return true;
        }
    }

    public byte q() throws j {
        if (this.e.d() > 0) {
            byte b = this.e.b()[this.e.c()];
            this.e.a(1);
            return b;
        }
        this.e.d(this.c, 0, 1);
        return this.c[0];
    }

    public short r() throws j {
        return (short) g(z());
    }

    public int s() throws j {
        return g(z());
    }

    public long t() throws j {
        return d(A());
    }

    public double u() throws j {
        byte[] bArr = new byte[8];
        this.e.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    public String v() throws j {
        int z = z();
        f(z);
        if (z == 0) {
            return "";
        }
        try {
            if (this.e.d() < z) {
                return new String(e(z), "UTF-8");
            }
            String str = new String(this.e.b(), this.e.c(), z, "UTF-8");
            this.e.a(z);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new j("UTF-8 not supported!");
        }
    }

    public ByteBuffer w() throws j {
        int z = z();
        f(z);
        if (z == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[z];
        this.e.d(bArr, 0, z);
        return ByteBuffer.wrap(bArr);
    }

    private byte[] e(int i) throws j {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.e.d(bArr, 0, i);
        return bArr;
    }

    private void f(int i) throws i {
        if (i < 0) {
            throw new i("Negative length: " + i);
        } else if (this.l != -1 && ((long) i) > this.l) {
            throw new i("Length exceeded max allowed: " + i);
        }
    }

    public void i() throws j {
    }

    public void k() throws j {
    }

    public void m() throws j {
    }

    public void o() throws j {
    }

    private int z() throws j {
        int i = 0;
        int i2;
        if (this.e.d() >= 5) {
            byte[] b = this.e.b();
            int c = this.e.c();
            i2 = 0;
            int i3 = 0;
            while (true) {
                byte b2 = b[c + i];
                i3 |= (b2 & 127) << i2;
                if ((b2 & 128) != 128) {
                    this.e.a(i + 1);
                    return i3;
                }
                i2 += 7;
                i++;
            }
        } else {
            i2 = 0;
            while (true) {
                byte q = q();
                i2 |= (q & 127) << i;
                if ((q & 128) != 128) {
                    return i2;
                }
                i += 7;
            }
        }
    }

    private long A() throws j {
        long j = null;
        long j2 = 0;
        if (this.e.d() >= 10) {
            int i;
            byte[] b = this.e.b();
            int c = this.e.c();
            long j3 = 0;
            while (true) {
                byte b2 = b[c + i];
                j2 |= ((long) (b2 & 127)) << j3;
                if ((b2 & 128) != 128) {
                    break;
                }
                j3 += 7;
                i++;
            }
            this.e.a(i + 1);
        } else {
            while (true) {
                byte q = q();
                j2 |= ((long) (q & 127)) << j;
                if ((q & 128) != 128) {
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

    private byte d(byte b) throws i {
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
                return ByteCode.T_LONG;
            case (byte) 9:
                return (byte) 15;
            case (byte) 10:
                return (byte) 14;
            case (byte) 11:
                return (byte) 13;
            case (byte) 12:
                return (byte) 12;
            default:
                throw new i("don't know what type: " + ((byte) (b & 15)));
        }
    }

    private byte e(byte b) {
        return g[b];
    }
}
