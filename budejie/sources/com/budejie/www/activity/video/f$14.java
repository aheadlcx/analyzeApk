package com.budejie.www.activity.video;

import android.view.animation.Animation;

class f$14 extends f$a {
    final /* synthetic */ f a;

    f$14(f fVar) {
        this.a = fVar;
        super(fVar);
    }

    public void onAnimationEnd(Animation animation) {
        super.onAnimationEnd(animation);
        if (f.h(this.a)) {
            f.i(this.a).setVisibility(8);
            f.j(this.a).setVisibility(0);
            f.k(this.a);
            f.a(this.a, false);
        }
    }
}
