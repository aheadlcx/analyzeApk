package qsbk.app.nearby.ui;

import android.os.SystemClock;
import java.util.TimerTask;

class av extends TimerTask {
    final /* synthetic */ ShakeDialogFragment a;

    av(ShakeDialogFragment shakeDialogFragment) {
        this.a = shakeDialogFragment;
    }

    public void run() {
        if (SystemClock.elapsedRealtime() - this.a.z > 50000) {
            this.a.j.post(this.a.D);
            this.a.K = 0;
            return;
        }
        this.a.j.post(this.a.O);
    }
}
