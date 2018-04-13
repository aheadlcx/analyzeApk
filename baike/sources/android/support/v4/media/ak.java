package android.support.v4.media;

import android.os.IBinder;
import android.util.Log;

class ak implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ String b;
    final /* synthetic */ IBinder c;
    final /* synthetic */ g d;

    ak(g gVar, h hVar, String str, IBinder iBinder) {
        this.d = gVar;
        this.a = hVar;
        this.b = str;
        this.c = iBinder;
    }

    public void run() {
        a aVar = (a) this.d.a.b.get(this.a.asBinder());
        if (aVar == null) {
            Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + this.b);
        } else if (!this.d.a.a(this.b, aVar, this.c)) {
            Log.w("MBServiceCompat", "removeSubscription called for " + this.b + " which is not subscribed");
        }
    }
}
