package com.sprite.ads.splash;

import android.app.Activity;
import android.view.ViewGroup;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.splash.SpriteSplashView.Builder;

public class SelfSplashAd extends SplashAdapter {
    public SelfSplashAd(AdItem adItem, ADConfig aDConfig) {
        super(adItem, aDConfig);
    }

    public void release() {
    }

    public void show(Activity activity, ViewGroup viewGroup, SplashADListener splashADListener) {
        new Builder(activity).setSplashADListener(splashADListener).setParentLayout(viewGroup).setData(this.mAdItem).setConfig(this.mAdConfig).build().show();
    }
}
