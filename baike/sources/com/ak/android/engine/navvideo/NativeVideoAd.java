package com.ak.android.engine.navvideo;

import com.ak.android.engine.nav.NativeAd;

public interface NativeVideoAd extends NativeAd {
    public static final int VIDEO_COMPLETE = 85;
    public static final int VIDEO_CONTINUE = 83;
    public static final int VIDEO_EXIT = 84;
    public static final int VIDEO_PAUSE = 82;
    public static final int VIDEO_START = 81;

    boolean hasVideo();

    void onVideoChanged(int i, int i2);
}
