package com.budejie.www.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import com.budejie.www.service.MediaPlayerServer.a;
import com.budejie.www.util.aa;

class MediaPlayerServer$a$1 implements OnCompletionListener {
    final /* synthetic */ a a;

    MediaPlayerServer$a$1(a aVar) {
        this.a = aVar;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        aa.a("PlayerActionImpl", "播放结束");
        if (a.a(this.a) != null) {
            a.a(this.a).a(0);
        }
        this.a.a = "";
        this.a.h();
        a.b(this.a);
    }
}
