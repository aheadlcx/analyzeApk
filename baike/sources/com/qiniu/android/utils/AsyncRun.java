package com.qiniu.android.utils;

import android.os.Handler;
import android.os.Looper;

public final class AsyncRun {
    public static void runInMain(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public static void runInBack(Runnable runnable) {
    }
}
