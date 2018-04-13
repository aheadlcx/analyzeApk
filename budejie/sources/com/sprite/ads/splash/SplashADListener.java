package com.sprite.ads.splash;

import com.sprite.ads.internal.bean.data.AdItem;

public interface SplashADListener {
    void onADDismissed();

    void onADPresent(AdItem adItem);

    void onADSkip(AdItem adItem);

    void onNoAD(int i);
}
