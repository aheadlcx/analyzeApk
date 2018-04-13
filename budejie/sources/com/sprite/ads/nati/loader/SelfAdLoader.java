package com.sprite.ads.nati.loader;

import android.content.Context;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.nati.a.a;
import com.sprite.ads.nati.a.b;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.nati.reporter.SelfReporter;
import com.sprite.ads.nati.view.NativeAdDataLoadedListener;

public class SelfAdLoader implements NativeAdLoader {
    public void loadAd(Context context, AdItem adItem) {
    }

    public void loadAd(Context context, a aVar, NativeAdDataLoadedListener nativeAdDataLoadedListener) {
        Reporter selfReporter = new SelfReporter(aVar.b());
        selfReporter.setDownwarn(aVar.a());
        aVar.a(selfReporter);
        nativeAdDataLoadedListener.onAdLoaded(new b(aVar.b()));
    }

    public void release() {
    }

    public void removeCurrentAd() {
    }

    public void setLoaderListener(AdLoaderListener adLoaderListener) {
    }

    public void startRefreshTask() {
    }
}
