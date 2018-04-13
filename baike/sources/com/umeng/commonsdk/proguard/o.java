package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.ae.a;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class o {
    private final ak a;
    private final ax b;

    public o() {
        this(new a());
    }

    public o(am amVar) {
        this.b = new ax();
        this.a = amVar.a(this.b);
    }

    public void a(l lVar, byte[] bArr) throws r {
        try {
            this.b.a(bArr);
            lVar.read(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(l lVar, String str, String str2) throws r {
        try {
            a(lVar, str.getBytes(str2));
            this.a.B();
        } catch (UnsupportedEncodingException e) {
            throw new r("JVM DOES NOT SUPPORT ENCODING: " + str2);
        } catch (Throwable th) {
            this.a.B();
        }
    }

    public void a(l lVar, byte[] bArr, s sVar, s... sVarArr) throws r {
        try {
            if (j(bArr, sVar, sVarArr) != null) {
                lVar.read(this.a);
            }
            this.b.e();
            this.a.B();
        } catch (Throwable e) {
            throw new r(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
        }
    }

    public Boolean a(byte[] bArr, s sVar, s... sVarArr) throws r {
        return (Boolean) a((byte) 2, bArr, sVar, sVarArr);
    }

    public Byte b(byte[] bArr, s sVar, s... sVarArr) throws r {
        return (Byte) a((byte) 3, bArr, sVar, sVarArr);
    }

    public Double c(byte[] bArr, s sVar, s... sVarArr) throws r {
        return (Double) a((byte) 4, bArr, sVar, sVarArr);
    }

    public Short d(byte[] bArr, s sVar, s... sVarArr) throws r {
        return (Short) a((byte) 6, bArr, sVar, sVarArr);
    }

    public Integer e(byte[] bArr, s sVar, s... sVarArr) throws r {
        return (Integer) a((byte) 8, bArr, sVar, sVarArr);
    }

    public Long f(byte[] bArr, s sVar, s... sVarArr) throws r {
        return (Long) a((byte) 10, bArr, sVar, sVarArr);
    }

    public String g(byte[] bArr, s sVar, s... sVarArr) throws r {
        return (String) a((byte) 11, bArr, sVar, sVarArr);
    }

    public ByteBuffer h(byte[] bArr, s sVar, s... sVarArr) throws r {
        return (ByteBuffer) a((byte) 100, bArr, sVar, sVarArr);
    }

    public Short i(byte[] bArr, s sVar, s... sVarArr) throws r {
        try {
            if (j(bArr, sVar, sVarArr) != null) {
                this.a.j();
                Short valueOf = Short.valueOf(this.a.l().c);
                this.b.e();
                this.a.B();
                return valueOf;
            }
            this.b.e();
            this.a.B();
            return null;
        } catch (Throwable e) {
            throw new r(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
        }
    }

    private Object a(byte b, byte[] bArr, s sVar, s... sVarArr) throws r {
        try {
            af j = j(bArr, sVar, sVarArr);
            if (j != null) {
                Object valueOf;
                switch (b) {
                    case (byte) 2:
                        if (j.b == (byte) 2) {
                            valueOf = Boolean.valueOf(this.a.t());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 3:
                        if (j.b == (byte) 3) {
                            valueOf = Byte.valueOf(this.a.u());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 4:
                        if (j.b == (byte) 4) {
                            valueOf = Double.valueOf(this.a.y());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 6:
                        if (j.b == (byte) 6) {
                            valueOf = Short.valueOf(this.a.v());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 8:
                        if (j.b == (byte) 8) {
                            valueOf = Integer.valueOf(this.a.w());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 10:
                        if (j.b == (byte) 10) {
                            valueOf = Long.valueOf(this.a.x());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 11:
                        if (j.b == (byte) 11) {
                            valueOf = this.a.z();
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 100:
                        if (j.b == (byte) 11) {
                            valueOf = this.a.A();
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                }
            }
            this.b.e();
            this.a.B();
            return null;
        } catch (Throwable e) {
            throw new r(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
        }
    }

    private af j(byte[] bArr, s sVar, s... sVarArr) throws r {
        int i = 0;
        this.b.a(bArr);
        s[] sVarArr2 = new s[(sVarArr.length + 1)];
        sVarArr2[0] = sVar;
        for (int i2 = 0; i2 < sVarArr.length; i2++) {
            sVarArr2[i2 + 1] = sVarArr[i2];
        }
        this.a.j();
        af afVar = null;
        while (i < sVarArr2.length) {
            afVar = this.a.l();
            if (afVar.b == (byte) 0 || afVar.c > sVarArr2[i].a()) {
                return null;
            }
            if (afVar.c != sVarArr2[i].a()) {
                an.a(this.a, afVar.b);
                this.a.m();
            } else {
                i++;
                if (i < sVarArr2.length) {
                    this.a.j();
                }
            }
        }
        return afVar;
    }

    public void a(l lVar, String str) throws r {
        a(lVar, str.getBytes());
    }
}
