package qsbk.app.core.utils;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import qsbk.app.core.utils.PlayerManager.PlayCallback;

class t implements OnCompletionListener {
    final /* synthetic */ PlayCallback a;
    final /* synthetic */ PlayerManager b;

    t(PlayerManager playerManager, PlayCallback playCallback) {
        this.b = playerManager;
        this.a = playCallback;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.a != null) {
            this.a.onComplete();
        }
    }
}
