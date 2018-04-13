package com.soundcloud.android.crop;

import java.util.concurrent.CountDownLatch;

class e implements Runnable {
    final /* synthetic */ CountDownLatch a;
    final /* synthetic */ d b;

    e(d dVar, CountDownLatch countDownLatch) {
        this.b = dVar;
        this.a = countDownLatch;
    }

    public void run() {
        if (this.b.a.l.getScale() == 1.0f) {
            this.b.a.l.a();
        }
        this.a.countDown();
    }
}
