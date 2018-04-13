package com.alibaba.sdk.android.utils;

import android.util.Log;
import java.lang.Thread.UncaughtExceptionHandler;

public class b implements UncaughtExceptionHandler {
    public void uncaughtException(Thread thread, Throwable th) {
        Log.e("AlicloudUtils", "Catch an uncaught exception, " + thread.getName() + ", error message: " + th.getMessage());
        th.printStackTrace();
    }
}
