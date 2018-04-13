package qsbk.app.core.utils;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnInfoListener;

class r implements OnInfoListener {
    final /* synthetic */ PlayerManager a;

    r(PlayerManager playerManager) {
        this.a = playerManager;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }
}
