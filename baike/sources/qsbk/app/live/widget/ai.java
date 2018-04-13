package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

class ai extends AnimatorListenerAdapter {
    final /* synthetic */ AnimatorSet a;
    final /* synthetic */ FanfanleGameView b;

    ai(FanfanleGameView fanfanleGameView, AnimatorSet animatorSet) {
        this.b = fanfanleGameView;
        this.a = animatorSet;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.start();
    }
}
