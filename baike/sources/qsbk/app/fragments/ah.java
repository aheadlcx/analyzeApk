package qsbk.app.fragments;

import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;

class ah implements MediaScannerConnectionClient {
    final /* synthetic */ String a;
    final /* synthetic */ BrowseLongImgFragment b;

    ah(BrowseLongImgFragment browseLongImgFragment, String str) {
        this.b = browseLongImgFragment;
        this.a = str;
    }

    public void onScanCompleted(String str, Uri uri) {
        this.b.e.disconnect();
    }

    public void onMediaScannerConnected() {
        synchronized (this.b.e) {
            if (this.b.e.isConnected()) {
                this.b.e.scanFile(this.a, "image/jpeg");
            }
        }
    }
}
