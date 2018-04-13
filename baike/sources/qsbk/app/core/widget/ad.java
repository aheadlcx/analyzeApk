package qsbk.app.core.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class ad implements AnimationListener {
    final /* synthetic */ ac a;

    ad(ac acVar) {
        this.a = acVar;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.a.setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
