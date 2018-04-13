package com.soundcloud.android.crop;

import java.util.concurrent.CountDownLatch;

class d implements Runnable {
    final /* synthetic */ CropImageActivity a;

    d(CropImageActivity cropImageActivity) {
        this.a = cropImageActivity;
    }

    public void run() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.a.a.post(new e(this, countDownLatch));
        try {
            countDownLatch.await();
            new a().crop();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
