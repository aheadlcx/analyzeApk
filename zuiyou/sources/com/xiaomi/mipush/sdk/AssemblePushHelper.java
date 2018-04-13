package com.xiaomi.mipush.sdk;

import android.content.Context;

public class AssemblePushHelper {
    private static boolean a = false;

    protected static synchronized String a(Context context) {
        String string;
        synchronized (AssemblePushHelper.class) {
            string = context.getSharedPreferences("mipush_extra", 0).getString("hms_push_token", "");
        }
        return string;
    }

    public static boolean needConnect() {
        return a;
    }

    public static synchronized void setConnectTime(Context context) {
        synchronized (AssemblePushHelper.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_connect_time", System.currentTimeMillis()).commit();
        }
    }

    public static synchronized boolean shouldTryConnect(Context context) {
        boolean z = false;
        synchronized (AssemblePushHelper.class) {
            if (Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_connect_time", -1)) > 5000) {
                z = true;
            }
        }
        return z;
    }
}
