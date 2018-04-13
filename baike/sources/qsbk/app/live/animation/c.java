package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class c extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ CarAnimation b;

    c(CarAnimation carAnimation, ImageView imageView) {
        this.b = carAnimation;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.b.a(this.a);
        if (this.b.j != null) {
            this.b.a(this.b.j);
            this.b.j = null;
        }
        if (this.b.k != null) {
            this.b.a(this.b.k);
            this.b.k = null;
        }
        this.b.a();
    }
}
