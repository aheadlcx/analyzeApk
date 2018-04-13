package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

class k extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ChristmasAnimation b;

    k(ChristmasAnimation christmasAnimation, ImageView imageView) {
        this.b = christmasAnimation;
        this.a = imageView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.setVisibility(4);
        this.b.n.oneShot(this.a, 50, new AccelerateInterpolator());
        this.b.o.oneShot(this.a, 50, new AccelerateInterpolator());
        this.b.p.oneShot(this.a, 50, new AccelerateInterpolator());
        this.b.q.oneShot(this.a, 50, new AccelerateInterpolator());
    }
}
