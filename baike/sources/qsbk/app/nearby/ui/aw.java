package qsbk.app.nearby.ui;

import android.os.SystemClock;
import java.util.TimerTask;

class aw extends TimerTask {
    final /* synthetic */ ShakeDialogFragment a;

    aw(ShakeDialogFragment shakeDialogFragment) {
        this.a = shakeDialogFragment;
    }

    public void run() {
        if (SystemClock.elapsedRealtime() - this.a.z > 50000) {
            this.a.K = 0;
            this.a.j.post(this.a.D);
            return;
        }
        this.a.j.post(this.a.P);
    }
}
