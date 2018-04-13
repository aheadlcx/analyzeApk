package android.support.v4.media;

import android.os.IBinder;

class an implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ g b;

    an(g gVar, h hVar) {
        this.b = gVar;
        this.a = hVar;
    }

    public void run() {
        IBinder asBinder = this.a.asBinder();
        a aVar = (a) this.b.a.b.remove(asBinder);
        if (aVar != null) {
            asBinder.unlinkToDeath(aVar, 0);
        }
    }
}
