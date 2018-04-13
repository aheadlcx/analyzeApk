package com.tencent.bugly.crashreport.biz;

import com.tencent.bugly.proguard.x;

final class e implements Runnable {
    private /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        try {
            this.a.c();
        } catch (Throwable th) {
            x.a(th);
        }
    }
}
