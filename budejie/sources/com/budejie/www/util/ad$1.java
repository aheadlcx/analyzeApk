package com.budejie.www.util;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

class ad$1 implements OnCompletionListener {
    final /* synthetic */ ad a;

    ad$1(ad adVar) {
        this.a = adVar;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.i("MediaPlayerHandler", "播放结束");
        if (ad.a(this.a) != null) {
            ad.a(this.a).a(0);
        }
        this.a.f();
    }
}
