package com.sprite.ads.media;

import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;

public interface MediaAdListener {
    void loadFailure(ErrorCode errorCode);

    void loadSuccess(AdItem adItem);
}
