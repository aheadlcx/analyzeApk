package com.alibaba.baichuan.android.trade.utils.log;

import android.util.Log;
import com.alibaba.baichuan.android.trade.AlibcContext;

public class AlibcLogger {
    public static void d(String str, String str2) {
        if (AlibcContext.isDebuggable()) {
            Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (AlibcContext.isDebuggable() || AlibcContext.showErrorInReleaseMode) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (AlibcContext.isDebuggable() || AlibcContext.showErrorInReleaseMode) {
            Log.e(str, str2, th);
        }
    }

    public static void i(String str, String str2) {
        if (AlibcContext.isDebuggable()) {
            Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (AlibcContext.isDebuggable()) {
            Log.w(str, str2);
        }
    }
}
