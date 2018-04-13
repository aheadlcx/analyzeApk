package cn.v6.sixrooms.room.popwindows;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

final class e implements AnimatorListener {
    final /* synthetic */ GodUpgradeWindow a;

    e(GodUpgradeWindow godUpgradeWindow) {
        this.a = godUpgradeWindow;
    }

    public final void onAnimationStart(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        GodUpgradeWindow.h(this.a);
        GodUpgradeWindow.i(this.a);
        GodUpgradeWindow.j(this.a);
        GodUpgradeWindow.k(this.a);
    }

    public final void onAnimationCancel(Animator animator) {
    }
}
