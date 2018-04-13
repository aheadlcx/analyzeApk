package com.budejie.www.util;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;

class ad$3 implements OnPreparedListener {
    final /* synthetic */ ad a;

    ad$3(ad adVar) {
        this.a = adVar;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        ad.b(this.a).seekTo(0);
        ad.b(this.a).start();
        if (ad.a(this.a) != null) {
            ad.a(this.a).a();
        }
    }
}
