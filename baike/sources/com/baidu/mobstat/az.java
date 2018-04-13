package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.stat.DeviceInfo;
import java.text.SimpleDateFormat;
import org.json.JSONObject;

public class az {
    private static az a;
    private Context b;
    private JSONObject c = new JSONObject();
    private long d = 24;
    private long e = 0;
    private long f = 0;
    private long g = 0;
    private long h = 5;
    private long i = 24;
    private long j = 15;
    private long k = 15;
    private long l = 30;
    private long m = 12;
    private long n = 1;
    private long o = 24;
    private String p = "";
    private String q = "";

    public static az a(Context context) {
        if (a == null) {
            synchronized (az.class) {
                if (a == null) {
                    a = new az(context);
                }
            }
        }
        return a;
    }

    private az(Context context) {
        this.b = context;
        m();
        j();
        k();
    }

    private void m() {
        Object b = cu.b("backups/system/.timestamp");
        try {
            if (!TextUtils.isEmpty(b)) {
                this.c = new JSONObject(b);
            }
        } catch (Exception e) {
        }
    }

    public boolean a() {
        return this.e != 0;
    }

    public boolean b() {
        return this.f != 0;
    }

    public long c() {
        return ((this.d * 60) * 60) * 1000;
    }

    public long d() {
        return ((this.o * 60) * 60) * 1000;
    }

    public long e() {
        return (this.h * 60) * 1000;
    }

    public long f() {
        return ((this.i * 60) * 60) * 1000;
    }

    public long g() {
        return (((this.j * 24) * 60) * 60) * 1000;
    }

    public long h() {
        return (((this.k * 24) * 60) * 60) * 1000;
    }

    public long i() {
        return ((this.m * 60) * 60) * 1000;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void j() {
        /*
        r4 = this;
        r0 = r4.b;
        r1 = ".config2";
        r0 = com.baidu.mobstat.cu.a(r0, r1);
        r1 = new java.lang.String;	 Catch:{ Exception -> 0x0090 }
        r2 = 0;
        r3 = com.baidu.mobstat.cw.a();	 Catch:{ Exception -> 0x0090 }
        r0 = r0.getBytes();	 Catch:{ Exception -> 0x0090 }
        r0 = com.baidu.mobstat.cv.a(r0);	 Catch:{ Exception -> 0x0090 }
        r0 = com.baidu.mobstat.dc.b(r2, r3, r0);	 Catch:{ Exception -> 0x0090 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0090 }
        r0 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x0090 }
        if (r0 == 0) goto L_0x0025;
    L_0x0024:
        return;
    L_0x0025:
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0090 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0090 }
        r0 = "c";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x0095 }
        r4.e = r0;	 Catch:{ JSONException -> 0x0095 }
    L_0x0032:
        r0 = "d";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x009a }
        r4.h = r0;	 Catch:{ JSONException -> 0x009a }
    L_0x003a:
        r0 = "e";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x009f }
        r4.i = r0;	 Catch:{ JSONException -> 0x009f }
    L_0x0042:
        r0 = "i";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x00a4 }
        r4.j = r0;	 Catch:{ JSONException -> 0x00a4 }
    L_0x004a:
        r0 = "f";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x00a9 }
        r4.d = r0;	 Catch:{ JSONException -> 0x00a9 }
    L_0x0052:
        r0 = "s";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x00ae }
        r4.o = r0;	 Catch:{ JSONException -> 0x00ae }
    L_0x005a:
        r0 = "pk";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x00b3 }
        r4.k = r0;	 Catch:{ JSONException -> 0x00b3 }
    L_0x0062:
        r0 = "at";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x00b8 }
        r4.l = r0;	 Catch:{ JSONException -> 0x00b8 }
    L_0x006a:
        r0 = "as";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x00bd }
        r4.m = r0;	 Catch:{ JSONException -> 0x00bd }
    L_0x0072:
        r0 = "ac";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x00c2 }
        r4.n = r0;	 Catch:{ JSONException -> 0x00c2 }
    L_0x007a:
        r0 = "mc";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x00c7 }
        r4.f = r0;	 Catch:{ JSONException -> 0x00c7 }
    L_0x0082:
        r0 = "lsc";
        r0 = r2.getLong(r0);	 Catch:{ JSONException -> 0x008b }
        r4.g = r0;	 Catch:{ JSONException -> 0x008b }
        goto L_0x0024;
    L_0x008b:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0024;
    L_0x0090:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);
        goto L_0x0024;
    L_0x0095:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0032;
    L_0x009a:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x003a;
    L_0x009f:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0042;
    L_0x00a4:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x004a;
    L_0x00a9:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0052;
    L_0x00ae:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x005a;
    L_0x00b3:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0062;
    L_0x00b8:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x006a;
    L_0x00bd:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0072;
    L_0x00c2:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x007a;
    L_0x00c7:
        r0 = move-exception;
        com.baidu.mobstat.bd.b(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.az.j():void");
    }

    public void k() {
        try {
            Object str = new String(dc.b(false, cw.a(), cv.a(cu.a(this.b, ".sign").getBytes())));
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    this.q = jSONObject.getString(Config.SIGN);
                } catch (Throwable e) {
                    bd.b(e);
                }
                try {
                    this.p = jSONObject.getString(DeviceInfo.TAG_VERSION);
                } catch (Throwable e2) {
                    bd.b(e2);
                }
            }
        } catch (Throwable e22) {
            bd.b(e22);
        }
    }

    public void a(String str) {
        cu.a(this.b, ".config2", str, false);
        j();
    }

    public void b(String str) {
        cu.a(this.b, ".sign", str, false);
        k();
    }

    public String c(String str) {
        if (TextUtils.isEmpty(this.p) || !this.p.equals(str) || TextUtils.isEmpty(this.q)) {
            return "";
        }
        return this.q;
    }

    public long a(u uVar) {
        long j = uVar.j;
        try {
            String uVar2 = uVar.toString();
            if (this.c.has(uVar2)) {
                j = this.c.getLong(uVar2);
            }
        } catch (Throwable e) {
            bd.a(e);
        }
        return b(j);
    }

    public void a(u uVar, long j) {
        uVar.j = j;
        try {
            this.c.put(uVar.toString(), j);
        } catch (Throwable e) {
            bd.a(e);
        }
        try {
            cu.a("backups/system/.timestamp", this.c.toString(), false);
        } catch (Throwable e2) {
            bd.a(e2);
        }
    }

    public boolean l() {
        long currentTimeMillis = System.currentTimeMillis();
        long a = a(u.LAST_SEND);
        long d = d();
        bd.a("canSend now=" + currentTimeMillis + ";lastSendTime=" + a + ";sendLogTimeInterval=" + d);
        return currentTimeMillis - a > d || !a(a);
    }

    public boolean a(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(Long.valueOf(j)).equals(simpleDateFormat.format(Long.valueOf(System.currentTimeMillis())));
    }

    private long b(long j) {
        return j - System.currentTimeMillis() > 0 ? 0 : j;
    }
}
