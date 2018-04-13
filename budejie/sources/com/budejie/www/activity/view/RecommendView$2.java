package com.budejie.www.activity.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class RecommendView$2 implements AnimationListener {
    final /* synthetic */ View a;
    final /* synthetic */ RecommendView b;

    RecommendView$2(RecommendView recommendView, View view) {
        this.b = recommendView;
        this.a = view;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.clearAnimation();
        RecommendView.b(this.b).setVisibility(8);
    }
}
