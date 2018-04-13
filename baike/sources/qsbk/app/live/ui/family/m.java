package qsbk.app.live.ui.family;

import android.net.Uri;

class m implements Runnable {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public void run() {
        this.a.c.i.setImageURI(Uri.parse("file://" + this.a.b));
    }
}
