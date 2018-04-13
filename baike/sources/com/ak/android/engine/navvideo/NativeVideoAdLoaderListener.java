package com.ak.android.engine.navvideo;

import com.ak.android.engine.navbase.BaseNativeAdLoaderListener;
import java.util.ArrayList;

public interface NativeVideoAdLoaderListener extends BaseNativeAdLoaderListener {
    void onAdLoadSuccess(ArrayList<NativeVideoAd> arrayList);
}
