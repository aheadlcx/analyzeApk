package com.qiushibaike.statsdk;

import android.content.Context;

public class StoreTool extends BaseStoreTool {
    public static final String DEVICE_CUID = "device_cuid";
    public static final String DEVICE_ID = "device_id";
    public static final String LAST_REPORT_TIME = "last_report_time";
    public static final String REPORT_STRATEGY = "report_strategy";
    public static final String REPORT_TIME_DELAY = "report_time_delay";
    public static final String REPORT_TIME_INTERVAL = "report_time_interval";
    public static final String WIFI_ONLY = "wifi_only";
    static StoreTool a;

    private StoreTool() {
    }

    public static final StoreTool getInstance() {
        StoreTool storeTool;
        synchronized (StoreTool.class) {
            if (a == null) {
                a = new StoreTool();
            }
            storeTool = a;
        }
        return storeTool;
    }

    public long lastReportTime(Context context) {
        return getLong(context, LAST_REPORT_TIME, 0);
    }

    public void setLastReportTime(Context context, long j) {
        putLong(context, LAST_REPORT_TIME, j);
    }

    public void setWifiOnly(Context context, boolean z) {
        putBoolean(context, WIFI_ONLY, z);
    }

    public boolean isWifiOnly(Context context) {
        return getBoolean(context, WIFI_ONLY, false);
    }

    public void setReportStragety(Context context, int i) {
        putInt(context, REPORT_STRATEGY, i);
    }

    public int getReportStragety(Context context) {
        return getInt(context, REPORT_STRATEGY, 0);
    }

    public void setReportInterval(Context context, int i) {
        putInt(context, REPORT_TIME_INTERVAL, i);
    }

    public int getReportInterval(Context context) {
        return getInt(context, REPORT_TIME_INTERVAL, 1);
    }

    public void setReportDelay(Context context, int i) {
        putInt(context, REPORT_TIME_DELAY, i);
    }

    public int getReportDelay(Context context) {
        return getInt(context, REPORT_TIME_DELAY, 1);
    }

    public void setDeviceId(Context context, String str) {
        putString(context, "device_id", str);
    }

    public String getDeviceId(Context context) {
        return getString(context, "device_id", null);
    }

    public void setDeviceCUID(Context context, String str) {
        putString(context, DEVICE_CUID, str);
    }

    public String getDeviceCUID(Context context) {
        return getString(context, DEVICE_CUID, null);
    }
}
