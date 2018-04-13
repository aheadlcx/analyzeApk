package com.crashlytics.android.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ah {
    public long a() {
        return System.currentTimeMillis();
    }

    public static ExecutorService a(String str) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(c(str));
        a(str, newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    public static ScheduledExecutorService b(String str) {
        Object newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(c(str));
        a(str, newSingleThreadScheduledExecutor);
        return newSingleThreadScheduledExecutor;
    }

    public static ThreadFactory c(String str) {
        return new bs(str, new AtomicLong(1));
    }

    private static void a(String str, ExecutorService executorService) {
        Runtime.getRuntime().addShutdownHook(new Thread(new bu(str, executorService, 2, TimeUnit.SECONDS), "Crashlytics Shutdown Hook for " + str));
    }
}
