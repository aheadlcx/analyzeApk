package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class az extends AnimatorListenerAdapter {
    final /* synthetic */ FanfanleGameView a;

    az(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.m.post(new ba(this));
    }
}
