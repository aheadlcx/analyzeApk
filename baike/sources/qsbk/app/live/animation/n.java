package qsbk.app.live.animation;

import android.animation.AnimatorSet;
import android.widget.ImageView;

class n implements Runnable {
    final /* synthetic */ ImageView a;
    final /* synthetic */ AnimatorSet b;
    final /* synthetic */ ChristmasAnimation c;

    n(ChristmasAnimation christmasAnimation, ImageView imageView, AnimatorSet animatorSet) {
        this.c = christmasAnimation;
        this.a = imageView;
        this.b = animatorSet;
    }

    public void run() {
        this.c.m.emitWithGravity(this.c.l, 80, 30);
        this.c.b.mUserInfoLayout.setVisibility(0);
        this.a.setVisibility(0);
        this.b.start();
    }
}
