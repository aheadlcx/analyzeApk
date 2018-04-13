package com.umeng.analytics.pro;

import com.umeng.analytics.pro.cz.a;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class cj {
    private final df a;
    private final ds b;

    public cj() {
        this(new a());
    }

    public cj(dh dhVar) {
        this.b = new ds();
        this.a = dhVar.a(this.b);
    }

    public void a(cg cgVar, byte[] bArr) throws cm {
        try {
            this.b.a(bArr);
            cgVar.a(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(cg cgVar, String str, String str2) throws cm {
        try {
            a(cgVar, str.getBytes(str2));
            this.a.B();
        } catch (UnsupportedEncodingException e) {
            throw new cm("JVM DOES NOT SUPPORT ENCODING: " + str2);
        } catch (Throwable th) {
            this.a.B();
        }
    }

    public void a(cg cgVar, byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        try {
            if (j(bArr, cnVar, cnVarArr) != null) {
                cgVar.a(this.a);
            }
            this.b.e();
            this.a.B();
        } catch (Throwable e) {
            throw new cm(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
        }
    }

    public Boolean a(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        return (Boolean) a((byte) 2, bArr, cnVar, cnVarArr);
    }

    public Byte b(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        return (Byte) a((byte) 3, bArr, cnVar, cnVarArr);
    }

    public Double c(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        return (Double) a((byte) 4, bArr, cnVar, cnVarArr);
    }

    public Short d(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        return (Short) a((byte) 6, bArr, cnVar, cnVarArr);
    }

    public Integer e(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        return (Integer) a((byte) 8, bArr, cnVar, cnVarArr);
    }

    public Long f(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        return (Long) a((byte) 10, bArr, cnVar, cnVarArr);
    }

    public String g(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        return (String) a((byte) 11, bArr, cnVar, cnVarArr);
    }

    public ByteBuffer h(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        return (ByteBuffer) a((byte) 100, bArr, cnVar, cnVarArr);
    }

    public Short i(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        try {
            if (j(bArr, cnVar, cnVarArr) != null) {
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
            throw new cm(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
        }
    }

    private Object a(byte b, byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        try {
            da j = j(bArr, cnVar, cnVarArr);
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
            throw new cm(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
        }
    }

    private da j(byte[] bArr, cn cnVar, cn... cnVarArr) throws cm {
        int i = 0;
        this.b.a(bArr);
        cn[] cnVarArr2 = new cn[(cnVarArr.length + 1)];
        cnVarArr2[0] = cnVar;
        for (int i2 = 0; i2 < cnVarArr.length; i2++) {
            cnVarArr2[i2 + 1] = cnVarArr[i2];
        }
        this.a.j();
        da daVar = null;
        while (i < cnVarArr2.length) {
            daVar = this.a.l();
            if (daVar.b == (byte) 0 || daVar.c > cnVarArr2[i].a()) {
                return null;
            }
            if (daVar.c != cnVarArr2[i].a()) {
                di.a(this.a, daVar.b);
                this.a.m();
            } else {
                i++;
                if (i < cnVarArr2.length) {
                    this.a.j();
                }
            }
        }
        return daVar;
    }

    public void a(cg cgVar, String str) throws cm {
        a(cgVar, str.getBytes());
    }
}
