package com.tencent.bugly.proguard;

import java.util.concurrent.ThreadFactory;

class am$1 implements ThreadFactory {
    final /* synthetic */ am a;

    am$1(am amVar) {
        this.a = amVar;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("BUGLY_THREAD");
        return thread;
    }
}
