package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class j extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ChangEAnimation b;

    j(ChangEAnimation changEAnimation, ImageView imageView) {
        this.b = changEAnimation;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.b.a(this.a);
    }
}
