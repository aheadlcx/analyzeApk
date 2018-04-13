package com.umeng.update;

import android.content.Context;
import u.upd.a;
import u.upd.b;

public class UpdateConfig {
    public static final String a = "update";
    public static final String b = "2.4.2.20140520";
    public static final String c = "1.4";
    public static final String d = "com.umeng.update.net.DownloadingService";
    public static final String e = "com.umeng.update.UpdateDialogActivity";
    public static final String f = "android.permission.WRITE_EXTERNAL_STORAGE";
    public static final String g = "android.permission.ACCESS_NETWORK_STATE";
    public static final String h = "android.permission.INTERNET";
    public static final String i = "UMUpdateCheck";
    private static final String j = "umeng_update";
    private static final String k = "ignore";
    private static String l;
    private static String m;
    private static boolean n = true;
    private static boolean o = true;
    private static boolean p = false;
    private static boolean q = true;
    private static boolean r = false;
    private static boolean s = true;
    private static boolean t = true;
    private static int u = 0;

    public static void setAppkey(String str) {
        l = str;
    }

    public static void setChannel(String str) {
        m = str;
    }

    public static void setDebug(boolean z) {
        b.a = z;
    }

    public static String getAppkey(Context context) {
        if (l == null) {
            l = a.f(context);
        }
        return l;
    }

    public static String getChannel(Context context) {
        if (m == null) {
            m = a.h(context);
        }
        return m;
    }

    public static void saveIgnoreMd5(Context context, String str) {
        context.getApplicationContext().getSharedPreferences(j, 0).edit().putString(k, str).commit();
    }

    public static String getIgnoreMd5(Context context) {
        String string = context.getApplicationContext().getSharedPreferences(j, 0).getString(k, "");
        if ("".equals(string)) {
            return null;
        }
        return string;
    }

    public static boolean isUpdateOnlyWifi() {
        return n;
    }

    public static void setUpdateOnlyWifi(boolean z) {
        n = z;
    }

    public static boolean isUpdateAutoPopup() {
        return o;
    }

    public static void setUpdateAutoPopup(boolean z) {
        o = z;
    }

    public static boolean isUpdateForce() {
        return p;
    }

    public static void setUpdateForce(boolean z) {
        p = z;
    }

    public static boolean isDeltaUpdate() {
        return q;
    }

    public static void setDeltaUpdate(boolean z) {
        q = z;
    }

    public static boolean isSilentDownload() {
        return r;
    }

    public static void setSilentDownload(boolean z) {
        r = z;
    }

    public static int getStyle() {
        return u;
    }

    public static void setStyle(int i) {
        u = i;
    }

    public static boolean isUpdateCheck() {
        return s;
    }

    public static void setUpdateCheck(boolean z) {
        s = z;
    }

    public static boolean isRichNotification() {
        return t;
    }

    public static void setRichNotification(boolean z) {
        t = z;
    }
}
