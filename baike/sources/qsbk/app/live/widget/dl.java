package qsbk.app.live.widget;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class dl implements OnCompletionListener {
    final /* synthetic */ GameWinDialog a;

    dl(GameWinDialog gameWinDialog) {
        this.a = gameWinDialog;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.a.w != null) {
            this.a.w.release();
            this.a.w = null;
        }
    }
}
