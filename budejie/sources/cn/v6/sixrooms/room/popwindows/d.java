package cn.v6.sixrooms.room.popwindows;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

final class d implements AnimatorListener {
    final /* synthetic */ GodUpgradeWindow a;

    d(GodUpgradeWindow godUpgradeWindow) {
        this.a = godUpgradeWindow;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        GodUpgradeWindow.g(this.a);
    }

    public final void onAnimationCancel(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }
}
