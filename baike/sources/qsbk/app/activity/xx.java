package qsbk.app.activity;

import android.animation.Animator;
import qsbk.app.widget.TransitionDraweeView.SimpleAnimationListener;

class xx extends SimpleAnimationListener {
    final /* synthetic */ xw a;

    xx(xw xwVar) {
        this.a = xwVar;
    }

    public void onAnimationStart(Animator animator) {
        this.a.b.d.setVisibility(0);
    }

    public void onAnimationEnd(Animator animator) {
        this.a.b.k.setVisibility(4);
        this.a.b.f.setVisibility(0);
        this.a.b.d.setVisibility(0);
        this.a.b.onPageSelected(this.a.b.c);
    }
}
