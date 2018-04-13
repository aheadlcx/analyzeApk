package qsbk.app.live.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.widget.ImageView;

class ag extends AnimatorListenerAdapter {
    final /* synthetic */ ImageView a;
    final /* synthetic */ ImageView b;
    final /* synthetic */ ImageView c;
    final /* synthetic */ ImageView d;
    final /* synthetic */ ImageView e;
    final /* synthetic */ UnknownLiquidAnimation f;

    ag(UnknownLiquidAnimation unknownLiquidAnimation, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5) {
        this.f = unknownLiquidAnimation;
        this.a = imageView;
        this.b = imageView2;
        this.c = imageView3;
        this.d = imageView4;
        this.e = imageView5;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.f.a(this.a);
        this.b.setVisibility(0);
        AnimatorSet a = this.f.c(this.b);
        a.addListener(new ah(this));
        a.start();
        this.f.b.mUserInfoLayout.setVisibility(0);
        this.f.d(this.f.b.mUserInfoLayout).start();
        this.c.setVisibility(0);
        this.f.f(this.c).start();
        this.e.setVisibility(0);
        this.f.g(this.e).start();
        this.d.setVisibility(0);
        this.f.h(this.d).start();
    }
}
