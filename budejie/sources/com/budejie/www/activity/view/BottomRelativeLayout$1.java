package com.budejie.www.activity.view;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class BottomRelativeLayout$1 implements AnimationListener {
    final /* synthetic */ BottomRelativeLayout a;

    BottomRelativeLayout$1(BottomRelativeLayout bottomRelativeLayout) {
        this.a = bottomRelativeLayout;
    }

    public void onAnimationStart(Animation animation) {
        BottomRelativeLayout.a(this.a, true);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        BottomRelativeLayout.a(this.a, false);
    }
}
