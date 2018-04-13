package com.sprite.ads.third.gdt.splash;

import android.app.Activity;
import android.view.ViewGroup;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.comm.util.AdError;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdSdkItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.splash.SplashADListener;
import com.sprite.ads.splash.SplashAdapter;

public class GdtSplashAd extends SplashAdapter {
    ThirdSdkItem thirdSdkItem = ((ThirdSdkItem) this.mAdItem);

    public GdtSplashAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    public void show(Activity activity, ViewGroup viewGroup, final SplashADListener splashADListener) {
        SplashAD splashAD = new SplashAD(activity, viewGroup, this.thirdSdkItem.aid, this.thirdSdkItem.pid, new com.qq.e.ads.splash.SplashADListener() {
            public void onADDismissed() {
                ADLog.d("广点通开屏 onADDismissed");
                splashADListener.onADDismissed();
            }

            public void onNoAD(AdError adError) {
                if (adError != null) {
                    ADLog.d("广电通开屏广告——无广告：" + adError.getErrorCode() + " " + adError.getErrorMsg());
                }
                splashADListener.onNoAD(adError.getErrorCode());
            }

            public void onADPresent() {
                ADLog.d("广点通开屏 onADPresent");
                splashADListener.onADPresent(GdtSplashAd.this.mAdItem);
            }

            public void onADClicked() {
                ADLog.d("广点通开屏 onADClicked");
            }

            public void onADTick(long j) {
                ADLog.d("广点通开屏 onADTick");
            }
        });
    }

    public void release() {
    }
}
