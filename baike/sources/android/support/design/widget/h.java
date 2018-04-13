package android.support.design.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class h implements AnimationListener {
    final /* synthetic */ int a;
    final /* synthetic */ BaseTransientBottomBar b;

    h(BaseTransientBottomBar baseTransientBottomBar, int i) {
        this.b = baseTransientBottomBar;
        this.a = i;
    }

    public void onAnimationEnd(Animation animation) {
        this.b.c(this.a);
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
