package cn.v6.sixrooms.widgets.phone;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

final class ak implements AnimatorListener {
    final /* synthetic */ ShowGuardPopWindow a;

    ak(ShowGuardPopWindow showGuardPopWindow) {
        this.a = showGuardPopWindow;
    }

    public final void onAnimationStart(Animator animator) {
        ShowGuardPopWindow.k(this.a);
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
    }

    public final void onAnimationCancel(Animator animator) {
    }
}
