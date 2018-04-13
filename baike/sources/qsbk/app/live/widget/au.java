package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class au extends AnimatorListenerAdapter {
    final /* synthetic */ FanfanleGameView a;

    au(FanfanleGameView fanfanleGameView) {
        this.a = fanfanleGameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.ab.setVisibility(4);
        this.a.K.setTranslationX(0.0f);
        this.a.L.setTranslationX(0.0f);
        this.a.M.setTranslationX(0.0f);
    }
}
