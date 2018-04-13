package qsbk.app.im;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class ba implements OnCompletionListener {
    final /* synthetic */ ax a;

    ba(ax axVar) {
        this.a = axVar;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        ChatMsg b = this.a.b(this.a.d);
        this.a.b();
        if (b != null) {
            ay c = this.a.a(b);
            if (c != null) {
                this.a.b(c);
            } else {
                this.a.c(b);
            }
        }
    }
}
