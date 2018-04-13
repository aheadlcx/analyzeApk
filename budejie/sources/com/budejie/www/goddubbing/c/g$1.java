package com.budejie.www.goddubbing.c;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class g$1 implements OnCompletionListener {
    final /* synthetic */ g a;

    g$1(g gVar) {
        this.a = gVar;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (g.a(this.a) != null) {
            g.a(this.a).a();
        }
    }
}
