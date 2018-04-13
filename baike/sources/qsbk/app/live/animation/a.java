package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class a extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ BalloonAnimation b;

    a(BalloonAnimation balloonAnimation, ImageView imageView) {
        this.b = balloonAnimation;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.b.a(this.a);
        this.b.a();
    }
}
