package com.r0adkll.slidr.model;

public interface SlidrListener {
    void onSlideChange(float f);

    void onSlideClosed();

    void onSlideOpened();

    void onSlideStateChanged(int i);
}
