package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat.CustomActionCallback;

class o implements Runnable {
    final /* synthetic */ CustomActionCallback a;
    final /* synthetic */ String b;
    final /* synthetic */ Bundle c;
    final /* synthetic */ f d;

    o(f fVar, CustomActionCallback customActionCallback, String str, Bundle bundle) {
        this.d = fVar;
        this.a = customActionCallback;
        this.b = str;
        this.c = bundle;
    }

    public void run() {
        this.a.onError(this.b, this.c, null);
    }
}
