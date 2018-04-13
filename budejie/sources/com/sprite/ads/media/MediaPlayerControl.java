package com.sprite.ads.media;

public interface MediaPlayerControl {
    int getCurrentPosition();

    long getDuration();

    int getProgress();

    boolean isPlaying();

    boolean isVideoAD();

    void play();

    void release();

    void replay();

    void stop();
}
