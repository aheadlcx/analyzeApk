package cn.v6.sixrooms.hall;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

final class as implements AnimationListener {
    final /* synthetic */ MineFragment a;

    as(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        MineFragment.s(this.a).setVisibility(8);
        MineFragment.r(this.a).setVisibility(8);
    }
}
