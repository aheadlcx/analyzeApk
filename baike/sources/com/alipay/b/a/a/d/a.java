package com.alipay.b.a.a.d;

import android.content.Context;
import com.alipay.b.a.a.a.a.c;

public class a {
    public static String a(Context context, String str, String str2) {
        String str3 = null;
        synchronized (a.class) {
            if (context != null) {
                if (!(com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2))) {
                    try {
                        String a = d.a(context, str, str2, "");
                        if (com.alipay.b.a.a.a.a.a(a)) {
                        } else {
                            str3 = c.b(c.a(), a);
                        }
                    } catch (Throwable th) {
                    }
                }
            }
        }
        return str3;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r3, java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
        r1 = com.alipay.b.a.a.d.a.class;
        monitor-enter(r1);
        r0 = com.alipay.b.a.a.a.a.a(r4);	 Catch:{ all -> 0x0028 }
        if (r0 != 0) goto L_0x0011;
    L_0x0009:
        r0 = com.alipay.b.a.a.a.a.a(r5);	 Catch:{ all -> 0x0028 }
        if (r0 != 0) goto L_0x0011;
    L_0x000f:
        if (r3 != 0) goto L_0x0013;
    L_0x0011:
        monitor-exit(r1);	 Catch:{ all -> 0x0028 }
    L_0x0012:
        return;
    L_0x0013:
        r0 = com.alipay.b.a.a.a.a.c.a();	 Catch:{ Throwable -> 0x002b }
        r0 = com.alipay.b.a.a.a.a.c.a(r0, r6);	 Catch:{ Throwable -> 0x002b }
        r2 = new java.util.HashMap;	 Catch:{ Throwable -> 0x002b }
        r2.<init>();	 Catch:{ Throwable -> 0x002b }
        r2.put(r5, r0);	 Catch:{ Throwable -> 0x002b }
        com.alipay.b.a.a.d.d.a(r3, r4, r2);	 Catch:{ Throwable -> 0x002b }
    L_0x0026:
        monitor-exit(r1);	 Catch:{ all -> 0x0028 }
        goto L_0x0012;
    L_0x0028:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x002b:
        r0 = move-exception;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.a.a.d.a.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
