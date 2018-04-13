package android.support.v4.media;

import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.util.Log;
import java.util.Iterator;

class af implements Runnable {
    final /* synthetic */ Token a;
    final /* synthetic */ f b;

    af(f fVar, Token token) {
        this.b = fVar;
        this.a = token;
    }

    public void run() {
        Iterator it = this.b.a.b.values().iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            try {
                aVar.c.onConnect(aVar.d.getRootId(), this.a, aVar.d.getExtras());
            } catch (RemoteException e) {
                Log.w("MBServiceCompat", "Connection for " + aVar.a + " is no longer valid.");
                it.remove();
            }
        }
    }
}
