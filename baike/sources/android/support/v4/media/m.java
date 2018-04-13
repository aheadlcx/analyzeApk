package android.support.v4.media;

import android.support.v4.media.MediaBrowserCompat.ItemCallback;

class m implements Runnable {
    final /* synthetic */ ItemCallback a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    m(f fVar, ItemCallback itemCallback, String str) {
        this.c = fVar;
        this.a = itemCallback;
        this.b = str;
    }

    public void run() {
        this.a.onError(this.b);
    }
}
