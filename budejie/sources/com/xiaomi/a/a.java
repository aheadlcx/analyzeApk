package com.xiaomi.a;

import android.content.Context;
import android.text.TextUtils;
import com.ixintui.pushsdk.SdkConstants;
import java.io.File;

public final class a {
    private static ClassLoader a;

    public static Class a(Context context) {
        return b(context, "com.xiaomi.mipush.sdk.m");
    }

    public static Class b(Context context) {
        return b(context, "com.xiaomi.mipush.sdk.PushMessageHelper");
    }

    public static Object a(Context context, String str) {
        Object obj = null;
        try {
            Class b = b(context, str);
            if (b != null) {
                obj = b.newInstance();
            }
        } catch (Exception e) {
            com.ixintui.smartlink.a.a.a(e);
        } catch (Exception e2) {
            com.ixintui.smartlink.a.a.a(e2);
        } catch (Exception e22) {
            com.ixintui.smartlink.a.a.a(e22);
        }
        return obj;
    }

    private static Class b(Context context, String str) {
        Class cls = null;
        ClassLoader c = c(context);
        if (c == null) {
            com.ixintui.smartlink.a.a.b(SdkConstants.TAG, "load mi sdk failed");
        } else {
            try {
                cls = c.loadClass(str);
            } catch (Exception e) {
                com.ixintui.smartlink.a.a.a(e);
            } catch (Exception e2) {
                com.ixintui.smartlink.a.a.a(e2);
            }
        }
        return cls;
    }

    public static synchronized ClassLoader c(Context context) {
        ClassLoader classLoader;
        synchronized (a.class) {
            if (a == null) {
                a = c(context, context.getFilesDir().getAbsolutePath() + "/mi_312.jar");
            }
            classLoader = a;
        }
        return classLoader;
    }

    private static ClassLoader c(Context context, String str) {
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        try {
            ClassLoader bVar = new b(str, context.getCacheDir().getAbsolutePath(), null, context.getClassLoader());
            if (a(bVar)) {
                return bVar;
            }
            com.ixintui.smartlink.a.a.b(SdkConstants.TAG, "checkother loader error");
            return null;
        } catch (Exception e) {
            com.ixintui.smartlink.a.a.a(e);
            return null;
        }
    }

    private static boolean a(ClassLoader classLoader) {
        try {
            if (classLoader.loadClass("com.xiaomi.mipush.sdk.MiPushClient") == null || classLoader.loadClass("com.xiaomi.push.service.XMPushService") == null || classLoader.loadClass("com.xiaomi.push.service.XMJobService") == null || classLoader.loadClass("com.xiaomi.mipush.sdk.MessageHandleService") == null || classLoader.loadClass("com.xiaomi.mipush.sdk.PushMessageHandler") == null || classLoader.loadClass("com.xiaomi.push.service.receivers.PingReceiver") == null || classLoader.loadClass("com.xiaomi.mipush.sdk.PushMessageReceiver") == null || classLoader.loadClass("com.xiaomi.mipush.sdk.m") == null || classLoader.loadClass("com.xiaomi.mipush.sdk.a") == null || classLoader.loadClass("com.xiaomi.mipush.sdk.o") == null || classLoader.loadClass("com.xiaomi.mipush.sdk.PushMessageHelper") == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            com.ixintui.smartlink.a.a.a(e);
            return false;
        } catch (Exception e2) {
            com.ixintui.smartlink.a.a.a(e2);
            return false;
        }
    }
}
