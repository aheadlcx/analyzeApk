package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class d extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ CarAnimation b;

    d(CarAnimation carAnimation, ImageView imageView) {
        this.b = carAnimation;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.b.a(this.a);
    }
}
