package qsbk.app.fragments;

import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;

class ac implements MediaScannerConnectionClient {
    final /* synthetic */ String a;
    final /* synthetic */ BrowseImgFragment b;

    ac(BrowseImgFragment browseImgFragment, String str) {
        this.b = browseImgFragment;
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
