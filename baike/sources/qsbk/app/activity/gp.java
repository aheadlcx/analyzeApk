package qsbk.app.activity;

import android.view.ViewTreeObserver.OnPreDrawListener;

class gp implements OnPreDrawListener {
    final /* synthetic */ CircleArticleImageViewer a;

    gp(CircleArticleImageViewer circleArticleImageViewer) {
        this.a = circleArticleImageViewer;
    }

    public boolean onPreDraw() {
        this.a.t.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.b();
        return true;
    }
}
