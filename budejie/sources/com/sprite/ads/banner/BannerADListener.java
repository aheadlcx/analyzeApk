package com.sprite.ads.banner;

import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.nati.reporter.Reporter;

public abstract class BannerADListener {
    public abstract void onADReceive(Reporter reporter, boolean z);

    public abstract void onADSkip(AdItem adItem);

    public abstract void onNoAD(int i);
}
