package com.budejie.www.activity.video;

import android.app.Activity;
import com.budejie.www.util.w;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;
import com.sprite.ads.nati.NativeAdRef;
import com.sprite.ads.nati.NativeSingleAdListener;

class k$2 extends NativeSingleAdListener {
    final /* synthetic */ k a;

    k$2(k kVar) {
        this.a = kVar;
    }

    public void loadFailure(ErrorCode errorCode) {
        k.a(this.a, null);
    }

    public void loadAd(NativeAdRef nativeAdRef) {
        k.a(this.a, nativeAdRef);
    }

    public void onADSkip(AdItem adItem) {
        w.a((Activity) k.a(this.a), false).a(adItem.getUrl());
    }
}
