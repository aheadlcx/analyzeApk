package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class y extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ EvilAnimation b;

    y(EvilAnimation evilAnimation, ImageView imageView) {
        this.b = evilAnimation;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.b.a(this.a);
    }
}
