package com.ak.android.engine.nav;

import com.ak.android.engine.navbase.BaseNativeAdLoaderListener;
import java.util.ArrayList;

public interface NativeAdLoaderListener extends BaseNativeAdLoaderListener {
    void onAdLoadSuccess(ArrayList<NativeAd> arrayList);
}
