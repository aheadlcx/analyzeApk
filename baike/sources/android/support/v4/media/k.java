package android.support.v4.media;

import android.os.RemoteException;
import android.util.Log;

class k implements Runnable {
    final /* synthetic */ f a;

    k(f fVar) {
        this.a = fVar;
    }

    public void run() {
        if (this.a.i != null) {
            try {
                this.a.h.a(this.a.i);
            } catch (RemoteException e) {
                Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.a.b);
            }
        }
        int i = this.a.f;
        this.a.a();
        if (i != 0) {
            this.a.f = i;
        }
        if (MediaBrowserCompat.a) {
            Log.d("MediaBrowserCompat", "disconnect...");
            this.a.b();
        }
    }
}
