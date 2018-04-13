package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class aj extends AnimatorListenerAdapter {
    final /* synthetic */ FanfanleGameView a;

    aj(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.m.post(new ak(this));
    }
}
