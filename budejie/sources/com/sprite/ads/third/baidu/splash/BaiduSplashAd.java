package com.sprite.ads.third.baidu.splash;

import android.app.Activity;
import android.view.ViewGroup;
import com.baidu.mobads.AdView;
import com.baidu.mobads.SplashAd;
import com.baidu.mobads.SplashAdListener;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdSdkItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.splash.SplashADListener;
import com.sprite.ads.splash.SplashAdapter;

public class BaiduSplashAd extends SplashAdapter {
    ThirdSdkItem thirdSdkItem = ((ThirdSdkItem) this.mAdItem);

    public BaiduSplashAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    public void show(Activity activity, ViewGroup viewGroup, final SplashADListener splashADListener) {
        AdView.setAppSid(activity, this.thirdSdkItem.aid);
        ThirdSdkItem thirdSdkItem = (ThirdSdkItem) this.mAdItem;
        SplashAd splashAd = new SplashAd(activity, viewGroup, new SplashAdListener() {
            public void onAdPresent() {
                ADLog.d("百度开屏 onAdPresent");
                splashADListener.onADPresent(BaiduSplashAd.this.mAdItem);
            }

            public void onAdDismissed() {
                ADLog.d("百度开屏 onAdDismissed");
                splashADListener.onADDismissed();
            }

            public void onAdFailed(String str) {
                ADLog.d("百度开屏 onAdFailed" + str);
                splashADListener.onNoAD(0);
            }

            public void onAdClick() {
                ADLog.d("百度开屏 onAdClick");
            }
        }, thirdSdkItem.pid, true);
    }

    public void release() {
    }
}
