package qsbk.app.fragments;

import android.content.Context;
import android.os.Looper;
import java.io.File;
import qsbk.app.widget.imageviewer.ImageDownloadSubscriber;

class af extends ImageDownloadSubscriber {
    final /* synthetic */ BrowseLongImgFragment a;

    af(BrowseLongImgFragment browseLongImgFragment, Context context) {
        this.a = browseLongImgFragment;
        super(context);
    }

    protected void a(int i) {
    }

    protected void a(File file) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            this.a.a(file);
        } else {
            this.a.b.post(new ag(this, file));
        }
    }

    protected void a(Throwable th) {
        th.printStackTrace();
    }
}
