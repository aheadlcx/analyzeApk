package qsbk.app.activity;

import android.view.ViewTreeObserver.OnPreDrawListener;

class oq implements OnPreDrawListener {
    final /* synthetic */ ImageViewer a;

    oq(ImageViewer imageViewer) {
        this.a = imageViewer;
    }

    public boolean onPreDraw() {
        this.a.k.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.e();
        return true;
    }
}
