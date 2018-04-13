package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat.SearchCallback;

class n implements Runnable {
    final /* synthetic */ SearchCallback a;
    final /* synthetic */ String b;
    final /* synthetic */ Bundle c;
    final /* synthetic */ f d;

    n(f fVar, SearchCallback searchCallback, String str, Bundle bundle) {
        this.d = fVar;
        this.a = searchCallback;
        this.b = str;
        this.c = bundle;
    }

    public void run() {
        this.a.onError(this.b, this.c);
    }
}
