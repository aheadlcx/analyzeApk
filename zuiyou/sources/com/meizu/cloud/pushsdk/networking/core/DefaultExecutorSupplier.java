package com.meizu.cloud.pushsdk.networking.core;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

public class DefaultExecutorSupplier implements ExecutorSupplier {
    public static final int DEFAULT_MAX_NUM_THREADS = ((Runtime.getRuntime().availableProcessors() * 2) + 1);
    private final ANExecutor mImmediateNetworkExecutor;
    private final Executor mMainThreadExecutor = new MainThreadExecutor();
    private final ANExecutor mNetworkExecutor;

    public DefaultExecutorSupplier() {
        ThreadFactory priorityThreadFactory = new PriorityThreadFactory(10);
        this.mNetworkExecutor = new ANExecutor(DEFAULT_MAX_NUM_THREADS, priorityThreadFactory);
        this.mImmediateNetworkExecutor = new ANExecutor(2, priorityThreadFactory);
    }

    public ANExecutor forNetworkTasks() {
        return this.mNetworkExecutor;
    }

    public ANExecutor forImmediateNetworkTasks() {
        return this.mImmediateNetworkExecutor;
    }

    public Executor forMainThreadTasks() {
        return this.mMainThreadExecutor;
    }
}
