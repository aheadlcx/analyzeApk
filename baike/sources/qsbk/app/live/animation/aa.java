package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageView;
import qsbk.app.live.widget.LiveRocketBackground;

class aa extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ ImageView c;
    final /* synthetic */ ImageView d;
    final /* synthetic */ LiveRocketBackground e;
    final /* synthetic */ RocketAnimation f;

    aa(RocketAnimation rocketAnimation, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, LiveRocketBackground liveRocketBackground) {
        this.f = rocketAnimation;
        this.a = imageView;
        this.b = imageView2;
        this.c = imageView3;
        this.d = imageView4;
        this.e = liveRocketBackground;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.f.a(this.a);
        this.f.a(this.b);
        this.f.a(this.c);
        this.f.a(this.d);
        this.e.clear();
        this.f.a(this.e);
        this.f.a();
    }
}
