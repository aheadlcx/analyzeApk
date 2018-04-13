package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import com.umeng.analytics.a;
import java.util.HashMap;
import java.util.Map;

public final class i {
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static Map<String, String> f = new HashMap();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String a(java.lang.String r3) {
        /*
        r1 = com.alipay.apmobilesecuritysdk.f.i.class;
        monitor-enter(r1);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x002d }
        r2 = "apdidTokenCache";
        r0.<init>(r2);	 Catch:{ all -> 0x002d }
        r0 = r0.append(r3);	 Catch:{ all -> 0x002d }
        r0 = r0.toString();	 Catch:{ all -> 0x002d }
        r2 = f;	 Catch:{ all -> 0x002d }
        r2 = r2.containsKey(r0);	 Catch:{ all -> 0x002d }
        if (r2 == 0) goto L_0x002a;
    L_0x001a:
        r2 = f;	 Catch:{ all -> 0x002d }
        r0 = r2.get(r0);	 Catch:{ all -> 0x002d }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x002d }
        r2 = com.alipay.b.a.a.a.a.b(r0);	 Catch:{ all -> 0x002d }
        if (r2 == 0) goto L_0x002a;
    L_0x0028:
        monitor-exit(r1);
        return r0;
    L_0x002a:
        r0 = "";
        goto L_0x0028;
    L_0x002d:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.f.i.a(java.lang.String):java.lang.String");
    }

    public static synchronized void a() {
        synchronized (i.class) {
        }
    }

    public static synchronized void a(b bVar) {
        synchronized (i.class) {
            if (bVar != null) {
                a = bVar.a();
                b = bVar.b();
                c = bVar.c();
            }
        }
    }

    public static synchronized void a(c cVar) {
        synchronized (i.class) {
            if (cVar != null) {
                a = cVar.a();
                b = cVar.b();
                d = cVar.d();
                e = cVar.e();
                c = cVar.c();
            }
        }
    }

    public static synchronized void a(String str, String str2) {
        synchronized (i.class) {
            String str3 = "apdidTokenCache" + str;
            if (f.containsKey(str3)) {
                f.remove(str3);
            }
            f.put(str3, str2);
        }
    }

    public static synchronized boolean a(Context context, String str) {
        boolean z;
        synchronized (i.class) {
            try {
                if (Math.abs(System.currentTimeMillis() - h.c(context, str)) < a.i) {
                    z = true;
                }
            } catch (Throwable th) {
                com.alipay.apmobilesecuritysdk.c.a.a(th);
            }
            z = false;
        }
        return z;
    }

    public static synchronized String b() {
        String str;
        synchronized (i.class) {
            str = a;
        }
        return str;
    }

    public static void b(String str) {
        a = str;
    }

    public static synchronized String c() {
        String str;
        synchronized (i.class) {
            str = b;
        }
        return str;
    }

    public static void c(String str) {
        b = str;
    }

    public static synchronized String d() {
        String str;
        synchronized (i.class) {
            str = d;
        }
        return str;
    }

    public static void d(String str) {
        c = str;
    }

    public static synchronized String e() {
        String str;
        synchronized (i.class) {
            str = e;
        }
        return str;
    }

    public static void e(String str) {
        d = str;
    }

    public static synchronized String f() {
        String str;
        synchronized (i.class) {
            str = c;
        }
        return str;
    }

    public static void f(String str) {
        e = str;
    }

    public static synchronized c g() {
        c cVar;
        synchronized (i.class) {
            cVar = new c(a, b, c, d, e);
        }
        return cVar;
    }

    public static void h() {
        f.clear();
        a = "";
        b = "";
        d = "";
        e = "";
        c = "";
    }
}
