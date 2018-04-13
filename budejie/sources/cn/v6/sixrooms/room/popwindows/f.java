package cn.v6.sixrooms.room.popwindows;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

final class f implements AnimatorListener {
    final /* synthetic */ GodUpgradeWindow a;

    f(GodUpgradeWindow godUpgradeWindow) {
        this.a = godUpgradeWindow;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        this.a.dismiss();
    }

    public final void onAnimationCancel(Animator animator) {
    }
}
