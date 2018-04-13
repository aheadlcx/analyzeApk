package com.meizu.cloud.pushsdk.pushtracer.utils;

import com.meizu.cloud.a.a;

public class Logger {
    private static int level = 0;

    public static void e(String str, String str2, Object... objArr) {
        if (level >= 1) {
            a.d(getTag(str), getMessage(str2, objArr));
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (level >= 2) {
            a.b(getTag(str), getMessage(str2, objArr));
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (level >= 3) {
            a.a(getTag(str), getMessage(str2, objArr));
        }
    }

    private static String getMessage(String str, Object... objArr) {
        return getThread() + "|" + String.format(str, objArr);
    }

    private static String getTag(String str) {
        return "PushTracker->" + str;
    }

    private static String getThread() {
        return Thread.currentThread().getName();
    }

    public static void updateLogLevel(LogLevel logLevel) {
        level = logLevel.getLevel();
    }
}
