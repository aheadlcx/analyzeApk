package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class h extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ChangEAnimation b;

    h(ChangEAnimation changEAnimation, ImageView imageView) {
        this.b = changEAnimation;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.b.a(this.a);
    }
}
