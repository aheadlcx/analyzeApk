package com.tencent.bugly.proguard;

import java.util.concurrent.ThreadFactory;

final class aw implements ThreadFactory {
    aw(w wVar) {
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("BUGLY_THREAD");
        return thread;
    }
}
