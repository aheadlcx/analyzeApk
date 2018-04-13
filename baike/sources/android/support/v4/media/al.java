package android.support.v4.media;

import android.support.v4.os.ResultReceiver;
import android.util.Log;

class al implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ String b;
    final /* synthetic */ ResultReceiver c;
    final /* synthetic */ g d;

    al(g gVar, h hVar, String str, ResultReceiver resultReceiver) {
        this.d = gVar;
        this.a = hVar;
        this.b = str;
        this.c = resultReceiver;
    }

    public void run() {
        a aVar = (a) this.d.a.b.get(this.a.asBinder());
        if (aVar == null) {
            Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.b);
        } else {
            this.d.a.a(this.b, aVar, this.c);
        }
    }
}
