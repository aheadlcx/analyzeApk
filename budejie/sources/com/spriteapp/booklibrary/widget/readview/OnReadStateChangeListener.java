package com.spriteapp.booklibrary.widget.readview;

public interface OnReadStateChangeListener {
    void onCenterClick();

    void onChapterChanged(int i);

    void onFlip();

    void onLoadChapterFailure(int i);

    void onPageChanged(int i, int i2);
}
