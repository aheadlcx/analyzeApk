package qsbk.app.widget;

import android.animation.Animator;
import qsbk.app.widget.RefreshTipView.SimpleAnimationListener;

class em extends SimpleAnimationListener {
    final /* synthetic */ RefreshTipView a;

    em(RefreshTipView refreshTipView) {
        this.a = refreshTipView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.a.setVisibility(8);
        if (RefreshTipView.a() == RefreshTipView.FIRST_REFRESH_INTERVAL) {
            RefreshTipView.a(RefreshTipView.SECOND_REFRESH_INTERVAL);
        }
        this.a.showDelay(RefreshTipView.a());
    }
}
