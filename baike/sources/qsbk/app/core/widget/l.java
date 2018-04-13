package qsbk.app.core.widget;

import android.util.Log;

class l implements Runnable {
    final /* synthetic */ FrameAnimationView a;

    l(FrameAnimationView frameAnimationView) {
        this.a = frameAnimationView;
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        this.a.d();
        long currentTimeMillis2 = System.currentTimeMillis();
        Log.d(FrameAnimationView.a, this.a.c + " decodeTime: " + (currentTimeMillis2 - currentTimeMillis));
        this.a.o = (int) ((currentTimeMillis2 - currentTimeMillis) + ((long) this.a.o));
    }
}
