package com.google.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.Logger.LogLevel;

final class Log {
    @VisibleForTesting
    static Logger sLogger = new DefaultLogger();

    Log() {
    }

    public static void setLogger(Logger logger) {
        if (logger == null) {
            sLogger = new NoOpLogger();
        } else {
            sLogger = logger;
        }
    }

    public static Logger getLogger() {
        return sLogger.getClass() == NoOpLogger.class ? null : sLogger;
    }

    public static void e(String str) {
        sLogger.e(str);
    }

    public static void e(String str, Throwable th) {
        sLogger.e(str, th);
    }

    public static void w(String str) {
        sLogger.w(str);
    }

    public static void w(String str, Throwable th) {
        sLogger.w(str, th);
    }

    public static void i(String str) {
        sLogger.i(str);
    }

    public static void i(String str, Throwable th) {
        sLogger.i(str, th);
    }

    public static void d(String str) {
        sLogger.d(str);
    }

    public static void d(String str, Throwable th) {
        sLogger.d(str, th);
    }

    public static void v(String str) {
        sLogger.v(str);
    }

    public static void v(String str, Throwable th) {
        sLogger.v(str, th);
    }

    public static LogLevel getLogLevel() {
        return sLogger.getLogLevel();
    }
}
