package com.huawei.hms.support.api.push.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import java.io.File;
import java.util.List;

public abstract class a {
    private static int a = -1;
    private static final Object b = new Object();

    private static boolean c(Context context) {
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("CommFun", "existFrameworkPush:" + a);
        }
        try {
            if (!new File("/system/framework/" + "hwpush.jar").isFile()) {
                return false;
            }
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("CommFun", "push jarFile is exist");
            }
            List queryIntentServices = context.getPackageManager().queryIntentServices(new Intent().setClassName("android", "com.huawei.android.pushagentproxy.PushService"), 128);
            if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
                if (com.huawei.hms.support.log.a.b()) {
                    com.huawei.hms.support.log.a.b("CommFun", "framework push exist, use framework push first");
                }
                return true;
            } else if (!com.huawei.hms.support.log.a.b()) {
                return false;
            } else {
                com.huawei.hms.support.log.a.b("CommFun", "framework push not exist, need vote apk or sdk to support pushservice");
                return false;
            }
        } catch (Exception e) {
            if (!com.huawei.hms.support.log.a.a()) {
                return false;
            }
            com.huawei.hms.support.log.a.d("CommFun", "get Apk version faild ,Exception e= " + e.toString());
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r5) {
        /*
        r1 = 0;
        r0 = 1;
        r2 = com.huawei.hms.support.log.a.a();
        if (r2 == 0) goto L_0x0024;
    L_0x0008:
        r2 = "CommFun";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "existFrameworkPush:";
        r3 = r3.append(r4);
        r4 = a;
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.huawei.hms.support.log.a.a(r2, r3);
    L_0x0024:
        r2 = b;
        monitor-enter(r2);
        r3 = -1;
        r4 = a;	 Catch:{ all -> 0x0048 }
        if (r3 == r4) goto L_0x0034;
    L_0x002c:
        r3 = a;	 Catch:{ all -> 0x0048 }
        if (r0 != r3) goto L_0x0032;
    L_0x0030:
        monitor-exit(r2);	 Catch:{ all -> 0x0048 }
    L_0x0031:
        return r0;
    L_0x0032:
        r0 = r1;
        goto L_0x0030;
    L_0x0034:
        r3 = c(r5);	 Catch:{ all -> 0x0048 }
        if (r3 == 0) goto L_0x0044;
    L_0x003a:
        r3 = 1;
        a = r3;	 Catch:{ all -> 0x0048 }
    L_0x003d:
        monitor-exit(r2);	 Catch:{ all -> 0x0048 }
        r2 = a;
        if (r0 == r2) goto L_0x0031;
    L_0x0042:
        r0 = r1;
        goto L_0x0031;
    L_0x0044:
        r3 = 0;
        a = r3;	 Catch:{ all -> 0x0048 }
        goto L_0x003d;
    L_0x0048:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0048 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.support.api.push.a.a.a(android.content.Context):boolean");
    }

    public static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (context.getPackageManager().getApplicationInfo(str, 0) == null) {
                return false;
            }
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("CommFun", str + " is installed");
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static String b(Context context) {
        String str = "0.0";
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            if (!com.huawei.hms.support.log.a.a()) {
                return str;
            }
            com.huawei.hms.support.log.a.a("CommFun", "package not exist");
            return str;
        } catch (Exception e2) {
            if (!com.huawei.hms.support.log.a.a()) {
                return str;
            }
            com.huawei.hms.support.log.a.d("CommFun", "getApkVersionName error" + e2.getMessage());
            return str;
        }
    }
}
