package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import android.util.Log;

class am implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ g c;

    am(g gVar, h hVar, Bundle bundle) {
        this.c = gVar;
        this.a = hVar;
        this.b = bundle;
    }

    public void run() {
        IBinder asBinder = this.a.asBinder();
        this.c.a.b.remove(asBinder);
        DeathRecipient aVar = new a(this.c.a);
        aVar.c = this.a;
        aVar.b = this.b;
        this.c.a.b.put(asBinder, aVar);
        try {
            asBinder.linkToDeath(aVar, 0);
        } catch (RemoteException e) {
            Log.w("MBServiceCompat", "IBinder is already dead.");
        }
    }
}
