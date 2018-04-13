package com.alibaba.sdk.android.httpdns;

import android.util.Log;

class f {
    private static boolean a = false;
    private static int c = -1;

    static void a(Throwable th) {
        if (a && th != null) {
            th.printStackTrace();
        }
    }

    private static String b() {
        int i = 0;
        if (c == -1) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int length = stackTrace.length;
            int i2 = 0;
            while (i < length) {
                if (stackTrace[i].getMethodName().equals("getTraceInfo")) {
                    c = i2 + 1;
                    break;
                }
                i2++;
                i++;
            }
        }
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[c + 1];
        return stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " - [" + stackTraceElement.getMethodName() + "]";
    }

    static void d(String str) {
        if (a && str != null) {
            Log.d("HttpDnsSDK", Thread.currentThread().getId() + " - " + b() + " - " + str);
        }
    }

    static void e(String str) {
        if (a && str != null) {
            Log.e("HttpDnsSDK", Thread.currentThread().getId() + " - " + b() + " - " + str);
        }
    }

    static synchronized void setLogEnabled(boolean z) {
        synchronized (f.class) {
            a = z;
        }
    }
}
