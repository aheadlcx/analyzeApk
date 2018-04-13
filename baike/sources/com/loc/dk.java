package com.loc;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class dk implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    dk() {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "disklrucache#" + this.a.getAndIncrement());
    }
}
