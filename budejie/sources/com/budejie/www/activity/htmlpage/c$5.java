package com.budejie.www.activity.htmlpage;

import android.text.TextUtils;
import com.sprite.ads.interstitial.InterstitalAd;

class c$5 implements Runnable {
    final /* synthetic */ c a;

    c$5(c cVar) {
        this.a = cVar;
    }

    public void run() {
        if (TextUtils.isEmpty(c.n(this.a))) {
            InterstitalAd.loadInterstitialAd(c.g(this.a));
        } else {
            InterstitalAd.loadInterstitialAd(c.g(this.a), c.n(this.a));
        }
    }
}
