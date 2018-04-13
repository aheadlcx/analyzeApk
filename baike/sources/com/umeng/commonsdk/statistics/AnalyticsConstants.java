package com.umeng.commonsdk.statistics;

import com.umeng.commonsdk.statistics.common.e;

public class AnalyticsConstants {
    public static String[] APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
    public static boolean CHECK_DEVICE = true;
    public static final String LOG_TAG = "MobclickAgent";
    public static final String OS = "Android";
    public static final String SDK_TYPE = "Android";
    public static final boolean UM_DEBUG = e.a;
    private static int a = 1;

    public static void setDeviceType(int i) {
        a = i;
    }

    public static synchronized int getDeviceType() {
        int i;
        synchronized (AnalyticsConstants.class) {
            i = a;
        }
        return i;
    }
}
