package qsbk.app.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class at implements OnErrorListener {
    final /* synthetic */ VideoEditPlayView a;

    at(VideoEditPlayView videoEditPlayView) {
        this.a = videoEditPlayView;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }
}
