package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.MediaSessionCompat.Token;

class aa implements Runnable {
    final /* synthetic */ Token a;
    final /* synthetic */ c b;

    aa(c cVar, Token token) {
        this.b = cVar;
        this.a = token;
    }

    public void run() {
        if (!this.b.a.isEmpty()) {
            IMediaSession extraBinder = this.a.getExtraBinder();
            if (extraBinder != null) {
                for (Bundle putBinder : this.b.a) {
                    BundleCompat.putBinder(putBinder, "extra_session_binder", extraBinder.asBinder());
                }
            }
            this.b.a.clear();
        }
        MediaBrowserServiceCompatApi21.setSessionToken(this.b.b, this.a.getToken());
    }
}
