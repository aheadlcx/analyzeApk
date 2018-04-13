package pl.droidsonroids.gif;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.Iterator;

class d extends Handler {
    private final WeakReference<b> a;

    public d(b bVar) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(bVar);
    }

    public void handleMessage(Message message) {
        b bVar = (b) this.a.get();
        if (bVar != null) {
            if (message.what == -1) {
                bVar.invalidateSelf();
                return;
            }
            Iterator it = bVar.g.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(message.what);
            }
        }
    }
}
