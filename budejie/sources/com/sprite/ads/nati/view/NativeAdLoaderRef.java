package com.sprite.ads.nati.view;

import com.sprite.ads.nati.a.a;
import com.sprite.ads.nati.loader.NativeAdLoader;

public class NativeAdLoaderRef {
    NativeAdLoader adLoader;
    a adRef;

    public NativeAdLoaderRef(a aVar, NativeAdLoader nativeAdLoader) {
        this.adRef = aVar;
        this.adLoader = nativeAdLoader;
    }
}
