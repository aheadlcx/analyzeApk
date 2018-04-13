package qsbk.app.activity;

import android.os.SystemClock;

class vm implements Runnable {
    final /* synthetic */ MyInfoActivity a;

    vm(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void run() {
        if (this.a.bG == 0) {
            this.a.f.setProgress(this.a.bG);
            this.a.bG = this.a.bG + 1;
        } else if (this.a.bG > 0 && this.a.bG < 100) {
            this.a.f.setProgress(this.a.bG);
            this.a.bG = this.a.bG + 1;
        } else if (this.a.bG == 100) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.a.bH != null) {
                this.a.bH.cancel();
            }
            this.a.bI.removeCallbacks(this.a.cb);
            this.a.f.setProgress(this.a.bG);
            this.a.bO.vibrate(1000);
            this.a.bG = 0;
            this.a.bN = (int) (elapsedRealtime - this.a.bM);
            this.a.f.setProgress(this.a.bG);
            this.a.a(this.a.bN, 0);
            this.a.bJ = true;
        }
    }
}
