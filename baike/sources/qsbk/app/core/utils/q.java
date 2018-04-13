package qsbk.app.core.utils;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import qsbk.app.core.utils.PlayerManager.PlayCallback;

class q implements OnPreparedListener {
    final /* synthetic */ PlayCallback a;
    final /* synthetic */ PlayerManager b;

    q(PlayerManager playerManager, PlayCallback playCallback) {
        this.b = playerManager;
        this.a = playCallback;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (this.a != null) {
            this.a.onPrepared();
        }
        this.b.b.start();
    }
}
