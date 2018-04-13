package cn.v6.sixrooms.widgets.phone;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

final class am implements AnimatorListener {
    final /* synthetic */ ShowGuardPopWindow a;

    am(ShowGuardPopWindow showGuardPopWindow) {
        this.a = showGuardPopWindow;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        ShowGuardPopWindow.n(this.a).setVisibility(4);
    }

    public final void onAnimationCancel(Animator animator) {
    }
}
