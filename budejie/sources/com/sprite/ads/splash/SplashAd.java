package com.sprite.ads.splash;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.ViewGroup;
import com.sprite.ads.BaseAd;
import com.sprite.ads.a;
import com.sprite.ads.internal.bean.data.ADConfig;

public final class SplashAd extends BaseAd {
    private ADConfig adConfig;
    private CountDownTimer countDownTimer;
    private SplashADListener mListener;
    private ViewGroup mParentLayout;

    public SplashAd(String str, Activity activity, ViewGroup viewGroup, SplashADListener splashADListener) {
        this.mActivity = activity;
        this.mParentLayout = viewGroup;
        this.mListener = splashADListener;
        this.adRequest = new a();
        this.mainHandler = new Handler(this.mActivity.getMainLooper());
        loadAd(str);
    }

    private void loadAd(String str) {
        this.adRequest.a(str, new SplashAd$1(this));
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
        }
        this.countDownTimer = new SplashAd$2(this, 5000, 1000);
        this.countDownTimer.start();
    }
}
