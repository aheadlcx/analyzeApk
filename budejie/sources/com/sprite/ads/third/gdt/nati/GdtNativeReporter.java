package com.sprite.ads.third.gdt.nati;

import android.view.View;
import com.sprite.ads.nati.reporter.Reporter;

public class GdtNativeReporter implements Reporter {
    GdtNativeAdData gdtNativeAdData;

    public GdtNativeReporter(GdtNativeAdData gdtNativeAdData) {
        this.gdtNativeAdData = gdtNativeAdData;
    }

    public void onClicked(View view) {
        this.gdtNativeAdData.adDataRef.onClicked(view);
    }

    public void onExposured(View view) {
        this.gdtNativeAdData.adDataRef.onExposured(view);
    }

    public void onPlay(View view) {
    }
}
