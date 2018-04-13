package qsbk.app.live.ui.family;

import android.graphics.Bitmap;

class ao implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ an b;

    ao(an anVar, Bitmap bitmap) {
        this.b = anVar;
        this.a = bitmap;
    }

    public void run() {
        this.b.a.e.setImageBitmap(this.a);
    }
}
