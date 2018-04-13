package qsbk.app.fragments;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class kr implements AnimationListener {
    final /* synthetic */ kq a;

    kr(kq kqVar) {
        this.a = kqVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.a.a();
        this.a.a.m.onAnimationEnd();
    }
}
