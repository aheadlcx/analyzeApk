package com.iflytek.cloud.thirdparty;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class du implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    du() {
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "CommonTask#" + this.a.getAndIncrement());
        thread.setPriority(5);
        return thread;
    }
}
