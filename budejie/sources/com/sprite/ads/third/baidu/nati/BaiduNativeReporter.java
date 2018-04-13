package com.sprite.ads.third.baidu.nati;

import android.view.View;
import com.sprite.ads.nati.reporter.Reporter;

public class BaiduNativeReporter implements Reporter {
    BaiduNativeAdData baiduNativeAdData;

    public BaiduNativeReporter(BaiduNativeAdData baiduNativeAdData) {
        this.baiduNativeAdData = baiduNativeAdData;
    }

    public void onClicked(View view) {
        this.baiduNativeAdData.nativeResponse.handleClick(view);
    }

    public void onExposured(View view) {
        this.baiduNativeAdData.nativeResponse.recordImpression(view);
    }

    public void onPlay(View view) {
    }
}
