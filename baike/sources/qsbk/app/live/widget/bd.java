package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class bd extends AnimatorListenerAdapter {
    final /* synthetic */ FanfanleGameView a;

    bd(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.m.post(new be(this));
    }
}
