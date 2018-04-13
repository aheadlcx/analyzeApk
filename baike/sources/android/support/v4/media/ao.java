package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

class ao implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ String b;
    final /* synthetic */ Bundle c;
    final /* synthetic */ ResultReceiver d;
    final /* synthetic */ g e;

    ao(g gVar, h hVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
        this.e = gVar;
        this.a = hVar;
        this.b = str;
        this.c = bundle;
        this.d = resultReceiver;
    }

    public void run() {
        a aVar = (a) this.e.a.b.get(this.a.asBinder());
        if (aVar == null) {
            Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.b);
        } else {
            this.e.a.a(this.b, this.c, aVar, this.d);
        }
    }
}
