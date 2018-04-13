package cn.v6.sixrooms.widgets.phone;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

final class aj implements AnimatorListener {
    final /* synthetic */ ShowGuardPopWindow a;

    aj(ShowGuardPopWindow showGuardPopWindow) {
        this.a = showGuardPopWindow;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        ShowGuardPopWindow.j(this.a);
    }

    public final void onAnimationCancel(Animator animator) {
    }
}
