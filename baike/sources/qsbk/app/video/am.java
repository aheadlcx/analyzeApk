package qsbk.app.video;

import android.graphics.Bitmap;
import qsbk.app.ye.videotools.utils.VideoEditer;
import qsbk.app.ye.videotools.utils.VideoEditer.OnCompletionListener;

class am implements OnCompletionListener {
    final /* synthetic */ String a;
    final /* synthetic */ Bitmap b;
    final /* synthetic */ al c;

    am(al alVar, String str, Bitmap bitmap) {
        this.c = alVar;
        this.a = str;
        this.b = bitmap;
    }

    public void onCompletion(VideoEditer videoEditer) {
        this.c.a.a(this.a, this.b);
    }
}
