package com.meizu.cloud.pushsdk.pushtracer.emitter.classic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor {
    private static ExecutorService executor;
    private static int threadCount = 2;

    public static ExecutorService getExecutor() {
        synchronized (Executor.class) {
            if (executor == null) {
                executor = Executors.newScheduledThreadPool(threadCount);
            }
        }
        return executor;
    }

    public static void execute(Runnable runnable) {
        getExecutor().execute(runnable);
    }

    public static Future futureCallable(Callable callable) {
        return getExecutor().submit(callable);
    }

    public static void shutdown() {
        if (executor != null) {
            executor.shutdown();
            executor = null;
        }
    }

    public static boolean status() {
        return (executor == null || executor.isShutdown()) ? false : true;
    }

    public static void setThreadCount(int i) {
        threadCount = i;
    }
}
