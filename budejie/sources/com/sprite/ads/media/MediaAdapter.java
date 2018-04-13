package com.sprite.ads.media;

import android.content.Context;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;

public abstract class MediaAdapter {
    protected ADConfig mAdConfig;
    protected AdItem mAdItem;

    public MediaAdapter(AdItem adItem, ADConfig aDConfig) {
        this.mAdItem = adItem;
        this.mAdConfig = aDConfig;
    }

    protected abstract void loadAd(Context context, NativeMediaADListener nativeMediaADListener);
}
