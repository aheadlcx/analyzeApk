package com.budejie.www.util;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.util.Log;

class ad$2 implements OnErrorListener {
    final /* synthetic */ ad a;

    ad$2(ad adVar) {
        this.a = adVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        mediaPlayer.reset();
        Log.i("MediaPlayerHandler", "播放出错");
        return false;
    }
}
