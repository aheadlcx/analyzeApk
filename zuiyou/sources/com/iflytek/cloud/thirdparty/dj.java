package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.iflytek.common.LaunchService;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.List;

public class dj {
    private static dk a = null;
    private static int b = -1;

    public static synchronized void a(Context context) {
        synchronized (dj.class) {
            dr.a(context, "============start=============");
            dk a = dk.a(context);
            a = a;
            a.a();
            if (a.b()) {
                a.a(System.currentTimeMillis());
                dt.a.execute(new dm(context));
            }
            d(context);
        }
    }

    private static void a(Context context, ResolveInfo resolveInfo) {
        try {
            String str = resolveInfo.serviceInfo.packageName;
            if (!a.a(str)) {
                dr.a("LaunchImpl", "startService not need:" + str);
            } else if (!context.getPackageName().equals(str)) {
                Intent intent = new Intent("com.iflytek.common.ACTION_LAUNCH");
                intent.setPackage(str);
                dr.a(context, "start app:" + context.startService(intent));
            }
        } catch (Throwable e) {
            dr.b("LaunchImpl", "", e);
        }
    }

    public static synchronized void a(Context context, String str, String str2) {
        synchronized (dj.class) {
            if (a == null) {
                a = dk.a(context);
            }
            a.a(str, str2);
        }
    }

    private static void c(Context context) {
        List queryIntentServices;
        try {
            queryIntentServices = context.getPackageManager().queryIntentServices(new Intent("com.iflytek.common.ACTION_LAUNCH"), Opcodes.SHL_INT_LIT8);
        } catch (Throwable e) {
            dr.b("LaunchImpl", "", e);
            queryIntentServices = null;
        }
        if (r0 == null) {
            dr.b("LaunchImpl", "query action null");
            return;
        }
        for (ResolveInfo a : r0) {
            a(context, a);
        }
    }

    private static void d(Context context) {
        if (b != 0) {
            try {
                Intent intent = new Intent();
                intent.setClass(context, LaunchService.class);
                if (context.startService(intent) != null) {
                    b = 1;
                }
            } catch (Exception e) {
                b = 0;
                dr.b("LaunchImpl", "start self Service error");
            }
        }
    }
}
