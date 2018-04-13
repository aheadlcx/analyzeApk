package com.qiniu.android.collect;

import com.qiniu.android.utils.ContextGetter;

public class Config {
    public static int interval = 10;
    public static boolean isRecord = true;
    public static boolean isUpload = true;
    public static int maxRecordFileSize = 2097152;
    public static String recordDir;
    public static String serverURL = "https://uplog.qbox.me/log";
    public static int uploadThreshold = 4096;

    static {
        recordDir = null;
        try {
            recordDir = ContextGetter.applicationContext().getCacheDir().getAbsolutePath();
        } catch (Throwable th) {
            th.fillInStackTrace();
        }
    }

    public static void quick() {
        uploadThreshold = 1024;
        interval = 2;
    }

    public static void normal() {
        uploadThreshold = 4096;
        interval = 10;
    }

    public static void slow() {
        uploadThreshold = 153600;
        interval = 300;
    }
}
