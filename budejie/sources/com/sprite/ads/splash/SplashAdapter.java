package com.sprite.ads.splash;

import android.app.Activity;
import android.view.ViewGroup;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;

public abstract class SplashAdapter {
    protected ADConfig mAdConfig;
    protected AdItem mAdItem;

    public SplashAdapter(AdItem adItem, ADConfig aDConfig) {
        this.mAdItem = adItem;
        this.mAdConfig = aDConfig;
    }

    public abstract void release();

    public abstract void show(Activity activity, ViewGroup viewGroup, SplashADListener splashADListener);
}
