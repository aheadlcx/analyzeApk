package com.sprite.ads.media;

import android.view.ViewGroup;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.reporter.Reporter;

public abstract class MediaPlayerControler<T extends NativeAdData> implements MediaPlayerControl, Reporter {
    public abstract void bindView(ViewGroup viewGroup);

    public abstract void setDataResoure(T t);

    public abstract void setMediaListener(MediaListener mediaListener);
}
