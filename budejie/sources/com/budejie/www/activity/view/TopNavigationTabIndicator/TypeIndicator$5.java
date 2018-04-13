package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.budejie.www.R;

class TypeIndicator$5 implements AnimationListener {
    final /* synthetic */ TypeIndicator a;

    TypeIndicator$5(TypeIndicator typeIndicator) {
        this.a = typeIndicator;
    }

    public void onAnimationStart(Animation animation) {
        TypeIndicator.b(this.a, true);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        TypeIndicator.b(this.a, false);
        this.a.setVisibility(8);
        TypeIndicator.b(this.a, 8);
        this.a.layout(0, -((int) this.a.getResources().getDimension(R.dimen.toptabpageindicator_height)), this.a.getWidth(), 0);
    }
}
