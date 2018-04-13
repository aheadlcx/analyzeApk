package com.ak.android.player;

import android.view.View;

public interface VideoAdPlayer {
    void continuePlay();

    void destroy();

    void full();

    View getUI();

    void pause();

    void play(boolean z);

    void prepare();

    void restore();

    void setListener(VideoAdPlayerListener videoAdPlayerListener);

    void stop();
}
