package com.sprite.ads.third.gdt.banner;

import com.sprite.ads.internal.log.ADLog;

public class GdtRetry {
    int retryTime = 3;

    interface RetryListener {
        void retry();
    }

    public boolean retry(RetryListener retryListener) {
        if (this.retryTime <= 0) {
            ADLog.d("广点通api重试结束 time:" + this.retryTime);
            return false;
        }
        ADLog.d("广点通api重试 time:" + this.retryTime);
        retryListener.retry();
        this.retryTime--;
        return true;
    }
}
