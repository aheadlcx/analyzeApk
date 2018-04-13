package com.sina.weibo.sdk.statistic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class j {
    private static ExecutorService a = Executors.newSingleThreadExecutor();
    private static long b = 5;

    public static synchronized void execute(Runnable runnable) {
        synchronized (j.class) {
            if (a.isShutdown()) {
                a = Executors.newSingleThreadExecutor();
            }
            a.execute(runnable);
        }
    }

    public static synchronized void shutDownExecutor() {
        synchronized (j.class) {
            try {
                if (!a.isShutdown()) {
                    a.shutdown();
                }
                a.awaitTermination(b, TimeUnit.SECONDS);
            } catch (Exception e) {
            }
        }
    }
}
