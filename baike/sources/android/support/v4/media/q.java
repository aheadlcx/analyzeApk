package android.support.v4.media;

import android.content.ComponentName;
import android.util.Log;

class q implements Runnable {
    final /* synthetic */ ComponentName a;
    final /* synthetic */ a b;

    q(a aVar, ComponentName componentName) {
        this.b = aVar;
        this.a = componentName;
    }

    public void run() {
        if (MediaBrowserCompat.a) {
            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + this.a + " this=" + this + " mServiceConnection=" + this.b.a.g);
            this.b.a.b();
        }
        if (this.b.a("onServiceDisconnected")) {
            this.b.a.h = null;
            this.b.a.i = null;
            this.b.a.e.a(null);
            this.b.a.f = 4;
            this.b.a.c.onConnectionSuspended();
        }
    }
}
