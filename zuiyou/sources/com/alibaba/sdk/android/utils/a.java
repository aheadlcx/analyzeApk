package com.alibaba.sdk.android.utils;

import java.util.concurrent.ThreadFactory;

public class a implements ThreadFactory {
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setUncaughtExceptionHandler(new b());
        return thread;
    }
}
