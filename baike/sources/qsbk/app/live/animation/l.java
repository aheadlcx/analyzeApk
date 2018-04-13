package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;

class l extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ ChristmasAnimation c;

    l(ChristmasAnimation christmasAnimation, ImageView imageView, ImageView imageView2) {
        this.c = christmasAnimation;
        this.a = imageView;
        this.b = imageView2;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.setVisibility(8);
        this.c.a(this.a);
        this.c.a(this.b);
        this.c.m.stopEmitting();
        this.c.m.cancel();
        this.c.a();
    }
}
