package com.tencent.bugly.beta.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class a$3 implements AnimationListener {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (this.a.b != null) {
            this.a.b.setVisibility(8);
        }
        a.a(this.a);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
