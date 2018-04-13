package android.support.v4.app;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class m extends b {
    final /* synthetic */ ViewGroup a;
    final /* synthetic */ View b;
    final /* synthetic */ Fragment c;
    final /* synthetic */ k d;

    m(k kVar, AnimationListener animationListener, ViewGroup viewGroup, View view, Fragment fragment) {
        this.d = kVar;
        this.a = viewGroup;
        this.b = view;
        this.c = fragment;
        super(animationListener);
    }

    public void onAnimationEnd(Animation animation) {
        super.onAnimationEnd(animation);
        this.a.post(new n(this));
    }
}
