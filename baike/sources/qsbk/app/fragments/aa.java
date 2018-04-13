package qsbk.app.fragments;

import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;

class aa implements MediaScannerConnectionClient {
    final /* synthetic */ z a;

    aa(z zVar) {
        this.a = zVar;
    }

    public void onScanCompleted(String str, Uri uri) {
        this.a.b.f.disconnect();
    }

    public void onMediaScannerConnected() {
        synchronized (this.a.b.f) {
            if (this.a.b.f.isConnected()) {
                this.a.b.f.scanFile(this.a.a.getPath(), "video/mp4");
            }
        }
    }
}
