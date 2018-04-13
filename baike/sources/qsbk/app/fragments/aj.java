package qsbk.app.fragments;

import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;

class aj implements MediaScannerConnectionClient {
    final /* synthetic */ String a;
    final /* synthetic */ BrowseUltraFragment b;

    aj(BrowseUltraFragment browseUltraFragment, String str) {
        this.b = browseUltraFragment;
        this.a = str;
    }

    public void onScanCompleted(String str, Uri uri) {
        this.b.c.disconnect();
    }

    public void onMediaScannerConnected() {
        synchronized (this.b.c) {
            if (this.b.c.isConnected()) {
                this.b.c.scanFile(this.a, "image/jpeg");
            }
        }
    }
}
