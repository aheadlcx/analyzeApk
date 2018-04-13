package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.util.Pair;
import java.util.List;

class ag implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Bundle b;
    final /* synthetic */ f c;

    ag(f fVar, String str, Bundle bundle) {
        this.c = fVar;
        this.a = str;
        this.b = bundle;
    }

    public void run() {
        for (IBinder iBinder : this.c.a.b.keySet()) {
            a aVar = (a) this.c.a.b.get(iBinder);
            List<Pair> list = (List) aVar.e.get(this.a);
            if (list != null) {
                for (Pair pair : list) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(this.b, (Bundle) pair.second)) {
                        this.c.a.a(this.a, aVar, (Bundle) pair.second);
                    }
                }
            }
        }
    }
}
