package qsbk.app.activity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import qsbk.app.R;

class qt implements OnCompletionListener {
    final /* synthetic */ AnimationDrawable a;
    final /* synthetic */ d b;

    qt(d dVar, AnimationDrawable animationDrawable) {
        this.b = dVar;
        this.a = animationDrawable;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.stop();
        this.b.g.setBackgroundResource(0);
        this.b.g.setBackgroundResource(R.drawable.bg_voice_record_play);
    }
}
