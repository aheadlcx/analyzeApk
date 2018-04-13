package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.util.Pair;
import java.util.List;

class ab implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ c c;

    ab(c cVar, String str, Bundle bundle) {
        this.c = cVar;
        this.a = str;
        this.b = bundle;
    }

    public void run() {
        for (IBinder iBinder : this.c.d.b.keySet()) {
            a aVar = (a) this.c.d.b.get(iBinder);
            List<Pair> list = (List) aVar.e.get(this.a);
            if (list != null) {
                for (Pair pair : list) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(this.b, (Bundle) pair.second)) {
                        this.c.d.a(this.a, aVar, (Bundle) pair.second);
                    }
                }
            }
        }
    }
}
