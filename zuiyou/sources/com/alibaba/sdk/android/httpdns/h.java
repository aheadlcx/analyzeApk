package com.alibaba.sdk.android.httpdns;

import android.util.Log;
import java.lang.Thread.UncaughtExceptionHandler;

public class h implements UncaughtExceptionHandler {
    public void uncaughtException(Thread thread, Throwable th) {
        Log.e("HttpDnsSDK", "Catch an uncaught exception, " + thread.getName() + ", error message: " + th.getMessage());
        th.printStackTrace();
    }
}
