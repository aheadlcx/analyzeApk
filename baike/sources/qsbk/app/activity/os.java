package qsbk.app.activity;

import android.animation.Animator;
import qsbk.app.widget.TransitionDraweeView.SimpleAnimationListener;

class os extends SimpleAnimationListener {
    final /* synthetic */ Runnable a;
    final /* synthetic */ ImageViewer b;

    os(ImageViewer imageViewer, Runnable runnable) {
        this.b = imageViewer;
        this.a = runnable;
    }

    public void onAnimationStart(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        this.a.run();
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }
}
