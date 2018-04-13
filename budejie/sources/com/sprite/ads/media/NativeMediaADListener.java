package com.sprite.ads.media;

import com.sprite.ads.internal.bean.data.MediaAdItem;
import java.util.List;

public interface NativeMediaADListener<T> {
    void onADLoaded(List<MediaAdItem> list);

    void onADStatusChanged(T t);

    void onNoAD(int i);
}
