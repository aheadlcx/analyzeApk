package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class ab extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ShipAnimation b;

    ab(ShipAnimation shipAnimation, ImageView imageView) {
        this.b = shipAnimation;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.b.a(this.a);
        this.b.a(this.b.h);
    }
}
