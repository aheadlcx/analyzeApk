package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.log.e;
import com.xiaomi.push.log.f;

public class Logger {
    private static boolean a = false;
    private static LoggerInterface b = null;

    private static void a(Context context) {
        Object obj = b != null ? 1 : null;
        LoggerInterface fVar = new f(context);
        if (!a && b(context) && obj != null) {
            b.a(new e(b, fVar));
        } else if (!a && b(context)) {
            b.a(fVar);
        } else if (obj != null) {
            b.a(b);
        } else {
            b.a(new e(null, null));
        }
    }

    private static boolean b(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            for (Object equals : strArr) {
                if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(equals)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void disablePushFileLog(Context context) {
        a = true;
        a(context);
    }

    public static void enablePushFileLog(Context context) {
        a = false;
        a(context);
    }

    public static void setLogger(Context context, LoggerInterface loggerInterface) {
        b = loggerInterface;
        a(context);
    }
}
