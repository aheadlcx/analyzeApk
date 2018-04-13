package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class TypeIndicator$4 implements AnimationListener {
    final /* synthetic */ TypeIndicator a;

    TypeIndicator$4(TypeIndicator typeIndicator) {
        this.a = typeIndicator;
    }

    public void onAnimationStart(Animation animation) {
        TypeIndicator.b(this.a, true);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        TypeIndicator.b(this.a, false);
    }
}
