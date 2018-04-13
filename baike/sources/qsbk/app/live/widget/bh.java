package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class bh extends AnimatorListenerAdapter {
    final /* synthetic */ FanfanleGameView a;

    bh(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.m.post(new bi(this));
    }
}
