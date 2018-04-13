package cn.v6.sixrooms.hall;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

final class ar implements AnimationListener {
    final /* synthetic */ MineFragment a;

    ar(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        MineFragment.r(this.a).setVisibility(0);
    }
}
