package android.support.v4.media;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

class p implements Runnable {
    final /* synthetic */ ComponentName a;
    final /* synthetic */ IBinder b;
    final /* synthetic */ a c;

    p(a aVar, ComponentName componentName, IBinder iBinder) {
        this.c = aVar;
        this.a = componentName;
        this.b = iBinder;
    }

    public void run() {
        if (MediaBrowserCompat.a) {
            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + this.a + " binder=" + this.b);
            this.c.a.b();
        }
        if (this.c.a("onServiceConnected")) {
            this.c.a.h = new h(this.b, this.c.a.d);
            this.c.a.i = new Messenger(this.c.a.e);
            this.c.a.e.a(this.c.a.i);
            this.c.a.f = 2;
            try {
                if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    this.c.a.b();
                }
                this.c.a.h.a(this.c.a.a, this.c.a.i);
            } catch (RemoteException e) {
                Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.c.a.b);
                if (MediaBrowserCompat.a) {
                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                    this.c.a.b();
                }
            }
        }
    }
}
