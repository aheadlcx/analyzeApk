package com.meizu.cloud.pushsdk.networking.core;

import java.util.concurrent.Executor;

public interface ExecutorSupplier {
    ANExecutor forImmediateNetworkTasks();

    Executor forMainThreadTasks();

    ANExecutor forNetworkTasks();
}
