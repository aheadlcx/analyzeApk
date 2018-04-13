package qsbk.app.video;

import android.media.MediaPlayer;

final class g implements Runnable {
    final /* synthetic */ MediaPlayer a;

    g(MediaPlayer mediaPlayer) {
        this.a = mediaPlayer;
    }

    public void run() {
        try {
            this.a.setDisplay(null);
        } catch (Exception e) {
        }
        try {
            this.a.stop();
        } catch (Exception e2) {
        }
        try {
            this.a.reset();
        } catch (Exception e3) {
        }
        MediaPlayerPool.a.add(this.a);
    }
}
