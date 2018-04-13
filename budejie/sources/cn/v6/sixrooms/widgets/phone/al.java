package cn.v6.sixrooms.widgets.phone;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

final class al implements AnimatorListener {
    final /* synthetic */ ShowGuardPopWindow a;

    al(ShowGuardPopWindow showGuardPopWindow) {
        this.a = showGuardPopWindow;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        this.a.showTitleBgAnimator();
        ShowGuardPopWindow.l(this.a);
        ShowGuardPopWindow.m(this.a);
    }

    public final void onAnimationCancel(Animator animator) {
    }
}
