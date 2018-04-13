package com.budejie.www.service;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.service.MediaPlayerServer.a;
import com.budejie.www.util.aa;

class MediaPlayerServer$a$2 implements OnErrorListener {
    final /* synthetic */ a a;

    MediaPlayerServer$a$2(a aVar) {
        this.a = aVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.a = "";
        if (i == R$styleable.Theme_Custom_posts_detail_forward_icon && i2 == -1003) {
            Toast.makeText(a.c(this.a), R.string.downLoad_error, 0).show();
            aa.a("PlayerActionImpl", "url is 404");
        }
        MediaPlayerServer.a.reset();
        a.a(this.a, "");
        if (!(i == -38 && i2 == 0)) {
            Toast.makeText(a.c(this.a), R.string.downLoad_error_please_improve_network, 0).show();
            aa.a("PlayerActionImpl", "播放出错" + i + "," + i2);
        }
        return false;
    }
}
