package com.sprite.ads.nati.loader;

import android.content.Context;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.nati.a.a;
import com.sprite.ads.nati.view.NativeAdDataLoadedListener;

public interface NativeAdLoader extends Refreshable {
    void loadAd(Context context, AdItem adItem);

    void loadAd(Context context, a aVar, NativeAdDataLoadedListener nativeAdDataLoadedListener);

    void release();

    void removeCurrentAd();

    void setLoaderListener(AdLoaderListener adLoaderListener);
}
