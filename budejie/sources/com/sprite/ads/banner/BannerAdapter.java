package com.sprite.ads.banner;

import android.content.Context;
import android.view.ViewGroup;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;

public abstract class BannerAdapter {
    protected ADConfig mAdConfig;
    protected AdItem mAdItem;

    public BannerAdapter(AdItem adItem, ADConfig aDConfig) {
        this.mAdItem = adItem;
        this.mAdConfig = aDConfig;
    }

    public abstract void loadAd(Context context, ViewGroup viewGroup, BannerADListener bannerADListener);
}
