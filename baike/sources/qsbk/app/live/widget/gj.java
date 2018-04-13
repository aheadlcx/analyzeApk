package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

class gj extends AnimatorListenerAdapter {
    final /* synthetic */ AnimatorSet a;
    final /* synthetic */ LevelGiftBoxDialog b;

    gj(LevelGiftBoxDialog levelGiftBoxDialog, AnimatorSet animatorSet) {
        this.b = levelGiftBoxDialog;
        this.a = animatorSet;
    }

    public void onAnimationStart(Animator animator) {
        this.b.k.setVisibility(0);
        this.b.i.setVisibility(8);
        this.b.h.setVisibility(8);
        this.b.j.setVisibility(8);
    }

    public void onAnimationEnd(Animator animator) {
        this.b.k.setVisibility(8);
        this.b.i.setVisibility(0);
        this.b.h.setVisibility(0);
        this.b.j.setVisibility(0);
        this.a.start();
    }
}
