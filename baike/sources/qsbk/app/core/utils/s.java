package qsbk.app.core.utils;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class s implements OnErrorListener {
    final /* synthetic */ PlayerManager a;

    s(PlayerManager playerManager) {
        this.a = playerManager;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        LogUtils.d(PlayerManager.a, "play error " + i + ", " + i2);
        return false;
    }
}
