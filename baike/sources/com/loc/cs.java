package com.loc;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public final class cs extends bj {
    Map<String, String> f = null;
    String g = "";
    byte[] h = null;
    byte[] i = null;
    boolean j = false;
    String k = null;
    Map<String, String> l = null;
    boolean m = false;

    public cs(Context context, s sVar) {
        super(context, sVar);
    }

    public final byte[] a_() {
        return this.h;
    }

    public final Map<String, String> b() {
        return this.f;
    }

    public final void b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            if (bArr != null) {
                try {
                    try {
                        byteArrayOutputStream.write(bj.a(bArr));
                        byteArrayOutputStream.write(bArr);
                    } catch (IOException e) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        th.printStackTrace();
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
            this.i = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e22) {
                e22.printStackTrace();
            }
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public final Map<String, String> b_() {
        return this.l;
    }

    public final String c() {
        return this.g;
    }

    public final byte[] e() {
        return this.i;
    }

    public final boolean g() {
        return this.j;
    }

    public final String h() {
        return this.k;
    }

    protected final boolean i() {
        return this.m;
    }
}
