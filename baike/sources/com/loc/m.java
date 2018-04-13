package com.loc;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public final class m {

    private static class a {
        String a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        String l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;
        String u;
        String v;
        String w;
        String x;
        String y;

        private a() {
        }
    }

    public static String a() {
        String str;
        Throwable th;
        Throwable th2;
        String str2 = null;
        try {
            str2 = String.valueOf(System.currentTimeMillis());
            try {
                str = "1";
                if (!k.a()) {
                    str = "0";
                }
                int length = str2.length();
                str = str2.substring(0, length - 2) + str + str2.substring(length - 1);
            } catch (Throwable th3) {
                th = th3;
                str = str2;
                th2 = th;
                w.a(th2, "CInfo", "getTS");
                return str;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
            w.a(th2, "CInfo", "getTS");
            return str;
        }
        return str;
    }

    public static String a(Context context, String str, String str2) {
        String str3 = null;
        try {
            str3 = p.b(k.e(context) + Config.TRACE_TODAY_VISIT_SPLIT + str.substring(0, str.length() - 3) + Config.TRACE_TODAY_VISIT_SPLIT + str2);
        } catch (Throwable th) {
            w.a(th, "CInfo", "Scode");
        }
        return str3;
    }

    private static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            t.a(byteArrayOutputStream, (byte) 0, new byte[0]);
        } else {
            t.a(byteArrayOutputStream, str.getBytes().length > 255 ? (byte) -1 : (byte) str.getBytes().length, t.a(str));
        }
    }

    public static byte[] a(Context context, boolean z) {
        try {
            a aVar = new a();
            aVar.a = n.q(context);
            aVar.b = n.i(context);
            String f = n.f(context);
            if (f == null) {
                f = "";
            }
            aVar.c = f;
            aVar.d = k.c(context);
            aVar.e = Build.MODEL;
            aVar.f = Build.MANUFACTURER;
            aVar.g = Build.DEVICE;
            aVar.h = k.b(context);
            aVar.i = k.d(context);
            aVar.j = String.valueOf(VERSION.SDK_INT);
            aVar.k = n.r(context);
            aVar.l = n.p(context);
            aVar.m = n.m(context);
            aVar.n = n.l(context);
            aVar.o = n.s(context);
            aVar.p = n.k(context);
            if (z) {
                aVar.q = "";
            } else {
                aVar.q = n.h(context);
            }
            if (z) {
                aVar.r = "";
            } else {
                aVar.r = n.g(context);
            }
            if (z) {
                aVar.s = "";
                aVar.t = "";
            } else {
                String[] j = n.j(context);
                aVar.s = j[0];
                aVar.t = j[1];
            }
            aVar.w = n.a();
            return a(aVar);
        } catch (Throwable th) {
            w.a(th, "CInfo", "getGZipXInfo");
            return null;
        }
    }

    private static byte[] a(a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                a(byteArrayOutputStream, aVar.a);
                a(byteArrayOutputStream, aVar.b);
                a(byteArrayOutputStream, aVar.c);
                a(byteArrayOutputStream, aVar.d);
                a(byteArrayOutputStream, aVar.e);
                a(byteArrayOutputStream, aVar.f);
                a(byteArrayOutputStream, aVar.g);
                a(byteArrayOutputStream, aVar.h);
                a(byteArrayOutputStream, aVar.i);
                a(byteArrayOutputStream, aVar.j);
                a(byteArrayOutputStream, aVar.k);
                a(byteArrayOutputStream, aVar.l);
                a(byteArrayOutputStream, aVar.m);
                a(byteArrayOutputStream, aVar.n);
                a(byteArrayOutputStream, aVar.o);
                a(byteArrayOutputStream, aVar.p);
                a(byteArrayOutputStream, aVar.q);
                a(byteArrayOutputStream, aVar.r);
                a(byteArrayOutputStream, aVar.s);
                a(byteArrayOutputStream, aVar.t);
                a(byteArrayOutputStream, aVar.u);
                a(byteArrayOutputStream, aVar.v);
                a(byteArrayOutputStream, aVar.w);
                a(byteArrayOutputStream, aVar.x);
                a(byteArrayOutputStream, aVar.y);
                byte[] b = t.b(byteArrayOutputStream.toByteArray());
                Key c = t.c();
                if (b.length > 117) {
                    byte[] bArr2 = new byte[117];
                    System.arraycopy(b, 0, bArr2, 0, 117);
                    Object a = o.a(bArr2, c);
                    Object obj = new byte[((b.length + 128) - 117)];
                    System.arraycopy(a, 0, obj, 0, 128);
                    System.arraycopy(b, 117, obj, 128, b.length - 117);
                    bArr = obj;
                } else {
                    bArr = o.a(b, c);
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            } catch (Throwable th4) {
                th3 = th4;
                try {
                    w.a(th3, "CInfo", "InitXInfo");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    return bArr;
                } catch (Throwable th5) {
                    th2 = th5;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th322) {
                            th322.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th3222) {
            byteArrayOutputStream = bArr;
            th2 = th3222;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th2;
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        Key c = t.c();
        if (c == null) {
            return null;
        }
        Object a = o.a(encoded, c);
        Object a2 = o.a(encoded, bArr);
        Object obj = new byte[(a.length + a2.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        System.arraycopy(a2, 0, obj, a.length, a2.length);
        return obj;
    }

    public static String b(byte[] bArr) {
        try {
            return d(bArr);
        } catch (Throwable th) {
            w.a(th, "CInfo", "AESData");
            return "";
        }
    }

    public static String c(byte[] bArr) {
        try {
            return d(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String d(byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        byte[] b = t.b(a(bArr));
        return b != null ? o.a(b) : "";
    }
}
