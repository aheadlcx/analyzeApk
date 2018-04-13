package com.baidu.lbsapi.auth;

import android.util.Log;
import com.alipay.sdk.util.h;

class b {
    public static boolean a = false;
    private static String b = "BaiduApiAuth";

    public static String a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        return stackTraceElement.getFileName() + "[" + stackTraceElement.getLineNumber() + "]";
    }

    public static void a(String str) {
        if (a && Thread.currentThread().getStackTrace().length != 0) {
            Log.d(b, a() + h.b + str);
        }
    }

    public static void b(String str) {
        if (a && Thread.currentThread().getStackTrace().length != 0) {
            Log.e(b, a() + h.b + str);
        }
    }
}
