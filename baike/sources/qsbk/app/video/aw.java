package qsbk.app.video;

import java.util.Timer;
import java.util.TimerTask;

class aw extends TimerTask {
    final /* synthetic */ Timer a;
    final /* synthetic */ int b;
    final /* synthetic */ VideoEditPlayView c;

    aw(VideoEditPlayView videoEditPlayView, Timer timer, int i) {
        this.c = videoEditPlayView;
        this.a = timer;
        this.b = i;
    }

    public void run() {
        try {
            if (this.c.j == null) {
                this.a.cancel();
            } else if (this.b <= this.c.j.getCurrentPosition()) {
                this.c.pause();
                this.a.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
