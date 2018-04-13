package qsbk.app.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class av implements OnCompletionListener {
    final /* synthetic */ VideoEditPlayView a;

    av(VideoEditPlayView videoEditPlayView) {
        this.a = videoEditPlayView;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.a.o != null) {
            this.a.o.onCompletion(mediaPlayer);
        }
    }
}
