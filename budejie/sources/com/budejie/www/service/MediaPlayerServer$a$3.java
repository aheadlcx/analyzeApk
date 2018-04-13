package com.budejie.www.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import com.budejie.www.activity.video.k;
import com.budejie.www.service.MediaPlayerServer.a;

class MediaPlayerServer$a$3 implements OnPreparedListener {
    final /* synthetic */ a a;

    MediaPlayerServer$a$3(a aVar) {
        this.a = aVar;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (!a.d(this.a) && a.e(this.a)) {
            k.a(a.c(this.a)).k();
            this.a.a = "";
            a.a(this.a, true);
            MediaPlayerServer.a.seekTo(0);
            MediaPlayerServer.a.start();
            a.f(this.a).post(new Runnable(this) {
                final /* synthetic */ MediaPlayerServer$a$3 a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (a.a(this.a.a) != null) {
                        a.a(this.a.a).a();
                    }
                }
            });
        }
    }
}
