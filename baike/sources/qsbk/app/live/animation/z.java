package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class z extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ PlaneAnimation b;

    z(PlaneAnimation planeAnimation, ImageView imageView) {
        this.b = planeAnimation;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.b.a(this.a);
        this.b.a();
    }
}
