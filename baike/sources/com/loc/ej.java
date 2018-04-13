package com.loc;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class ej implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    ej() {
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, "pama#" + this.a.getAndIncrement());
    }
}
