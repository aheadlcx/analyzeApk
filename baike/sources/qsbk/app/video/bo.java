package qsbk.app.video;

import android.graphics.Bitmap;
import qsbk.app.ye.videotools.utils.VideoEditer;
import qsbk.app.ye.videotools.utils.VideoEditer.OnCompletionListener;

class bo implements OnCompletionListener {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ bn b;

    bo(bn bnVar, Bitmap bitmap) {
        this.b = bnVar;
        this.a = bitmap;
    }

    public void onCompletion(VideoEditer videoEditer) {
        this.b.d.a(this.b.b, this.a);
    }
}
