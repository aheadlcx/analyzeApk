package com.sprite.ads.nati;

import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;
import java.util.List;

class NativeAd$2 implements NativeADListener {
    final /* synthetic */ NativeSingleAdListener a;
    final /* synthetic */ NativeAd b;

    NativeAd$2(NativeAd nativeAd, NativeSingleAdListener nativeSingleAdListener) {
        this.b = nativeAd;
        this.a = nativeSingleAdListener;
    }

    public void loadFailure(ErrorCode errorCode) {
        this.a.loadFailure(errorCode);
    }

    public void loadSuccess(List<NativeAdRef> list) {
        if (list == null || list.size() <= 0) {
            this.a.loadFailure(new ErrorCode(11));
        } else {
            this.a.loadAd((NativeAdRef) list.get(0));
        }
    }

    public void onADSkip(AdItem adItem) {
    }

    public void preLoad(List<AdItem> list) {
        this.a.preLoad(list);
    }
}
