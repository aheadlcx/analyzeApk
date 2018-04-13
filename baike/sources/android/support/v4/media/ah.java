package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

class ah implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ String b;
    final /* synthetic */ Bundle c;
    final /* synthetic */ int d;
    final /* synthetic */ g e;

    ah(g gVar, h hVar, String str, Bundle bundle, int i) {
        this.e = gVar;
        this.a = hVar;
        this.b = str;
        this.c = bundle;
        this.d = i;
    }

    public void run() {
        IBinder asBinder = this.a.asBinder();
        this.e.a.b.remove(asBinder);
        a aVar = new a(this.e.a);
        aVar.a = this.b;
        aVar.b = this.c;
        aVar.c = this.a;
        aVar.d = this.e.a.onGetRoot(this.b, this.d, this.c);
        if (aVar.d == null) {
            Log.i("MBServiceCompat", "No root for client " + this.b + " from service " + getClass().getName());
            try {
                this.a.onConnectFailed();
                return;
            } catch (RemoteException e) {
                Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.b);
                return;
            }
        }
        try {
            this.e.a.b.put(asBinder, aVar);
            asBinder.linkToDeath(aVar, 0);
            if (this.e.a.e != null) {
                this.a.onConnect(aVar.d.getRootId(), this.e.a.e, aVar.d.getExtras());
            }
        } catch (RemoteException e2) {
            Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.b);
            this.e.a.b.remove(asBinder);
        }
    }
}
