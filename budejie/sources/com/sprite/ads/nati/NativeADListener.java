package com.sprite.ads.nati;

import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;
import java.util.List;

public interface NativeADListener {
    void loadFailure(ErrorCode errorCode);

    void loadSuccess(List<NativeAdRef> list);

    void onADSkip(AdItem adItem);

    void preLoad(List<AdItem> list);
}
