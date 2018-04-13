package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class bf extends AnimatorListenerAdapter {
    final /* synthetic */ FanfanleGameView a;

    bf(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.m.post(new bg(this));
    }
}
