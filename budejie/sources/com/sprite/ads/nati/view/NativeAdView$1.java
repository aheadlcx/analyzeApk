package com.sprite.ads.nati.view;

import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.nati.NativeAdData;

class NativeAdView$1 implements NativeAdDataLoadedListener {
    final /* synthetic */ NativeAdView this$0;
    final /* synthetic */ NativeAdDataLoadedListener val$listener;

    NativeAdView$1(NativeAdView nativeAdView, NativeAdDataLoadedListener nativeAdDataLoadedListener) {
        this.this$0 = nativeAdView;
        this.val$listener = nativeAdDataLoadedListener;
    }

    public void onAdLoaded(NativeAdData nativeAdData) {
        if (nativeAdData == null) {
            this.val$listener.onAdLoaded(nativeAdData);
            return;
        }
        ADLog.d("广告曝光:" + this.this$0.adItemRef);
        this.this$0.adItemRef.onExposured(this.this$0);
        this.val$listener.onAdLoaded(nativeAdData);
        ADLog.d("nativeAdData:" + nativeAdData);
    }
}
