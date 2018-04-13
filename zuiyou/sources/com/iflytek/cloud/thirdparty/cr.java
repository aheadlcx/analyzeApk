package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.Version;
import com.iflytek.cloud.thirdparty.cq.a;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class cr extends cq {
    public static final int b;
    public static final int c;
    public static final int d;
    public static final int e;
    public static final int f;
    private static boolean g = true;
    private static String h = "300008448508";
    private static final byte[] i = new byte[]{(byte) 31};
    private static final String j = new String(i);
    private static Context k = null;
    private static boolean l = false;
    private static int m;
    private static final int n;
    private static final int o;
    private static final int p;
    private static final int q;
    private static final int r;
    private static final int s = m;
    private static boolean v;
    private String[] t = new String[s];
    private Object u = this;
    private a w = null;
    private boolean x = false;
    private long y = System.currentTimeMillis();

    static {
        boolean z = true;
        m = 0;
        int i = m;
        m = i + 1;
        b = i;
        i = m;
        m = i + 1;
        n = i;
        i = m;
        m = i + 1;
        o = i;
        i = m;
        m = i + 1;
        c = i;
        i = m;
        m = i + 1;
        p = i;
        i = m;
        m = i + 1;
        q = i;
        i = m;
        m = i + 1;
        r = i;
        i = m;
        m = i + 1;
        d = i;
        i = m;
        m = i + 1;
        e = i;
        i = m;
        m = i + 1;
        f = i;
        if (MSC.isIflyVersion()) {
            z = false;
        }
        v = z;
    }

    private cr(a aVar) {
        this.w = aVar;
        h();
    }

    private static String a(String str) {
        String str2 = null;
        try {
            str2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.CHINA).format(Long.valueOf(str));
        } catch (Throwable th) {
            h("DC exception:" + th.getLocalizedMessage());
            th.printStackTrace();
        }
        return str2;
    }

    private static void a(Context context) {
        synchronized (a) {
            k = context;
        }
    }

    private static synchronized void a(boolean z) {
        synchronized (cr.class) {
            synchronized (a) {
                g = z;
            }
        }
    }

    private static final boolean a(int i) {
        return o == i || p == i || q == i || r == i;
    }

    public static synchronized cr b(a aVar) {
        cr crVar;
        synchronized (cr.class) {
            g("DC create enter.");
            crVar = new cr(aVar);
            g("DC create leave.");
        }
        return crVar;
    }

    public static synchronized boolean b(Context context, String str, boolean z) {
        synchronized (cr.class) {
            g("DC init enter.");
            synchronized (a) {
                try {
                    a(z);
                    a(context);
                    c(str);
                    g();
                } catch (Throwable th) {
                    h("DC exception:" + th.getLocalizedMessage());
                    th.printStackTrace();
                }
            }
            g("DC init leave.");
        }
        return false;
    }

    private static boolean b(String str) {
        return str != null && str.length() >= 12;
    }

    public static synchronized void c() {
        synchronized (cr.class) {
            g("DC destory enter.");
            synchronized (a) {
                try {
                    k = null;
                    l = false;
                } catch (Throwable th) {
                    h("DC exception:" + th.getLocalizedMessage());
                    th.printStackTrace();
                }
            }
            g("DC destory leave.");
        }
    }

    private static void c(String str) {
        synchronized (a) {
            if (b(str)) {
                h = str;
            }
        }
    }

    private static String d(String str) {
        String str2 = null;
        g("getMscParameter enter key=" + str);
        if (!MSC.isLoaded()) {
            h("getMscParameter MSC is not loaded");
        } else if (TextUtils.isEmpty(str)) {
            h("getMscParameter key is empty");
        } else {
            try {
                String str3;
                String str4;
                SpeechUtility utility = SpeechUtility.getUtility();
                CharSequence parameter = utility != null ? utility.getParameter("pte") : null;
                if (TextUtils.isEmpty(parameter)) {
                    str3 = "utf-8";
                } else {
                    CharSequence charSequence = parameter;
                }
                byte[] bytes = str.getBytes(str3);
                MSCSessionInfo mSCSessionInfo = new MSCSessionInfo();
                int QMSPGetParam = MSC.QMSPGetParam(bytes, mSCSessionInfo);
                if (QMSPGetParam == 0) {
                    str4 = new String(mSCSessionInfo.buffer, str3);
                } else {
                    g("getMscParameter MSC return " + QMSPGetParam);
                    str4 = null;
                }
                str2 = str4;
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        g("getMscParameter value=" + str2);
        g("getMscParameter leave");
        return str2;
    }

    public static synchronized boolean d() {
        boolean z;
        synchronized (cr.class) {
            g("DC getEnable enter.");
            synchronized (a) {
                g("DC getEnable static value=" + g);
                z = g;
            }
            if (z) {
                Object d = d("idc");
                g("DC getEnable msc val=" + d);
                if (!TextUtils.isEmpty(d)) {
                    if (AppIconSetting.LARGE_ICON_URL.equalsIgnoreCase(d)) {
                        v = true;
                    } else {
                        v = false;
                    }
                }
                z = v;
            }
            g("DC getEnable value=" + z);
            g("DC getEnable leave.");
        }
        return z;
    }

    private static void e(String str) {
    }

    public static boolean e() {
        boolean z;
        f("DC isActive enter.");
        synchronized (a) {
            z = l;
        }
        g("DC isActive=" + z);
        f("DC isActive leave.");
        return z;
    }

    private static void f(String str) {
    }

    private static void g() {
        f("DC inner init enter.");
        synchronized (a) {
            try {
                if (!d()) {
                    g("DC init is not enable.");
                } else if (n() != null) {
                    g("DC calling MA.init");
                    b.d(n(), m(), "MobileMarket");
                    g("DC MA.init end");
                    l = true;
                }
            } catch (Throwable th) {
                h("DC exception:" + th.getLocalizedMessage());
                th.printStackTrace();
            }
        }
        f("DC inner init leave.");
    }

    private static void g(String str) {
        cb.a(str);
    }

    private void h() {
        g("DC resetAllValues enter.");
        synchronized (this.u) {
            int i = 0;
            while (i < this.t.length) {
                try {
                    this.t[i] = "";
                    i++;
                } catch (Throwable th) {
                    h("DC exception:" + th.getLocalizedMessage());
                    th.printStackTrace();
                }
            }
        }
        g("DC resetAllValues leave.");
    }

    private static void h(String str) {
        cb.c(str);
    }

    private void i() {
        g("DC resetDynamicValues enter.");
        synchronized (this.u) {
            int i = 0;
            while (i < this.t.length) {
                try {
                    if (!a(i)) {
                        this.t[i] = "";
                    }
                    i++;
                } catch (Throwable th) {
                    h("DC exception:" + th.getLocalizedMessage());
                    th.printStackTrace();
                }
            }
        }
        g("DC resetDynamicValues leave.");
    }

    private void j() {
        g("DC initStaticValues enter.");
        synchronized (this.u) {
            try {
                if (TextUtils.isEmpty(this.t[o])) {
                    this.t[o] = SpeechUtility.getUtility().getParameter("appid");
                    f("DC info APPID:" + this.t[o]);
                }
                if (TextUtils.isEmpty(this.t[p])) {
                    this.t[p] = Version.getVersion();
                }
                if (TextUtils.isEmpty(this.t[q])) {
                    this.t[q] = bp.a(k).e("os.imsi");
                }
                if (TextUtils.isEmpty(this.t[r])) {
                    this.t[r] = bp.a(k).e("os.imei");
                }
                if (TextUtils.isEmpty(this.t[n])) {
                    this.t[n] = l();
                }
            } catch (Throwable th) {
                h("DC exception:" + th.getLocalizedMessage());
                th.printStackTrace();
            }
        }
        g("DC initStaticValues leave.");
    }

    private final void k() {
        f("DC check enter.");
        int i = 0;
        while (i < this.t.length) {
            try {
                if (this.t[i] == null) {
                    this.t[i] = "";
                }
                i++;
            } catch (Throwable th) {
                h("DC exception:" + th.getLocalizedMessage());
                th.printStackTrace();
            }
        }
        f("DC check leave.");
    }

    private static synchronized String l() {
        String str;
        synchronized (cr.class) {
            g("DC getUid enter.");
            str = null;
            try {
                String str2 = "loginid";
                str2 = "@";
                str2 = SpeechUtility.getUtility().getParameter("pte");
                if (TextUtils.isEmpty(str2)) {
                    str2 = "utf-8";
                }
                MSCSessionInfo mSCSessionInfo = new MSCSessionInfo();
                int QMSPGetParam = MSC.QMSPGetParam("loginid".getBytes(str2), mSCSessionInfo);
                if (QMSPGetParam == 0) {
                    str2 = new String(mSCSessionInfo.buffer).trim();
                    f("DC getUid loginid=" + str2);
                    int indexOf = str2.indexOf("@");
                    if (indexOf < 0 || indexOf >= str2.length()) {
                        h("DC getUid error, loginid has no tag of @:" + str2);
                        g("DC getUid leave.");
                    } else {
                        str = str2.substring(0, indexOf);
                        g("DC getUid leave.");
                    }
                } else {
                    h("DC getUid error:" + QMSPGetParam);
                    g("DC getUid leave.");
                }
            } catch (Throwable th) {
                h("DC exception:" + th.getLocalizedMessage());
                th.printStackTrace();
            }
        }
        return str;
    }

    private static String m() {
        String str;
        synchronized (a) {
            str = h;
        }
        return str;
    }

    private static Context n() {
        Context context;
        synchronized (a) {
            context = k;
        }
        return context;
    }

    public void a(int i, int i2) {
        a(i, String.valueOf(i2));
    }

    public void a(int i, long j) {
        a(i, String.valueOf(j));
    }

    public void a(int i, String str) {
        f("DC setData enter: key=" + i + ", value=" + str);
        synchronized (this.u) {
            try {
                this.t[i] = str;
                if (d == i || e == i) {
                    this.t[i] = a(str);
                }
                if (d == i || e == i || b == i || c == i) {
                    g("DC info: key=" + i + ", value=" + this.t[i]);
                }
            } catch (Throwable th) {
                h("DC exception:" + th.getLocalizedMessage());
                th.printStackTrace();
            }
        }
        f("DC setData leave.");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.iflytek.cloud.SpeechError r5) {
        /*
        r4 = this;
        r1 = r4.u;	 Catch:{ Throwable -> 0x0072 }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x0072 }
        r0 = r4.x;	 Catch:{ all -> 0x007d }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x007d }
    L_0x0008:
        return;
    L_0x0009:
        r0 = 1;
        r4.x = r0;	 Catch:{ all -> 0x007d }
        monitor-exit(r1);	 Catch:{ all -> 0x007d }
        r0 = d();	 Catch:{ Throwable -> 0x0072 }
        if (r0 == 0) goto L_0x0008;
    L_0x0013:
        r0 = r4.w;	 Catch:{ Throwable -> 0x0072 }
        if (r0 == 0) goto L_0x0008;
    L_0x0017:
        r0 = r4.w;	 Catch:{ Throwable -> 0x0072 }
        r0 = r0.g();	 Catch:{ Throwable -> 0x0072 }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x0072 }
        if (r1 != 0) goto L_0x0008;
    L_0x0023:
        r1 = c;	 Catch:{ Throwable -> 0x0072 }
        r4.a(r1, r0);	 Catch:{ Throwable -> 0x0072 }
        r0 = d;	 Catch:{ Throwable -> 0x0072 }
        r2 = r4.y;	 Catch:{ Throwable -> 0x0072 }
        r4.a(r0, r2);	 Catch:{ Throwable -> 0x0072 }
        r0 = "local";
        r1 = r4.w;	 Catch:{ Throwable -> 0x0072 }
        r1 = r1.A();	 Catch:{ Throwable -> 0x0072 }
        r0 = r0.equals(r1);	 Catch:{ Throwable -> 0x0072 }
        if (r0 == 0) goto L_0x0080;
    L_0x003e:
        r0 = "csid:";
    L_0x0041:
        r1 = b;	 Catch:{ Throwable -> 0x0072 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0072 }
        r2.<init>();	 Catch:{ Throwable -> 0x0072 }
        r0 = r2.append(r0);	 Catch:{ Throwable -> 0x0072 }
        r2 = r4.w;	 Catch:{ Throwable -> 0x0072 }
        r2 = r2.B();	 Catch:{ Throwable -> 0x0072 }
        r0 = r0.append(r2);	 Catch:{ Throwable -> 0x0072 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0072 }
        r4.a(r1, r0);	 Catch:{ Throwable -> 0x0072 }
        if (r5 != 0) goto L_0x0084;
    L_0x005f:
        r0 = 0;
    L_0x0060:
        r1 = f;	 Catch:{ Throwable -> 0x0072 }
        r4.a(r1, r0);	 Catch:{ Throwable -> 0x0072 }
        r0 = e;	 Catch:{ Throwable -> 0x0072 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0072 }
        r4.a(r0, r2);	 Catch:{ Throwable -> 0x0072 }
        r4.f();	 Catch:{ Throwable -> 0x0072 }
        goto L_0x0008;
    L_0x0072:
        r0 = move-exception;
        r1 = "DC exception:";
        com.iflytek.cloud.thirdparty.cb.c(r1);
        com.iflytek.cloud.thirdparty.cb.a(r0);
        goto L_0x0008;
    L_0x007d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x007d }
        throw r0;	 Catch:{ Throwable -> 0x0072 }
    L_0x0080:
        r0 = "sid:";
        goto L_0x0041;
    L_0x0084:
        r0 = r5.getErrorCode();	 Catch:{ Throwable -> 0x0072 }
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iflytek.cloud.thirdparty.cr.a(com.iflytek.cloud.SpeechError):void");
    }

    public void a(String str, boolean z) {
    }

    public void b() {
        this.y = System.currentTimeMillis();
    }

    public boolean f() {
        g("DC flush enter.");
        try {
            if (!e()) {
                g();
            }
            if (e()) {
                j();
                k();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < this.t.length; i++) {
                    if (this.t[i] != null) {
                        stringBuffer.append(this.t[i]);
                    }
                    stringBuffer.append(j);
                }
                String substring = stringBuffer.substring(0, stringBuffer.length() - 1);
                f("DC flush data=" + substring);
                e(substring);
                synchronized (a) {
                    if (!TextUtils.isEmpty(substring) && e()) {
                        g("DC calling MA.onEvent");
                        b.a(k, "iflytek", substring);
                        g("DC MA.onEvent end");
                    }
                }
                i();
            }
        } catch (Throwable th) {
            h("DC exception:" + th.getLocalizedMessage());
            th.printStackTrace();
        }
        g("DC flush leave.");
        return true;
    }
}
