package com.iflytek.cloud.thirdparty;

import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class ah {
    an a;
    private af b;
    private char[] c;
    private String d = "";
    private MSCSessionInfo e = new MSCSessionInfo();
    private String f = "";
    private boolean g;
    private MSCSessionInfo h = new MSCSessionInfo();
    private ae i;

    public ah(af afVar) {
        this.b = afVar;
        this.i = ae.a();
    }

    public int a(String str, String str2, String str3, String str4, String str5, Object obj) {
        return MSC.AIUIRegisterNotify(this.c, str, str2, str3, str4, str5, obj);
    }

    public void a() {
        synchronized (this) {
            if (this.c != null) {
                cb.a("AIUISession", "clear session data.");
                MSC.AIUIClear(this.c);
            }
            this.i.b();
        }
    }

    public void a(ce ceVar) throws n {
        synchronized (this) {
            if (this.a != null) {
                this.a.a("app_lau");
                b(ceVar);
            }
            ce b = ceVar.b();
            b.a("dts", "4");
            byte[] bArr = new byte[1];
            a("", ac.c(b), bArr, bArr.length);
            cb.a("AIUISession", "stmid=" + this.d + ", dts=4, tstamp=" + System.currentTimeMillis());
            this.d = ceVar.e("stmid");
            ao.a(AIUIConstant.PARAM_SPEECH, this.d, null);
            this.g = false;
        }
    }

    public void a(ce ceVar, byte[] bArr, int i, boolean z) throws n {
        synchronized (this) {
            String c;
            String str;
            int i2;
            this.g = true;
            if (z) {
                this.f = "";
                ce b = ceVar.b();
                this.a = new an();
                this.a.a(ceVar);
                b.a("dts", "1");
                b.a("scene", ac.a("global", "scene", ""), false);
                b.a("userparams", ac.c());
                if (!(b.g("msc.lng") && b.g("msc.lat"))) {
                    br a = br.a(this.b.e());
                    float a2 = a.a("msc.lng");
                    float a3 = a.a("msc.lat");
                    if (a2 > 0.0f && a3 > 0.0f) {
                        b.a("msc.lng", a2 + "", false);
                        b.a("msc.lat", a3 + "", false);
                    }
                }
                this.d = ceVar.e("stmid");
                ao.b(AIUIConstant.PARAM_SPEECH, al.b());
                String d = ac.d(b);
                c = ac.c(b);
                str = d;
                i2 = 1;
            } else {
                if (TextUtils.isEmpty(this.f)) {
                    ceVar.a("dts", "2");
                    this.f = ac.c(ceVar);
                }
                str = "";
                c = this.f;
                i2 = 2;
            }
            if (1 == i2) {
                cb.a("AIUISession", "stmid=" + this.d + ", dts=" + i2 + ", tstamp=" + System.currentTimeMillis());
            }
            a(str, c, bArr, i);
        }
    }

    public void a(String str, String str2, byte[] bArr, int i) throws n {
        synchronized (this) {
            if (this.c == null) {
                return;
            }
            try {
                MSC.AIUIDataWrite(this.c, str.getBytes("utf-8"), str2.getBytes("utf-8"), bArr, i, this.e);
                cb.a("AIUISession", String.format(Locale.ENGLISH, "dataLen=%d, params=%s, mscParams=%s, errorCode=%d, timestamp=%d", new Object[]{Integer.valueOf(i), str, str2, Integer.valueOf(this.e.errorcode), Long.valueOf(System.currentTimeMillis())}));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (this.e.errorcode != 0) {
                throw new n(this.e.errorcode, "");
            }
        }
    }

    public void a(String str, byte[] bArr, int i) throws n {
        synchronized (this) {
            if (this.c == null) {
                return;
            }
            try {
                MSC.AIUISendLog(this.c, str.getBytes("utf-8"), bArr, i, this.e);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (this.e.errorcode != 0) {
                throw new n(this.e.errorcode, "");
            }
        }
    }

    public void a(byte[] bArr, byte[] bArr2, int i, int i2) throws n {
        synchronized (this) {
            if (this.c == null) {
                return;
            }
            MSC.AIUISyncData(this.c, bArr, bArr2, i, i2, this.e);
            cb.a("AIUISession", String.format(Locale.ENGLISH, "dataLen=%d, errorCode=%d", new Object[]{Integer.valueOf(i), Integer.valueOf(this.e.errorcode)}));
            if (this.e.errorcode != 0) {
                String str = "";
                if (this.e.buffer != null) {
                    str = new String(this.e.buffer);
                }
                throw new n(this.e.errorcode, str);
            }
        }
    }

    public boolean a(String str, String str2) {
        if (this.c != null) {
            int AIUISetParam;
            try {
                AIUISetParam = MSC.AIUISetParam(this.c, str.getBytes("utf-8"), str2.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                AIUISetParam = -1;
            }
            if (AIUISetParam == 0) {
                return true;
            }
        }
        return false;
    }

    public void b(ce ceVar) throws n {
        synchronized (this) {
            try {
                String e = ceVar.e("stmid");
                byte[] bytes = this.a.a().getBytes("utf-8");
                StringBuffer stringBuffer = new StringBuffer("log=sessinfo");
                stringBuffer.append(",dtype=audio,cnt_id=0,").append("stmid=" + e);
                a("", stringBuffer.toString(), bytes, bytes.length);
            } catch (Throwable e2) {
                cb.a(e2);
            }
        }
    }

    public boolean b() {
        boolean z;
        synchronized (this) {
            z = this.c != null;
        }
        return z;
    }

    public int c(ce ceVar) throws n {
        this.g = false;
        try {
            byte[] bytes = ceVar.toString().getBytes("utf-8");
            synchronized (this) {
                if (this.c != null) {
                    return 0;
                }
                long currentTimeMillis = System.currentTimeMillis();
                this.c = MSC.AIUISessionBegin(bytes, this.h);
                long currentTimeMillis2 = System.currentTimeMillis();
                int i = this.h.errorcode;
                cb.a("AIUISession", String.format(Locale.ENGLISH, "session begin, errorCode=%d, spent=%dms", new Object[]{Integer.valueOf(i), Long.valueOf(currentTimeMillis2 - currentTimeMillis)}));
                if (i != 0) {
                    throw new n(i, "");
                }
                return 0;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            this.c = null;
            return -1;
        }
    }

    public boolean c() {
        return this.g;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() throws com.iflytek.cloud.thirdparty.n {
        /*
        r8 = this;
        r1 = 0;
        r8.g = r1;
        monitor-enter(r8);
        r0 = r8.c;	 Catch:{ all -> 0x006a }
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r8);	 Catch:{ all -> 0x006a }
    L_0x0009:
        return;
    L_0x000a:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x006a }
        r0 = r8.c;	 Catch:{ UnsupportedEncodingException -> 0x0064 }
        r4 = "";
        r5 = "utf-8";
        r4 = r4.getBytes(r5);	 Catch:{ UnsupportedEncodingException -> 0x0064 }
        r0 = com.iflytek.msc.MSC.AIUISessionEnd(r0, r4);	 Catch:{ UnsupportedEncodingException -> 0x0064 }
    L_0x001e:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x006a }
        r1 = 0;
        r8.c = r1;	 Catch:{ all -> 0x006a }
        r1 = "AIUISession";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006a }
        r6.<init>();	 Catch:{ all -> 0x006a }
        r7 = "session end, ret=";
        r6 = r6.append(r7);	 Catch:{ all -> 0x006a }
        r6 = r6.append(r0);	 Catch:{ all -> 0x006a }
        r7 = ", spent=";
        r6 = r6.append(r7);	 Catch:{ all -> 0x006a }
        r2 = r4 - r2;
        r2 = r6.append(r2);	 Catch:{ all -> 0x006a }
        r3 = "ms";
        r2 = r2.append(r3);	 Catch:{ all -> 0x006a }
        r2 = r2.toString();	 Catch:{ all -> 0x006a }
        com.iflytek.cloud.thirdparty.cb.a(r1, r2);	 Catch:{ all -> 0x006a }
        monitor-exit(r8);	 Catch:{ all -> 0x006a }
        r1 = r8.i;
        r1.b();
        if (r0 == 0) goto L_0x0009;
    L_0x005b:
        r1 = new com.iflytek.cloud.thirdparty.n;
        r2 = "";
        r1.<init>(r0, r2);
        throw r1;
    L_0x0064:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x006a }
        r0 = r1;
        goto L_0x001e;
    L_0x006a:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x006a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.ah.d():void");
    }
}
