package cn.v6.sixrooms.widgets.phone;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

final class an implements AnimatorListener {
    final /* synthetic */ ShowGuardPopWindow a;

    an(ShowGuardPopWindow showGuardPopWindow) {
        this.a = showGuardPopWindow;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        this.a.dismiss();
        ShowGuardPopWindow.o(this.a).sendEmptyMessage(2);
    }

    public final void onAnimationCancel(Animator animator) {
    }
}
