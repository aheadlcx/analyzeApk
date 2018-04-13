package com.alibaba.sdk.android.httpdns;

import android.util.Log;
import java.util.concurrent.ThreadFactory;

public class g implements ThreadFactory {
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setUncaughtExceptionHandler(new h());
        Log.i("HttpDnsSDK", "HttpDnsThreadFactory create a new thread, name: " + thread.getName());
        return thread;
    }
}
