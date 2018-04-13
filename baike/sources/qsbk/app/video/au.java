package qsbk.app.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnInfoListener;

class au implements OnInfoListener {
    final /* synthetic */ VideoEditPlayView a;

    au(VideoEditPlayView videoEditPlayView) {
        this.a = videoEditPlayView;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }
}
