package qsbk.app.activity;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class dg implements AnimationListener {
    final /* synthetic */ df a;

    dg(df dfVar) {
        this.a = dfVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.a.a.C.removeView(this.a.a.a.k);
        if (this.a.a.a.H.get()) {
            this.a.a.a.k();
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
