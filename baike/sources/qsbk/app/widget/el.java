package qsbk.app.widget;

import android.animation.Animator;
import qsbk.app.widget.RefreshTipView.SimpleAnimationListener;

class el extends SimpleAnimationListener {
    final /* synthetic */ RefreshTipView a;

    el(RefreshTipView refreshTipView) {
        this.a = refreshTipView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.a.setVisibility(0);
        this.a.hideDelay(3000);
    }
}
