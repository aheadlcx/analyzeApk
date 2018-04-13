package qsbk.app.video;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class ah implements OnCompletionListener {
    final /* synthetic */ VideoEditActivity a;

    ah(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.p = mediaPlayer.getDuration();
        this.a.o = this.a.p;
        this.a.k.post(new ai(this));
    }
}
