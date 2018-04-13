package com.alipay.apmobilesecuritysdk.g;

import android.content.Context;
import com.alipay.b.a.a.a.a.c;
import com.alipay.b.a.a.d.b;
import com.alipay.b.a.a.d.d;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class a {
    public static String a(Context context, String str, String str2) {
        String str3 = null;
        if (!(context == null || com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2))) {
            try {
                String a = d.a(context, str, str2, "");
                if (!com.alipay.b.a.a.a.a.a(a)) {
                    str3 = c.b(c.a(), a);
                }
            } catch (Throwable th) {
            }
        }
        return str3;
    }

    public static String a(String str, String str2) {
        String str3 = null;
        synchronized (a.class) {
            if (com.alipay.b.a.a.a.a.a(str) || com.alipay.b.a.a.a.a.a(str2)) {
            } else {
                try {
                    String a = b.a(str);
                    if (com.alipay.b.a.a.a.a.a(a)) {
                    } else {
                        a = new JSONObject(a).getString(str2);
                        if (com.alipay.b.a.a.a.a.a(a)) {
                        } else {
                            str3 = c.b(c.a(), a);
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
        return str3;
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (!com.alipay.b.a.a.a.a.a(str) && !com.alipay.b.a.a.a.a.a(str2) && context != null) {
            try {
                String a = c.a(c.a(), str3);
                Map hashMap = new HashMap();
                hashMap.put(str2, a);
                d.a(context, str, hashMap);
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
        r2 = com.alipay.apmobilesecuritysdk.g.a.class;
        monitor-enter(r2);
        r0 = com.alipay.b.a.a.a.a.a(r7);	 Catch:{ all -> 0x008c }
        if (r0 != 0) goto L_0x000f;
    L_0x0009:
        r0 = com.alipay.b.a.a.a.a.a(r8);	 Catch:{ all -> 0x008c }
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r2);	 Catch:{ all -> 0x008c }
    L_0x0010:
        return;
    L_0x0011:
        r1 = com.alipay.b.a.a.d.b.a(r7);	 Catch:{ Throwable -> 0x0096 }
        r0 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0096 }
        r0.<init>();	 Catch:{ Throwable -> 0x0096 }
        r3 = com.alipay.b.a.a.a.a.b(r1);	 Catch:{ Throwable -> 0x0096 }
        if (r3 == 0) goto L_0x0025;
    L_0x0020:
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x008f }
        r0.<init>(r1);	 Catch:{ Exception -> 0x008f }
    L_0x0025:
        r1 = com.alipay.b.a.a.a.a.c.a();	 Catch:{ Throwable -> 0x0096 }
        r1 = com.alipay.b.a.a.a.a.c.a(r1, r9);	 Catch:{ Throwable -> 0x0096 }
        r0.put(r8, r1);	 Catch:{ Throwable -> 0x0096 }
        r3 = r0.toString();	 Catch:{ Throwable -> 0x0096 }
        r0 = com.alipay.b.a.a.a.a.a(r3);	 Catch:{ Throwable -> 0x00b6 }
        if (r0 != 0) goto L_0x003d;
    L_0x003a:
        java.lang.System.setProperty(r7, r3);	 Catch:{ Throwable -> 0x00b6 }
    L_0x003d:
        r0 = com.alipay.b.a.a.d.c.a();	 Catch:{ Throwable -> 0x0096 }
        if (r0 == 0) goto L_0x008a;
    L_0x0043:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0096 }
        r1 = ".SystemConfig";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x0096 }
        r1 = java.io.File.separator;	 Catch:{ Throwable -> 0x0096 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0096 }
        r0 = r0.append(r7);	 Catch:{ Throwable -> 0x0096 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0096 }
        r1 = com.alipay.b.a.a.d.c.a();	 Catch:{ Exception -> 0x00a9 }
        if (r1 == 0) goto L_0x008a;
    L_0x005e:
        r1 = new java.io.File;	 Catch:{ Exception -> 0x00a9 }
        r4 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x00a9 }
        r1.<init>(r4, r0);	 Catch:{ Exception -> 0x00a9 }
        r0 = r1.exists();	 Catch:{ Exception -> 0x00a9 }
        if (r0 != 0) goto L_0x0074;
    L_0x006d:
        r0 = r1.getParentFile();	 Catch:{ Exception -> 0x00a9 }
        r0.mkdirs();	 Catch:{ Exception -> 0x00a9 }
    L_0x0074:
        r0 = r1.getAbsolutePath();	 Catch:{ Exception -> 0x00a9 }
        r4 = new java.io.File;	 Catch:{ Exception -> 0x00a9 }
        r4.<init>(r0);	 Catch:{ Exception -> 0x00a9 }
        r1 = 0;
        r0 = new java.io.FileWriter;	 Catch:{ Exception -> 0x0098, all -> 0x00a2 }
        r5 = 0;
        r0.<init>(r4, r5);	 Catch:{ Exception -> 0x0098, all -> 0x00a2 }
        r0.write(r3);	 Catch:{ Exception -> 0x00b4, all -> 0x00af }
        r0.close();	 Catch:{ IOException -> 0x00ab }
    L_0x008a:
        monitor-exit(r2);	 Catch:{ all -> 0x008c }
        goto L_0x0010;
    L_0x008c:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
    L_0x008f:
        r0 = move-exception;
        r0 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0096 }
        r0.<init>();	 Catch:{ Throwable -> 0x0096 }
        goto L_0x0025;
    L_0x0096:
        r0 = move-exception;
        goto L_0x008a;
    L_0x0098:
        r0 = move-exception;
        r0 = r1;
    L_0x009a:
        if (r0 == 0) goto L_0x008a;
    L_0x009c:
        r0.close();	 Catch:{ IOException -> 0x00a0 }
        goto L_0x008a;
    L_0x00a0:
        r0 = move-exception;
        goto L_0x008a;
    L_0x00a2:
        r0 = move-exception;
    L_0x00a3:
        if (r1 == 0) goto L_0x00a8;
    L_0x00a5:
        r1.close();	 Catch:{ IOException -> 0x00ad }
    L_0x00a8:
        throw r0;	 Catch:{ Exception -> 0x00a9 }
    L_0x00a9:
        r0 = move-exception;
        goto L_0x008a;
    L_0x00ab:
        r0 = move-exception;
        goto L_0x008a;
    L_0x00ad:
        r1 = move-exception;
        goto L_0x00a8;
    L_0x00af:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x00a3;
    L_0x00b4:
        r1 = move-exception;
        goto L_0x009a;
    L_0x00b6:
        r0 = move-exception;
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.g.a.a(java.lang.String, java.lang.String, java.lang.String):void");
    }
}
