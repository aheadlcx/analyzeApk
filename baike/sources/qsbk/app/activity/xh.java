package qsbk.app.activity;

import android.os.SystemClock;
import java.util.TimerTask;

class xh extends TimerTask {
    final /* synthetic */ xf a;

    xh(xf xfVar) {
        this.a = xfVar;
    }

    public void run() {
        if (SystemClock.elapsedRealtime() - this.a.a.bM > 50000) {
            this.a.a.bG = 0;
            this.a.a.bI.post(this.a.a.bL);
            return;
        }
        this.a.a.bI.post(this.a.a.bK);
    }
}
