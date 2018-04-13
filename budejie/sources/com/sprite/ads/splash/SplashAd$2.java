package com.sprite.ads.splash;

import android.os.CountDownTimer;

class SplashAd$2 extends CountDownTimer {
    final /* synthetic */ SplashAd this$0;

    SplashAd$2(SplashAd splashAd, long j, long j2) {
        this.this$0 = splashAd;
        super(j, j2);
    }

    public void onFinish() {
        if (!SplashAd.access$800(this.this$0).a()) {
            SplashAd.access$900(this.this$0).b();
            SplashAd.access$100(this.this$0).onNoAD(11);
        }
    }

    public void onTick(long j) {
    }
}
