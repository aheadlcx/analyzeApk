package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserServiceCompat.Result;
import android.support.v4.os.ResultReceiver;

class y extends Result<Bundle> {
    final /* synthetic */ ResultReceiver a;
    final /* synthetic */ MediaBrowserServiceCompat b;

    y(MediaBrowserServiceCompat mediaBrowserServiceCompat, Object obj, ResultReceiver resultReceiver) {
        this.b = mediaBrowserServiceCompat;
        this.a = resultReceiver;
        super(obj);
    }

    void a(Bundle bundle) {
        this.a.send(0, bundle);
    }

    void b(Bundle bundle) {
        this.a.send(1, bundle);
    }

    void c(Bundle bundle) {
        this.a.send(-1, bundle);
    }
}
