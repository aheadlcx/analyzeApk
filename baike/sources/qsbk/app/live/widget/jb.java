package qsbk.app.live.widget;

import android.graphics.Bitmap;

class jb implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ ja b;

    jb(ja jaVar, Bitmap bitmap) {
        this.b = jaVar;
        this.a = bitmap;
    }

    public void run() {
        if (this.a != null) {
            this.b.a.setDynamicImage(this.a, this.b.b.b.ak);
        }
    }
}
