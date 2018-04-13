package com.prolificinteractive.materialcalendarview;

import android.animation.Animator;
import android.view.ViewPropertyAnimator;

class p extends a {
    final /* synthetic */ CharSequence a;
    final /* synthetic */ int b;
    final /* synthetic */ o c;

    p(o oVar, CharSequence charSequence, int i) {
        this.c = oVar;
        this.a = charSequence;
        this.b = i;
    }

    public void onAnimationCancel(Animator animator) {
        o.a(this.c, o.a(this.c), 0);
        o.a(this.c).setAlpha(1.0f);
    }

    public void onAnimationEnd(Animator animator) {
        o.a(this.c).setText(this.a);
        o.a(this.c, o.a(this.c), this.b);
        ViewPropertyAnimator animate = o.a(this.c).animate();
        if (o.b(this.c) == 1) {
            animate.translationX(0.0f);
        } else {
            animate.translationY(0.0f);
        }
        animate.alpha(1.0f).setDuration((long) o.d(this.c)).setInterpolator(o.c(this.c)).setListener(new a()).start();
    }
}
