package com.budejie.www.util;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

class q$1 implements AnimationListener {
    final /* synthetic */ ImageView a;

    q$1(ImageView imageView) {
        this.a = imageView;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
