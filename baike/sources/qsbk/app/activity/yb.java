package qsbk.app.activity;

import android.animation.Animator;
import qsbk.app.widget.TransitionDraweeView.SimpleAnimationListener;

class yb extends SimpleAnimationListener {
    final /* synthetic */ NewImageViewer a;

    yb(NewImageViewer newImageViewer) {
        this.a = newImageViewer;
    }

    public void onAnimationStart(Animator animator) {
        this.a.d.setVisibility(4);
    }

    public void onAnimationEnd(Animator animator) {
        this.a.finish();
        this.a.overridePendingTransition(0, 0);
    }
}
