package com.meizu.cloud.pushsdk.base;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorProxy extends Proxy<Executor> implements Executor {
    private static ExecutorProxy sInstance;

    public static ExecutorProxy get() {
        if (sInstance == null) {
            synchronized (ExecutorProxy.class) {
                if (sInstance == null) {
                    sInstance = new ExecutorProxy(new ThreadPoolExecutor(0, 5, 30, TimeUnit.SECONDS, new LinkedBlockingDeque(100), new RejectedExecutionHandler() {
                        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                            new Thread(runnable).start();
                        }
                    }));
                }
            }
        }
        return sInstance;
    }

    protected ExecutorProxy(Executor executor) {
        super(executor);
    }

    public void execute(Runnable runnable) {
        ((Executor) getImpl()).execute(runnable);
    }
}
