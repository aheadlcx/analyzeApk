package qsbk.app.activity;

import android.os.SystemClock;
import java.util.TimerTask;

class xg extends TimerTask {
    final /* synthetic */ xf a;

    xg(xf xfVar) {
        this.a = xfVar;
    }

    public void run() {
        if (SystemClock.elapsedRealtime() - this.a.a.bM > 50000) {
            this.a.a.bI.post(this.a.a.bL);
            this.a.a.bG = 0;
            return;
        }
        this.a.a.bI.post(this.a.a.cb);
    }
}
