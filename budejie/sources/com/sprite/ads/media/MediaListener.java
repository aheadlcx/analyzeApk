package com.sprite.ads.media;

public interface MediaListener {
    void onADClicked();

    void onVideoComplete();

    void onVideoError();

    void onVideoReady();

    void onVideoReplay();

    void onVideoStart();

    void onVideoStop();
}
