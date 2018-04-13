package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class bb extends AnimatorListenerAdapter {
    final /* synthetic */ FanfanleGameView a;

    bb(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.m.post(new bc(this));
    }
}
