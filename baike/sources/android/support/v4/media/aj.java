package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

class aj implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ String b;
    final /* synthetic */ IBinder c;
    final /* synthetic */ Bundle d;
    final /* synthetic */ g e;

    aj(g gVar, h hVar, String str, IBinder iBinder, Bundle bundle) {
        this.e = gVar;
        this.a = hVar;
        this.b = str;
        this.c = iBinder;
        this.d = bundle;
    }

    public void run() {
        a aVar = (a) this.e.a.b.get(this.a.asBinder());
        if (aVar == null) {
            Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.b);
        } else {
            this.e.a.a(this.b, aVar, this.c, this.d);
        }
    }
}
