package com.tencent.bugly.proguard;

import java.util.concurrent.ThreadFactory;

class s$1 implements ThreadFactory {
    final /* synthetic */ s a;

    s$1(s sVar) {
        this.a = sVar;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("BETA_SDK_DOWNLOAD");
        return thread;
    }
}
