package io.agora.rtc.internal;

import android.util.Log;

public class Logging {
    private static final int LOG_DEBUG = 2048;
    private static final int LOG_ERROR = 4;
    private static final int LOG_INFO = 1;
    private static final int LOG_WARN = 2;

    public static void log(int i, String str, String str2) {
        RtcEngineImpl.nativeLog(i, "[" + str + "] " + str2);
    }

    public static void d(String str) {
        RtcEngineImpl.nativeLog(2048, str);
    }

    public static void i(String str) {
        RtcEngineImpl.nativeLog(1, str);
    }

    public static void e(String str) {
        RtcEngineImpl.nativeLog(4, str);
    }

    public static void w(String str) {
        RtcEngineImpl.nativeLog(2, str);
    }

    public static void d(String str, String str2) {
        log(2048, str, str2);
    }

    public static void i(String str, String str2) {
        log(1, str, str2);
    }

    public static void e(String str, String str2) {
        log(4, str, str2);
    }

    public static void w(String str, String str2) {
        log(2, str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        log(4, str, str2);
        log(4, str, th.toString());
        log(4, str, Log.getStackTraceString(th));
    }
}
