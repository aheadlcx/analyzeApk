package com.crashlytics.android;

import java.util.concurrent.CountDownLatch;

final class az {
    private boolean a;
    private final CountDownLatch b;

    private az(Crashlytics crashlytics) {
        this.a = false;
        this.b = new CountDownLatch(1);
    }

    final void a(boolean z) {
        this.a = z;
        this.b.countDown();
    }

    final boolean a() {
        return this.a;
    }

    final void b() {
        try {
            this.b.await();
        } catch (InterruptedException e) {
        }
    }
}
