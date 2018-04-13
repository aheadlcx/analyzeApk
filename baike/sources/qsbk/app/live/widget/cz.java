package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class cz extends AnimatorListenerAdapter {
    final /* synthetic */ GameView a;

    cz(GameView gameView) {
        this.a = gameView;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.d.setVisibility(4);
        this.a.c.setVisibility(0);
    }
}
