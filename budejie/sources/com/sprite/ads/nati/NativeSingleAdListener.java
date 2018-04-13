package com.sprite.ads.nati;

import com.sprite.ads.internal.bean.data.AdItem;
import java.util.List;

public abstract class NativeSingleAdListener implements NativeADListener {
    public abstract void loadAd(NativeAdRef nativeAdRef);

    public void loadSuccess(List<NativeAdRef> list) {
    }

    public void onADSkip(AdItem adItem) {
    }

    public void preLoad(List<AdItem> list) {
    }
}
