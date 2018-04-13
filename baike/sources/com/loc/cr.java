package com.loc;

import android.content.Context;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.core.model.CustomButton;

public final class cr {
    private static cr b = null;
    bi a = null;
    private Context c = null;
    private int d = 0;
    private int e = cw.e;
    private boolean f = false;

    private cr(Context context) {
        this.c = context;
        this.a = bi.a();
    }

    public static cr a(Context context) {
        if (b == null) {
            b = new cr(context);
        }
        return b;
    }

    public final int a() {
        return this.d;
    }

    public final bo a(cs csVar) throws Throwable {
        if (de.a(de.c(this.c)) == -1) {
            return null;
        }
        long b = de.b();
        bi biVar = this.a;
        bo a = bi.a(csVar, this.f);
        this.d = Long.valueOf(de.b() - b).intValue();
        return a;
    }

    public final cs a(Context context, byte[] bArr, String str, boolean z) {
        try {
            Map hashMap = new HashMap();
            cs csVar = new cs(context, cw.b());
            try {
                hashMap.put("Content-Type", "application/octet-stream");
                hashMap.put(HttpHeaders.ACCEPT_ENCODING, "gzip");
                hashMap.put("gzipped", "1");
                hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
                hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.4.0");
                hashMap.put("KEY", k.f(context));
                hashMap.put("enginever", "4.7");
                String a = m.a();
                String a2 = m.a(context, a, "key=" + k.f(context));
                hashMap.put("ts", a);
                hashMap.put("scode", a2);
                hashMap.put("encr", "1");
                csVar.f = hashMap;
                a = "loc";
                if (!z) {
                    a = "locf";
                }
                csVar.m = true;
                csVar.k = String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", new Object[]{"3.4.0", a, Integer.valueOf(3)});
                csVar.j = z;
                csVar.g = str;
                csVar.h = de.a(bArr);
                csVar.a(q.a(context));
                Map hashMap2 = new HashMap();
                hashMap2.put("output", "bin");
                hashMap2.put("policy", "3103");
                csVar.l = hashMap2;
                csVar.a(this.e);
                csVar.b(this.e);
                if (!this.f) {
                    return csVar;
                }
                csVar.g = csVar.c().replace("http", "https");
                return csVar;
            } catch (Throwable th) {
                return csVar;
            }
        } catch (Throwable th2) {
            return null;
        }
    }

    public final String a(byte[] bArr, Context context, String str) {
        if (de.a(de.c(context)) == -1) {
            return null;
        }
        String str2;
        Map hashMap = new HashMap();
        bn csVar = new cs(context, cw.b());
        hashMap.clear();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.4.0");
        Map hashMap2 = new HashMap();
        hashMap2.put("custom", "26260A1F00020002");
        hashMap2.put(PayPWDUniversalActivity.KEY, k.f(context));
        String a = m.a();
        String a2 = m.a(context, a, t.b(hashMap2));
        hashMap2.put("ts", a);
        hashMap2.put("scode", a2);
        csVar.m = false;
        csVar.b(bArr);
        csVar.j = true;
        csVar.k = String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", new Object[]{"3.4.0", "loc", Integer.valueOf(3)});
        csVar.l = hashMap2;
        csVar.f = hashMap;
        csVar.g = str;
        csVar.a(q.a(context));
        csVar.a(cw.e);
        csVar.b(cw.e);
        try {
            bi biVar = this.a;
            str2 = new String(bi.a(csVar), "utf-8");
        } catch (Throwable th) {
            cw.a(th, "LocNetManager", CustomButton.REQUEST_METHOD_POST);
            str2 = null;
        }
        return str2;
    }

    public final void a(long j, boolean z) {
        try {
            this.f = z;
            this.e = Long.valueOf(j).intValue();
        } catch (Throwable th) {
            cw.a(th, "netmanager", "setOption");
        }
    }

    public final String b(byte[] bArr, Context context, String str) {
        if (de.a(de.c(context)) == -1) {
            return null;
        }
        String str2;
        Map hashMap = new HashMap();
        bn cqVar = new cq();
        hashMap.clear();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        cqVar.a = hashMap;
        cqVar.f = str;
        cqVar.g = bArr;
        cqVar.a(q.a(context));
        cqVar.a(cw.e);
        cqVar.b(cw.e);
        try {
            bi biVar = this.a;
            str2 = new String(bi.a(cqVar), "utf-8");
        } catch (Throwable th) {
            cw.a(th, "LocNetManager", CustomButton.REQUEST_METHOD_POST);
            str2 = null;
        }
        return str2;
    }
}
