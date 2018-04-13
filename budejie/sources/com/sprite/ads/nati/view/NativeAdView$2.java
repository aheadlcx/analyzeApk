package com.sprite.ads.nati.view;

import com.sprite.ads.nati.reporter.Reporter.OnChangeAdListener;

class NativeAdView$2 implements OnChangeAdListener {
    final /* synthetic */ NativeAdView this$0;

    NativeAdView$2(NativeAdView nativeAdView) {
        this.this$0 = nativeAdView;
    }

    public void onChange() {
        this.this$0.adLoader.removeCurrentAd();
        NativeAdView.access$000(this.this$0).onChange();
    }
}
