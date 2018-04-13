package com.facebook.common.b;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class c extends b implements g {
    public c(Executor executor) {
        super("SerialExecutor", 1, executor, new LinkedBlockingQueue());
    }

    public synchronized void execute(Runnable runnable) {
        super.execute(runnable);
    }
}
