package com.sprite.ads.media;

import com.sprite.ads.internal.bean.data.MediaAdItem;

public interface ADStatusChangeListner<T> {
    void onADStatusChanged(MediaAdItem<T> mediaAdItem);
}
